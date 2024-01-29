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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;

import java.util.List;

@MicronautTest
public class SmClientTests {

    @Inject
    private SmClient client;

    private SubscriptionDTO testSubscription;

    @BeforeEach
    public void createTestSubscription() {
        Publisher<SubscriptionDTO> subscriptionPublisher = client.subscribe("documentary", "test_user");
        Flowable.fromPublisher(subscriptionPublisher)
                .doOnNext((sub) -> testSubscription = sub)
                .blockingSubscribe();
    }

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
        Publisher<List<SmVideoDTO>> trendingPublisher = client.getTrendingVideos(testSubscription.getId());
        Flowable.fromPublisher(trendingPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();

    }

    @Test
    @Order(3)
    public void testUnsubscribe() {
        Publisher<Void> subscriptionPublisher = client.unsubscribe(testSubscription.getId());
        Flowable.fromPublisher(subscriptionPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

}
