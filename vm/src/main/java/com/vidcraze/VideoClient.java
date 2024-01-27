/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 4:02 PM
 */
package com.vidcraze;

import com.vidcraze.dtos.DislikeDTO;
import com.vidcraze.dtos.LikeDTO;
import com.vidcraze.dtos.VideoDTO;
import com.vidcraze.dtos.ViewDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface VideoClient {

    @Topic("post-video")
    void postVideo(@KafkaKey Integer id, VideoDTO videoDTO);

    @Topic("like-video")
    void likeVideo(@KafkaKey Integer id, LikeDTO likeDTO);

    @Topic("dislike-video")
    void dislikeVideo(@KafkaKey Integer id, DislikeDTO dislikeDTO);

    @Topic("view-video")
    void viewVideo(@KafkaKey Integer id, ViewDTO viewDTO);

}
