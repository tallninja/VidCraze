/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/29/24 : 12:35 AM
 */
package com.vidcraze;

import com.vidcraze.dtos.ThmHashTagDTO;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;
import org.reactivestreams.Publisher;

@Client("http://localhost:8082")
@Header(name = HttpHeaders.ACCEPT, value = "application/json")
public interface ThmClient {

    @Get("/trending/hashtags")
    Publisher<ThmHashTagDTO> getTrendingHashTags();

}
