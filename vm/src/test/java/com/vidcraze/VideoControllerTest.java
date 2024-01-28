/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 3:41 PM
 */
package com.vidcraze;

import com.vidcraze.dtos.PostVideoDTO;
import com.vidcraze.dtos.VideoDTO;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;

import java.util.List;
import java.util.Set;

//@Sql(scripts = "classpath:sql/vm.sql")
@MicronautTest
public class VideoControllerTest {

    @Inject
    private VideoControllerTestClient client;

    @Test
    @Order(1)
    public void testPostVideo() {
        PostVideoDTO newVideo = PostVideoDTO.builder()
                .title("Traffiked")
                .user("test_user")
                .hashTags(Set.of("documentary", "freedom"))
                .build();
        Publisher<VideoDTO> videoPublisher = client.postVideo(newVideo);
        Flowable.fromPublisher(videoPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

    @Test
    @Order(2)
    public void testGetAllVideos() {
        Publisher<List<VideoDTO>> videosPublisher = client.getVideos();
        Flowable.fromPublisher(videosPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

    @Test
    @Order(3)
    public void testGetVideo() {
        Publisher<VideoDTO> videoPublisher = client.getVideo(1);
        Flowable.fromPublisher(videoPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

}
