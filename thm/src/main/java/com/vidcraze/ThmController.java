/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 7:41 PM
 */
package com.vidcraze;

import com.vidcraze.domain.HashTag;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.tracing.annotation.NewSpan;

import java.util.List;

@Controller("trending")
public class ThmController {

    private final HashTagRepository hashTagRepository;

    public ThmController(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    @NewSpan
    @Get("/hashtags")
    public HttpResponse<List<HashTag>> getTrendingHashTags() {
        return HttpResponse.ok(hashTagRepository.findTopTenTrendingHashTags());
    }

}
