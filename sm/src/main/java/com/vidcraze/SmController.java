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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Controller("subscriptions")
public class SmController {

    private final SmService smService;

    public SmController(SmService smService) {
        this.smService = smService;
    }

    @Get("subscribe")
    public HttpResponse<Subscription> subscribe(
            @QueryValue String user,
            @QueryValue String hashtag
    ) throws Exception {
        Subscription subscription = smService.subscribe(user, hashtag);
        return HttpResponse.ok(subscription);
    }

    @Get("unsubscribe/{id}")
    public void unsubscribe(@PathVariable Integer id) {
        smService.unsubscribe(id);
    }

    @Get("trending/{id}")
    public HttpResponse<List<HashMap<String, Object>>> getTopTrendingVideos(@PathVariable Integer id) throws Exception {
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
