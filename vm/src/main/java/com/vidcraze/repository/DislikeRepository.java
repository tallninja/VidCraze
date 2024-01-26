/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:54 AM
 */
package com.vidcraze.repository;

import com.vidcraze.domain.Dislike;
import com.vidcraze.domain.Video;
import com.vidcraze.domain.View;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DislikeRepository extends JpaRepository<Dislike, Integer> {

    List<Dislike> findByVideo(Video video);

    @Query("FROM Dislike d WHERE d.user = :user AND d.video = :video")
    Optional<Dislike> findByUserAndVideo(String user, Video video);

}
