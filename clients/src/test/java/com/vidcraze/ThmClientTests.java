/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/29/24 : 12:37 AM
 */
package com.vidcraze;

import com.vidcraze.dtos.ThmHashTagDTO;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;

@MicronautTest
public class ThmClientTests {

    @Inject
    private ThmClient client;

    @Test
    public void testGetTrendingHashTags() {
        Publisher<ThmHashTagDTO> hashTagPublisher = client.getTrendingHashTags();
        Flowable.fromPublisher(hashTagPublisher)
                .doOnNext(System.out::println)
                .blockingSubscribe();
    }

}
