/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 2:17 PM
 */
package com.vidcraze.domain;

import com.vidcraze.repository.DislikeRepository;
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

@Sql(scripts = "classpath:sql/dislike.sql")
@Sql(scripts = "classpath:sql/dislike-rollback.sql", phase = Sql.Phase.AFTER_ALL)
@MicronautTest(startApplication = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DislikeTest implements TestPropertyProvider {

    @Inject
    private DislikeRepository dislikeRepository;

    @Inject
    private VideoRepository videoRepository;

    @Override
    public @NonNull Map<String, String> getProperties() {
        return PostgreSQL.getProperties();
    }

    @Test
    public void testDislikeCount() {
        assertEquals(3L, dislikeRepository.count());
    }

    @Test
    public void testDislikeVideo() {
        Video video1 = videoRepository.findById(1).get();
        Video dislikedVideo = dislikeRepository.findById(11).get().getVideo();
        assertEquals(video1.getId(), dislikedVideo.getId());
    }

    @Test
    public void testVideoDislikesCount() {
        Video video1 = videoRepository.findById(1).get();
        Video video2 = videoRepository.findById(2).get();
        Dislike video1Dislike = Dislike.builder()
                .user("user 4")
                .video(video1)
                .build();
        dislikeRepository.save(video1Dislike);

        int video1DislikeCount = dislikeRepository.findByVideo(video1).size();
        int video2DislikeCount = dislikeRepository.findByVideo(video2).size();
        assertEquals(2, video1DislikeCount);
        assertEquals(1, video2DislikeCount);
    }
}
