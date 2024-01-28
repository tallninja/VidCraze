/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 2:35 PM
 */
package com.vidcraze.domain;

import com.vidcraze.repository.HashTagRepository;
import com.vidcraze.repository.VideoRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

@Sql(scripts = "classpath:sql/hashtag.sql")
@Sql(scripts = "classpath:sql/hashtag-rollback.sql", phase = Sql.Phase.AFTER_ALL)
@MicronautTest(startApplication = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HashTagTest implements TestPropertyProvider {

    @Inject
    private HashTagRepository hashTagRepository;

    @Inject
    private VideoRepository videoRepository;

    @Override
    public @NonNull Map<String, String> getProperties() {
        return PostgreSQL.getProperties();
    }

    @Test
    public void testHashTagsCount() {
        assertEquals(3L, hashTagRepository.count());
    }

    @Test
    public void testFindVideoHashTag() {
        Video video1 = videoRepository.findById(1).get();
        List<HashTag> hashTags = hashTagRepository.findVideoHashTags(video1);
        assertEquals(1, hashTags.size());
        assertEquals("Tag 1", hashTags.get(0).getTag());
    }

    @Test
    public void testCreateHashTag() {
        HashTag newHashTag = HashTag.builder().tag("Tag 4").build();
        hashTagRepository.save(newHashTag);
        assertEquals(4L, hashTagRepository.count());
    }

    @Test
    public void testAddHashTagToVideo() {
        HashTag newHashTag = HashTag.builder().tag("Tag 5").build();
        newHashTag = hashTagRepository.save(newHashTag);
        Video video1 = videoRepository.findById(1).get();
        video1.getHashTags().add(newHashTag);
        videoRepository.update(video1);
        video1 = videoRepository.findById(1).get();
        assertEquals(2, video1.getHashTags().size());
    }
}
