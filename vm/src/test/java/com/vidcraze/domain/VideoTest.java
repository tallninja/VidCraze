/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 12:33 PM
 */
package com.vidcraze.domain;

import com.vidcraze.repository.VideoRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

@Sql(scripts = "classpath:sql/video.sql")
@Sql(scripts = "classpath:sql/video-rollback.sql", phase = Sql.Phase.AFTER_ALL)
@MicronautTest(startApplication = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VideoTest implements TestPropertyProvider {

    @Inject
    private VideoRepository videoRepository;

    @Override
    public @NonNull Map<String, String> getProperties() {
        return PostgreSQL.getProperties();
    }

    @Test
    void thereAreThreeVideos() {
        assertEquals(3L, videoRepository.count());
    }

    @Test
    void testInsertVideo() {
        Video newVideo = Video.builder()
                .title("New Video 4")
                .user("user 4")
                .build();
        Video createdVideo = videoRepository.save(newVideo);
        assertEquals(newVideo.getTitle(), createdVideo.getTitle());
        assertEquals(4L, videoRepository.count());
    }

    @Test
    void testFindById() {
        Video video = videoRepository.findById(2).get();
        assertEquals("New Video 2", video.getTitle());
        assertEquals("user 2", video.getUser());
    }
}
