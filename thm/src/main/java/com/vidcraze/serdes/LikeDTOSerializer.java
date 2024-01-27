/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 10:09 PM
 */
package com.vidcraze.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidcraze.dtos.LikeDTO;
import jakarta.inject.Singleton;
import org.apache.kafka.common.serialization.Serializer;
import org.hibernate.type.SerializationException;

import java.util.Objects;

@Singleton
public class LikeDTOSerializer implements Serializer<LikeDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, LikeDTO data) {
        if (Objects.isNull(data)) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing like.", e);
        }
    }
}
