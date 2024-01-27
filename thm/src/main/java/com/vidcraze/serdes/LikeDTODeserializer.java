/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 10:14 PM
 */
package com.vidcraze.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidcraze.dtos.LikeDTO;
import org.apache.kafka.common.serialization.Deserializer;
import org.hibernate.type.SerializationException;

import java.util.Objects;

public class LikeDTODeserializer implements Deserializer<LikeDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public LikeDTO deserialize(String topic, byte[] data) {
        if (Objects.isNull(data)) {
            return null;
        }

        LikeDTO likeDTO;

        try {
            likeDTO = objectMapper.treeToValue(objectMapper.readTree(data), LikeDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Failed to deserialize like.", e);
        }

        return likeDTO;
    }
}
