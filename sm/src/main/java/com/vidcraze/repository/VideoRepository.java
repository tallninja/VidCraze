/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/27/24 : 10:20 AM
 */
package com.vidcraze.repository;

import com.vidcraze.domain.HashTag;
import com.vidcraze.domain.Video;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

    @Query("FROM Video  v WHERE :hashTag MEMBER OF v.hashTags ORDER BY v.likes DESC LIMIT 10")
    List<Video> findTopVideosWithHashTag(HashTag hashTag);

}
