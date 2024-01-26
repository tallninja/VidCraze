/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:53 AM
 */
package com.vidcraze.repository;

import com.vidcraze.domain.Video;
import com.vidcraze.domain.View;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewRepository extends JpaRepository<View, Integer> {

    List<View> findByVideo(Video video);

    @Query("FROM View v WHERE v.user = :user AND v.video = :video")
    Optional<View> findByUserAndVideo(String user, Video video);

}
