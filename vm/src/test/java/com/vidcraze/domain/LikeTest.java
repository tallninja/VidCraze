/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 2:17 PM
 */
package com.vidcraze.domain;

import com.vidcraze.repository.LikeRepository;
import com.vidcraze.repository.VideoRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql(scripts = "classpath:sql/like.sql")
@Sql(scripts = "classpath:sql/like-rollback.sql", phase = Sql.Phase.AFTER_ALL)
@MicronautTest(startApplication = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LikeTest implements TestPropertyProvider {

    @Inject
    private LikeRepository likeRepository;

    @Inject
    private VideoRepository videoRepository;

    @Override
    public @NonNull Map<String, String> getProperties() {
        return PostgreSQL.getProperties();
    }

    @Test
    public void testLikeCount() {
        assertEquals(3L, likeRepository.count());
    }

    @Test
    public void testLikeVideo() {
        Video video1 = videoRepository.findById(1).get();
        Video likedVideo = likeRepository.findById(11).get().getVideo();
        assertEquals(video1.getId(), likedVideo.getId());
    }

    @Test
    public void testVideoLikesCount() {
        Video video1 = videoRepository.findById(1).get();
        Video video2 = videoRepository.findById(2).get();
        Like video1Like = Like.builder()
                .user("user 4")
                .video(video1)
                .build();
        likeRepository.save(video1Like);

        int video1LikeCount = likeRepository.findByVideo(video1).size();
        int video2LikeCount = likeRepository.findByVideo(video2).size();
        assertEquals(2, video1LikeCount);
        assertEquals(1, video2LikeCount);
    }
}
