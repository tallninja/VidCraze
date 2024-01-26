/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:54 AM
 */
package com.vidcraze.repository;

import com.vidcraze.domain.Like;
import com.vidcraze.domain.Video;
import com.vidcraze.domain.View;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    List<Like> findByVideo(Video video);

    @Query("FROM Like l WHERE l.user = :user AND l.video = :video")
    Optional<Like> findByUserAndVideo(String user, Video video);

}
