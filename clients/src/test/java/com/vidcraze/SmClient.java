/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 11:07 PM
 */
package com.vidcraze;

import com.vidcraze.dtos.SmVideoDTO;
import com.vidcraze.dtos.SubscriptionDTO;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import org.reactivestreams.Publisher;

import java.util.List;

@Client("http://localhost:8083")
@Header(name = HttpHeaders.ACCEPT, value = "application/json")
public interface SmClient {

    @Get("/subscriptions/subscribe/{hashTag}")
    Publisher<SubscriptionDTO> subscribe(@PathVariable String hashTag, @QueryValue String user);

    @Get("/subscriptions/unsubscribe/{id}")
    Publisher<Void> unsubscribe(@PathVariable Integer id);

    @Get("subscriptions/trending/{id}")
    Publisher<List<SmVideoDTO>> getTrendingVideos(@PathVariable Integer id);


}
