/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 4:01 PM
 */
package com.vidcraze;

import com.vidcraze.dtos.*;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import org.reactivestreams.Publisher;

import java.util.List;

@Client("http://localhost:8081")
@Header(name = HttpHeaders.ACCEPT, value = "application/json")
public interface VmClient {

    @Get("/videos")
    Publisher<List<VideoDTO>> getVideos();

    @Get("/videos/{id}")
    Publisher<VideoDTO> getVideo(@PathVariable Integer id);

    @Post("/videos")
    Publisher<VideoDTO> postVideo(@Body PostVideoDTO video);

    @Put("/videos/{id}")
    Publisher<VideoDTO> editVideo(@PathVariable Integer id, @Body PostVideoDTO video);

    @Delete("/videos/{id}")
    Publisher<Void> deleteVideo(@PathVariable Integer id);

    @Get("/videos/view/{id}")
    Publisher<ViewDTO> viewVideo(@PathVariable Integer id, @QueryValue String user);

    @Get("/videos/like/{id}")
    Publisher<LikeDTO> likeVideo(@PathVariable Integer id, @QueryValue String user);

    @Get("/videos/dislike/{id}")
    Publisher<DislikeDTO> dislikeVideo(@PathVariable Integer id, @QueryValue String user);

}
