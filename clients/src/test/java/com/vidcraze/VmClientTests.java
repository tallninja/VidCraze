/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 3:41 PM
 */
package com.vidcraze;

import com.vidcraze.dtos.*;
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
public class VmClientTests {

    @Inject
    private VmClient client;

    private static VideoDTO testVideo;

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
                .doOnNext((video) -> {
                    testVideo = video;
                    System.out.println(video);
                })
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
        Publisher<VideoDTO> videoPublisher = client.getVideo(testVideo.getId());
        Flowable.fromPublisher(videoPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

    @Test
    @Order(4)
    public void testEditVideo() {
        PostVideoDTO editData = PostVideoDTO.builder()
                .title("Traffiked (UPDATED!!)")
                .user("test_user")
                .build();
        Publisher<VideoDTO> videoPublisher = client.editVideo(testVideo.getId(), editData);
        Flowable.fromPublisher(videoPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

    @Test
    @Order(5)
    public void testViewVideo() {
        Publisher<ViewDTO> videoPublisher = client.viewVideo(testVideo.getId(), "test_user");
        Flowable.fromPublisher(videoPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

    @Test
    @Order(6)
    public void testLikeVideo() {
        Publisher<LikeDTO> videoPublisher = client.likeVideo(testVideo != null ? testVideo.getId() : 2, "test_user");
        Flowable.fromPublisher(videoPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

    @Test
    @Order(7)
    public void testDislikeVideo() {
        Publisher<DislikeDTO> videoPublisher = client.dislikeVideo(testVideo.getId(), "test_user");
        Flowable.fromPublisher(videoPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

    @Test
    @Order(8)
    public void deleteVideo() {
        Publisher<Void> videoPublisher = client.deleteVideo(Integer.MAX_VALUE);
        Flowable.fromPublisher(videoPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }
}
