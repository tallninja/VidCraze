/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 4:59 PM
 */
package com.vidcraze.mapper;

import com.vidcraze.domain.Dislike;
import com.vidcraze.domain.HashTag;
import com.vidcraze.dtos.DislikeDTO;
import com.vidcraze.repository.HashTagRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Singleton
public class DislikeMapper {

    private final HashTagRepository hashTagRepository;

    public DislikeMapper(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    public DislikeDTO toDislikeDTO(Dislike dislike) {
        List<HashTag> hashTags = hashTagRepository.findVideoHashTags(dislike.getVideo());
        return DislikeDTO.builder()
                .id(dislike.getId())
                .user(dislike.getUser())
                .video(dislike.getVideo().getId())
                .hashTags(hashTags.stream()
                        .map(Objects::toString)
                        .collect(Collectors.toSet()))
                .build();
    }

}
