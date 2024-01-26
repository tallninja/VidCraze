/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:52 AM
 */
package com.vidcraze.repository;

import com.vidcraze.domain.HashTag;
import com.vidcraze.domain.Video;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, String> {

    @Query("FROM HashTag h WHERE :video MEMBER OF h.videos")
    List<HashTag> findVideoHashTags(Video video);

}
