/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 4:41 PM
 */
package com.vidcraze.mapper;

import com.vidcraze.domain.HashTag;
import com.vidcraze.domain.Like;
import com.vidcraze.dtos.LikeDTO;
import com.vidcraze.repository.HashTagRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Singleton
public class LikeMapper {

    private final HashTagRepository hashTagRepository;

    public LikeMapper(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    public LikeDTO toLikeDto(Like like) {
        List<HashTag> hashTags = hashTagRepository.findVideoHashTags(like.getVideo());
        return LikeDTO.builder()
                .id(like.getId())
                .user(like.getUser())
                .video(like.getVideo().getId())
                .hashTags(hashTags.stream()
                        .map(Objects::toString)
                        .collect(Collectors.toSet()))
                .build();
    }

}
