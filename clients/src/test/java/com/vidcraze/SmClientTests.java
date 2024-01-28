/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 11:24 PM
 */
package com.vidcraze;

import com.vidcraze.dtos.SmVideoDTO;
import com.vidcraze.dtos.SubscriptionDTO;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;

import java.util.List;

@MicronautTest
public class SmClientTests {

    @Inject
    private SmClient client;

    @Test
    @Order(1)
    public void testSubscribe() {
        Publisher<SubscriptionDTO> subscriptionPublisher = client.subscribe("documentary", "test_user");
        Flowable.fromPublisher(subscriptionPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

    @Test
    @Order(2)
    public void testGetTrendingVideos() {
        Publisher<SubscriptionDTO> subscriptionPublisher = client.subscribe("documentary", "test_user2");
        Flowable.fromPublisher(subscriptionPublisher)
                .doOnNext(sub -> {
                    Publisher<List<SmVideoDTO>> getTrendingVideosPublisher = client
                            .getTrendingVideos(sub.getId());
                    Flowable.fromPublisher(getTrendingVideosPublisher)
                            .doOnNext(System.out::println)
                            .blockingSubscribe();
                })
                .blockingSubscribe();

    }

    @Test
    @Order(3)
    public void testUnsubscribe() {
        Publisher<Void> subscriptionPublisher = client.unsubscribe(Integer.MAX_VALUE);
        Flowable.fromPublisher(subscriptionPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

}
