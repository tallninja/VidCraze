/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 8:25 PM
 */
package com.vidcraze;

import com.vidcraze.dtos.LikeDTO;
import com.vidcraze.serdes.LikeDTOSerde;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import io.micronaut.serde.ObjectMapper;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.util.Properties;

@Factory
public class TrendingHashtagsStream {

    public static final String INPUT = "like-video";
    public static final String OUTPUT = "trending-hashtags";

    @Singleton
    @Named("trendingHashTags")
    public KStream<Integer, LikeDTO> trendingHashTagsStream(ConfiguredStreamBuilder builder, ObjectMapper objectMapper) {
        Properties props = builder.getConfiguration();
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, LikeDTOSerde.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        KStream<Integer, LikeDTO> source = builder.stream(INPUT);
        KTable<Windowed<String>, Long> stream = source
                .flatMapValues(LikeDTO::getHashTags)
                .groupBy((key, value) -> value)
                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofHours(1)))
                .count();

        stream.toStream().to(OUTPUT);

        return source;
    }

}
