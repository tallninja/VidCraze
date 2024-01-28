/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 1:15 PM
 */
package com.vidcraze.domain;

import com.vidcraze.repository.VideoRepository;
import com.vidcraze.repository.ViewRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = "classpath:sql/view.sql")
@Sql(scripts = "classpath:sql/view-rollback.sql", phase = Sql.Phase.AFTER_ALL)
@MicronautTest(startApplication = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ViewTest implements TestPropertyProvider {

    @Inject
    private ViewRepository viewRepository;

    @Inject
    private VideoRepository videoRepository;

    @Override
    public @NonNull Map<String, String> getProperties() {
        return PostgreSQL.getProperties();
    }

    @Test
    public void testViewCount() {
        assertEquals(3L, viewRepository.count());
    }

    @Test
    public void testViewVideo() {
        Video video1 = videoRepository.findById(1).get();
        Video viewedVideo = viewRepository.findById(11).get().getVideo();
        assertEquals(video1.getId(), viewedVideo.getId());
    }

    @Test
    public void testVideoViewsCount() {
        Video video1 = videoRepository.findById(1).get();
        Video video2 = videoRepository.findById(2).get();
        View video1View = View.builder()
                .user("user 4")
                .video(video1)
                .build();
        viewRepository.save(video1View);

        int video1ViewCount = viewRepository.findByVideo(video1).size();
        int video2ViewCount = viewRepository.findByVideo(video2).size();
        assertEquals(2, video1ViewCount);
        assertEquals(1, video2ViewCount);
    }
}
