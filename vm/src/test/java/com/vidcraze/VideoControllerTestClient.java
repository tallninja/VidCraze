/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/28/24 : 4:01 PM
 */
package com.vidcraze;

import com.vidcraze.dtos.PostVideoDTO;
import com.vidcraze.dtos.VideoDTO;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import org.reactivestreams.Publisher;

import java.util.List;

@Client("/")
@Header(name = HttpHeaders.ACCEPT, value = "application/json")
public interface VideoControllerTestClient {

    @Get("/videos")
    Publisher<List<VideoDTO>> getVideos();

    @Get("/videos/{id}")
    Publisher<VideoDTO> getVideo(@PathVariable Integer id);

    @Post("/videos")
    Publisher<VideoDTO> postVideo(@Body PostVideoDTO video);

    @Put("/videos/{id}")
    Publisher<VideoDTO> editVideo(@Body PostVideoDTO video, @PathVariable Integer id);

    @Delete
    Publisher<Void> deleteVideo(@PathVariable Integer id);

}
