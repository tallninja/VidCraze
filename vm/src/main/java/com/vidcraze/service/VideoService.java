/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:57 AM
 */
package com.vidcraze.service;

import com.vidcraze.domain.*;
import com.vidcraze.dtos.*;
import com.vidcraze.VideoClient;
import com.vidcraze.mapper.DislikeMapper;
import com.vidcraze.mapper.LikeMapper;
import com.vidcraze.mapper.VideoMapper;
import com.vidcraze.mapper.ViewMapper;
import com.vidcraze.repository.DislikeRepository;
import com.vidcraze.repository.LikeRepository;
import com.vidcraze.repository.VideoRepository;
import com.vidcraze.repository.ViewRepository;
import io.micronaut.tracing.annotation.ContinueSpan;
import io.micronaut.tracing.annotation.SpanTag;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Singleton
public class VideoService {

    private final VideoRepository videoRepository;
    private final ViewRepository viewRepository;
    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;
    private final VideoClient videoClient;
    private final  VideoMapper videoMapper;
    private final LikeMapper likeMapper;
    private  final DislikeMapper dislikeMapper;
    private final ViewMapper viewMapper;

    public VideoService(VideoRepository videoRepository, VideoMapper videoMapper, ViewRepository viewRepository, LikeRepository likeRepository, DislikeRepository dislikeRepository, VideoClient videoClient, LikeMapper likeMapper, DislikeMapper dislikeMapper, ViewMapper viewMapper) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
        this.viewRepository = viewRepository;
        this.likeRepository = likeRepository;
        this.dislikeRepository = dislikeRepository;
        this.videoClient = videoClient;
        this.likeMapper = likeMapper;
        this.dislikeMapper = dislikeMapper;
        this.viewMapper = viewMapper;
    }

    @ContinueSpan
    public List<VideoDTO> findAll() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream().map(videoMapper::toVideoDTO).collect(Collectors.toList());
    }

    @ContinueSpan
    public List<VideoDTO> findByHashTags(Set<HashTag> hashTags) {
        List<Video> videos = videoRepository.findVideosByHashTags(hashTags);
        return videos.stream().map(videoMapper::toVideoDTO).collect(Collectors.toList());
    }


    @ContinueSpan
    public VideoDTO findOne(@SpanTag("video.id") Integer id) throws Exception {
        Video video = findById(id);
        return videoMapper.toVideoDTO(video);
    }

    @ContinueSpan
    public  VideoDTO post(PostVideoDTO postVideoDTO) {
        Video video = videoMapper.toVideo(postVideoDTO);
        Video newVideo = videoRepository.save(video);
        VideoDTO videoDTO = videoMapper.toVideoDTO(newVideo);
        videoClient.postVideo(newVideo.getId(), videoDTO);
        return videoDTO;
    }

    @ContinueSpan
    public VideoDTO update(@SpanTag("video.id") Integer id, PostVideoDTO postVideoDTO) throws Exception {
        Video video = findById(id);
        video.setTitle(postVideoDTO.getTitle());
        video.setUser(postVideoDTO.getUser());
        Video updatedVideo = videoRepository.update(video);
        return videoMapper.toVideoDTO(updatedVideo);
    }

    @ContinueSpan
    public void delete(@SpanTag("video.id") Integer id) {
        videoRepository.deleteById(id);
    }

    @ContinueSpan
    public void likeVideo(String user, @SpanTag("video.id") Integer videoId) throws Exception {
        Video video = findById(videoId);

        Optional<Dislike> existingDisLike = dislikeRepository.findByUserAndVideo(user, video);
        existingDisLike.ifPresent(dislikeRepository::delete);

        Optional<Like> existingLike = likeRepository.findByUserAndVideo(user, video);
        if (existingLike.isEmpty()) {
            Like like = likeRepository.save(
                    Like.builder()
                            .user(user)
                            .video(video
                            ).build()
            );
            LikeDTO likeDTO = likeMapper.toLikeDto(like);
            log.info("Like event: " + likeDTO);
            videoClient.likeVideo(like.getId(), likeDTO);
            log.info("User " + like.getUser() + " liked video " + video.getId() + ".");
        }
    }

    @ContinueSpan
    public void dislikeVideo(String user, @SpanTag("video.id") Integer videoId) throws Exception {
        Video video = findById(videoId);

        Optional<Like> existingLike = likeRepository.findByUserAndVideo(user, video);
        existingLike.ifPresent(likeRepository::delete);

        Optional<Dislike> existingDislike = dislikeRepository.findByUserAndVideo(user, video);
        if (existingDislike.isEmpty()) {
            Dislike dislike = dislikeRepository.save(
                    Dislike.builder()
                            .user(user)
                            .video(video
                            ).build()
            );
            DislikeDTO dislikeDTO = dislikeMapper.toDislikeDTO(dislike);
            log.info("Dislike event: " + dislikeDTO);
            videoClient.dislikeVideo(dislikeDTO.getId(), dislikeDTO);
            log.info("User " + dislike.getUser() + " disliked video " + video.getId() + ".");
        }
    }

    @ContinueSpan
    public void viewVideo(String user, @SpanTag("video.id") Integer videoId) throws Exception {
        Video video = findById(videoId);
        Optional<View> existingView = viewRepository.findByUserAndVideo(user, video);
        if (existingView.isEmpty()) {
            View view = viewRepository.save(
                    View.builder()
                            .user(user)
                            .video(video
                            ).build()
            );
            ViewDTO viewDTO = viewMapper.toViewDTO(view);
            log.info("View event: " + viewDTO);
            videoClient.viewVideo(view.getId(), viewDTO);
            log.info("User " + view.getUser() + " viewed video " + video.getId() + ".");
        }
    }

    private Video findById(@SpanTag("video.id") Integer id) throws Exception {
        String message = String.format("Video with id %d was not found.", id);
        return videoRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error(message);
                     return new Exception(message);
                });
    }
}
