/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/27/24 : 12:17 PM
 */
package com.vidcraze;

import com.vidcraze.domain.Subscription;
import com.vidcraze.domain.Video;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.tracing.annotation.NewSpan;
import io.micronaut.tracing.annotation.SpanTag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Controller("subscriptions")
public class SmController {

    private final SmService smService;

    public SmController(SmService smService) {
        this.smService = smService;
    }

    @NewSpan
    @Get("subscribe/{hashTag}")
    public HttpResponse<Subscription> subscribe(
            @SpanTag("subscription.hashTag") @PathVariable String hashTag,
            @SpanTag("subscription.user") @QueryValue String user
    ) throws Exception {
        Subscription subscription = smService.subscribe(user, hashTag);
        return HttpResponse.ok(subscription);
    }

    @NewSpan
    @Get("unsubscribe/{id}")
    public void unsubscribe(@PathVariable Integer id) {
        smService.unsubscribe(id);
    }

    @NewSpan
    @Get("trending/{id}")
    public HttpResponse<List<HashMap<String, Object>>> getTopTrendingVideos(
            @SpanTag("subscription.id") @PathVariable Integer id
    ) throws Exception {
        List<HashMap<String, Object>> videos = new LinkedList<>();
        List<Video> _videos = smService.getTrendingVideosBySubscription(id);

        for (Video video : _videos) {
            HashMap<String, Object> _video = new HashMap<>();
            _video.put("id", video.getId());
            _video.put("title", video.getTitle());
            _video.put("user", video.getUser());
            videos.add(_video);
        }

        return HttpResponse.ok(videos);
    }

}
