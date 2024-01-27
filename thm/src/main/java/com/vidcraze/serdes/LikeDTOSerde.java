/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 9:35 PM
 */
package com.vidcraze.serdes;

import com.vidcraze.dtos.LikeDTO;
import jakarta.inject.Singleton;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;


@Singleton
public class LikeDTOSerde implements Serde<LikeDTO> {

    @Override
    public Serializer<LikeDTO> serializer() {
        return new LikeDTOSerializer();
    }

    @Override
    public Deserializer<LikeDTO> deserializer() {
        return new LikeDTODeserializer();
    }
}
