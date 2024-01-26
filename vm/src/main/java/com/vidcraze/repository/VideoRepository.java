/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:45 AM
 */
package com.vidcraze.repository;

import com.vidcraze.domain.HashTag;
import com.vidcraze.domain.Video;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

    @Query("FROM Video v WHERE :hashTags MEMBER OF v.hashTags")
    List<Video> findVideosByHashTags(Set<HashTag> hashTags);

}
