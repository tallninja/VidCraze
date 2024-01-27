/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 6:04 PM
 */
package com.vidcraze;

import com.vidcraze.domain.HashTag;
import com.vidcraze.dtos.DislikeDTO;
import com.vidcraze.dtos.LikeDTO;
import com.vidcraze.dtos.VideoDTO;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Set;


@Slf4j
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class ThmListener {

    private final HashTagRepository hashTagRepository;

    public ThmListener(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    @Topic("post-video")
    public void handlePostVideo(@KafkaKey Integer id, VideoDTO videoDTO) {
        Set<String> hashTags = videoDTO.getHashTags();
        for (String tag : hashTags) {
            Optional<HashTag> existingHashTag = hashTagRepository.findById(tag);
            if (existingHashTag.isEmpty()) {
                hashTagRepository
                        .save(HashTag.builder().tag(tag).likes(0).build());
            }
        }
        log.info("Video posted: " + videoDTO);
    }

    @Topic("like-video")
    public void handleLikeVideo(@KafkaKey Integer id, LikeDTO likeDTO) {
        Set<String> hashTags = likeDTO.getHashTags();
        for (String tag : hashTags) {
            Optional<HashTag> existingHashTag = hashTagRepository.findById(tag);
            if (existingHashTag.isPresent()) {
                HashTag hashTag = existingHashTag.get();
                hashTag.setLikes(hashTag.getLikes() + 1);
                hashTagRepository.update(hashTag);
            }
        }
        log.info("Video liked: " + likeDTO);
    }

    @Topic("dislike-video")
    public void handleDislikeVideo(@KafkaKey Integer id, DislikeDTO dislikeDTO) {
        Set<String> hashTags = dislikeDTO.getHashTags();
        for (String tag : hashTags) {
            Optional<HashTag> existingHashTag = hashTagRepository.findById(tag);
            if (existingHashTag.isPresent()) {
                HashTag hashTag = existingHashTag.get();
                hashTag.setLikes(hashTag.getLikes() - 1);
                hashTagRepository.update(hashTag);
            }
        }
        log.info("Video disliked: " + dislikeDTO);
    }

}
