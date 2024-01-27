/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 8:25 PM
 */
package com.vidcraze;

import com.vidcraze.dtos.LikeDTO;
import com.vidcraze.serdes.LikeDTODeserializer;
import com.vidcraze.serdes.LikeDTOSerde;
import com.vidcraze.serdes.LikeDTOSerializer;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import io.micronaut.serde.ObjectMapper;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.util.Properties;

@Slf4j
@Factory
public class TrendingHashtagsStream {

    public static final String INPUT = "like-video";
    public static final String OUTPUT = "trending-hashtags";

    @Singleton
    @Named("trending-hashtags-stream")
    public KStream<Integer, LikeDTO> trendingHashTagsStream(ConfiguredStreamBuilder builder, ObjectMapper objectMapper) {
        Properties props = builder.getConfiguration();

        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Integer.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, LikeDTOSerde.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "1000");

        KStream<Integer, LikeDTO> source = builder.stream(INPUT);
        KTable<String, Long> groupedByHashtag = source
                .flatMapValues(LikeDTO::getHashTags)
                .groupBy((key, hashtag) -> hashtag)
                .count(Materialized.as("trending-hashtags-store"));

        groupedByHashtag.toStream().to(OUTPUT, Produced.with(Serdes.String(), Serdes.Long()));

        return source;
    }

}
