/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 2:53 AM
 */
package com.vidcraze;

import com.vidcraze.dtos.PostVideoDTO;
import com.vidcraze.dtos.VideoDTO;
import com.vidcraze.service.VideoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import io.micronaut.tracing.annotation.ContinueSpan;
import io.micronaut.tracing.annotation.NewSpan;
import io.micronaut.tracing.annotation.SpanTag;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.List;

@Slf4j
@Controller("/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @NewSpan
    @Get
    public HttpResponse<List<VideoDTO>> getAllVideos() {
        return HttpResponse.ok(videoService.findAll());
    }

    @NewSpan
    @Get("{id}")
    public HttpResponse<VideoDTO> getVideoById(@SpanTag("video.id") @PathVariable Integer id) {
        try {
            return HttpResponse.ok(videoService.findOne(id));
        } catch (Exception e) {
            return HttpResponse.serverError();
        }
    }

    @NewSpan
    @Post
    public HttpResponse<VideoDTO> postVideo(@Body PostVideoDTO videoDTO, HttpRequest<?> request) {
        URI location = request.getUri();
        return HttpResponse.created(videoService.post(videoDTO), location);
    }

    @NewSpan
    @Put("{id}")
    public HttpResponse<VideoDTO> editVideo(@SpanTag("video.id") @PathVariable Integer id, @Body PostVideoDTO videoDTO) {
        try {
            return HttpResponse.ok(videoService.update(id, videoDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            return HttpResponse.serverError();
        }
    }

    @NewSpan
    @Delete("{id}")
    public HttpResponse<Void> deleteVideo(@PathVariable Integer id) {
        videoService.delete(id);
        return HttpResponse.noContent();
    }

    @NewSpan
    @Get("/like/{id}")
    public HttpResponse<Void> likeVideo(@SpanTag("video.id") @PathVariable Integer id, @QueryValue String user) {
        try {
            videoService.likeVideo(user, id);
            return HttpResponse.noContent();
        } catch (Exception e) {
            log.error(e.getMessage());
            return HttpResponse.serverError();
        }
    }

    @NewSpan
    @Get("/dislike/{id}")
    public HttpResponse<Void> disLikeVideo(@SpanTag("video.id") @PathVariable Integer id, @QueryValue String user) {
        try {
            videoService.dislikeVideo(user, id);
            return HttpResponse.noContent();
        } catch (Exception e) {
            log.error(e.getMessage());
            return HttpResponse.serverError();
        }
    }

    @NewSpan
    @Get("/view/{id}")
    public HttpResponse<Void> viewVideo(@SpanTag("video.id") @PathVariable Integer id, @QueryValue String user) {
        try {
            videoService.viewVideo(user, id);
            return HttpResponse.noContent();
        } catch (Exception e) {
            log.error(e.getMessage());
            return HttpResponse.serverError();
        }
    }
}
