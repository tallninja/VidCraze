/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 1:20 AM
 */
package com.vidcraze.mapper;

import com.vidcraze.domain.HashTag;
import com.vidcraze.domain.Video;
import com.vidcraze.dtos.PostVideoDTO;
import com.vidcraze.dtos.VideoDTO;
import com.vidcraze.repository.DislikeRepository;
import com.vidcraze.repository.HashTagRepository;
import com.vidcraze.repository.LikeRepository;
import com.vidcraze.repository.ViewRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Singleton
public class VideoMapper {

    private final HashTagMapper hashTagMapper;
    private final ViewRepository viewRepository;
    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;
    private final HashTagRepository hashTagRepository;

    public VideoMapper(HashTagMapper hashTagMapper, ViewRepository viewRepository, LikeRepository likeRepository, DislikeRepository dislikeRepository, HashTagRepository hashTagRepository) {
        this.hashTagMapper = hashTagMapper;
        this.viewRepository = viewRepository;
        this.likeRepository = likeRepository;
        this.dislikeRepository = dislikeRepository;
        this.hashTagRepository = hashTagRepository;
    }

    public Video toVideo(PostVideoDTO videoDTO) {
        return Video.builder()
                .title(videoDTO.getTitle())
                .user(videoDTO.getUser())
                .hashTags(
                        videoDTO.getHashTags().stream()
                                .map(hashTagMapper::toHashTag)
                                .collect(Collectors.toSet()))
                .build();
    }

    public VideoDTO toVideoDTO(Video video) {
        List<HashTag> hashTags = hashTagRepository.findVideoHashTags(video);
        return VideoDTO.builder()
                .id(video.getId())
                .title(video.getTitle())
                .user(video.getUser())
                .views(viewRepository.findByVideo(video).size())
                .likes(likeRepository.findByVideo(video).size())
                .dislikes(dislikeRepository.findByVideo(video).size())
                .hashTags(
                        hashTags.stream()
                                .map(Objects::toString)
                                .collect(Collectors.toList()))
                .build();
    }
}
