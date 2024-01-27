/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/27/24 : 9:30 AM
 */
package com.vidcraze;

import com.vidcraze.domain.HashTag;
import com.vidcraze.domain.Video;
import com.vidcraze.dtos.DislikeDTO;
import com.vidcraze.dtos.LikeDTO;
import com.vidcraze.dtos.VideoDTO;
import com.vidcraze.repository.HashTagRepository;
import com.vidcraze.repository.VideoRepository;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Singleton
@KafkaListener(offsetReset = OffsetReset.LATEST, groupId = "subscription-manager")
public class SmEventHandler {

    private final VideoRepository videoRepository;
    private final HashTagRepository hashTagRepository;

    public SmEventHandler(VideoRepository videoRepository, HashTagRepository hashTagRepository) {
        this.videoRepository = videoRepository;
        this.hashTagRepository = hashTagRepository;
    }

    @Topic("post-video")
    public void handlePostVideo(@KafkaKey Integer id, VideoDTO videoDTO) {
        Set<HashTag> hashTags = videoDTO.getHashTags().stream().map((tag) -> {
            Optional<HashTag> existingHashtag = hashTagRepository.findById(tag);
            return existingHashtag.orElseGet(
                    () -> hashTagRepository.save(HashTag.builder().tag(tag).build())
            );
        }).collect(Collectors.toSet());

        Video video = Video.builder()
                .id(videoDTO.getId())
                .title(videoDTO.getTitle())
                .user(videoDTO.getUser())
                .likes(videoDTO.getLikes())
                .hashTags(hashTags)
                .build();

        videoRepository.save(video);
        log.info("Video posted: " + video);
    }

    @Topic("like-video")
    public void handleLikeVideo(@KafkaKey Integer id, LikeDTO likeDTO) {
        Optional<Video> existingVideo = videoRepository.findById(likeDTO.getVideo());

        if (existingVideo.isPresent()) {
            Video video = existingVideo.get();
            video.setLikes(video.getLikes() + 1);
            videoRepository.update(video);
            log.info("Video liked: " + video);
        }
    }

    @Topic("dislike-video")
    public void handleDislikeVideo(@KafkaKey Integer id, DislikeDTO dislikeDTO) {
        Optional<Video> existingVideo = videoRepository.findById(dislikeDTO.getVideo());

        if (existingVideo.isPresent()) {
            Video video = existingVideo.get();
            video.setLikes(video.getLikes() - 1);
            videoRepository.update(video);
            log.info("Video disliked: " + video);
        }
    }

}
