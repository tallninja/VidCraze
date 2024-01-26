/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:57 AM
 */
package com.vidcraze.service;

import com.vidcraze.domain.*;
import com.vidcraze.dtos.PostVideoDTO;
import com.vidcraze.dtos.VideoDTO;
import com.vidcraze.mapper.VideoMapper;
import com.vidcraze.repository.DislikeRepository;
import com.vidcraze.repository.LikeRepository;
import com.vidcraze.repository.VideoRepository;
import com.vidcraze.repository.ViewRepository;
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
    private final  VideoMapper videoMapper;
    private final ViewRepository viewRepository;
    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;

    public VideoService(VideoRepository videoRepository, VideoMapper videoMapper, ViewRepository viewRepository, LikeRepository likeRepository, DislikeRepository dislikeRepository) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
        this.viewRepository = viewRepository;
        this.likeRepository = likeRepository;
        this.dislikeRepository = dislikeRepository;
    }

    public List<VideoDTO> findAll() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream().map(videoMapper::toVideoDTO).collect(Collectors.toList());
    }

    public List<VideoDTO> findByHashTags(Set<HashTag> hashTags) {
        List<Video> videos = videoRepository.findVideosByHashTags(hashTags);
        return videos.stream().map(videoMapper::toVideoDTO).collect(Collectors.toList());
    }


    public VideoDTO findOne(Integer id) throws Exception {
        Video video = findById(id);
        return videoMapper.toVideoDTO(video);
    }

    public  VideoDTO post(PostVideoDTO postVideoDTO) {
        Video video = videoMapper.toVideo(postVideoDTO);
        Video newVideo = videoRepository.save(video);
        return videoMapper.toVideoDTO(newVideo);
    }

    public VideoDTO update(Integer id, PostVideoDTO postVideoDTO) throws Exception {
        Video video = findById(id);
        video.setTitle(postVideoDTO.getTitle());
        video.setUser(postVideoDTO.getUser());
        Video updatedVideo = videoRepository.update(video);
        return videoMapper.toVideoDTO(updatedVideo);
    }

    public void delete(Integer id) {
        videoRepository.deleteById(id);
    }

    public void likeVideo(String user, Integer videoId) throws Exception {
        Video video = findById(videoId);

        Optional<Dislike> existingDisLike = dislikeRepository.findByUserAndVideo(user, video);
        existingDisLike.ifPresent(dislikeRepository::delete);

        Optional<Like> existingLike = likeRepository.findByUserAndVideo(user, video);
        if (existingLike.isEmpty()) likeRepository.save(
                Like.builder()
                        .user(user)
                        .video(video
                        ).build()
        );
    }

    public void dislikeVideo(String user, Integer videoId) throws Exception {
        Video video = findById(videoId);

        Optional<Like> existingLike = likeRepository.findByUserAndVideo(user, video);
        existingLike.ifPresent(likeRepository::delete);

        Optional<Dislike> existingDislike = dislikeRepository.findByUserAndVideo(user, video);
        if (existingDislike.isEmpty()) dislikeRepository.save(
                Dislike.builder()
                        .user(user)
                        .video(video
                        ).build()
        );
    }

    public void viewVideo(String user, Integer videoId) throws Exception {
        Video video = findById(videoId);
        Optional<View> existingView = viewRepository.findByUserAndVideo(user, video);
        if (existingView.isEmpty()) viewRepository.save(
                View.builder()
                        .user(user)
                        .video(video
                        ).build()
        );
    }

    private Video findById(Integer id) throws Exception {
        String message = String.format("Video with id %d was not found.", id);
        return videoRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error(message);
                     return new Exception(message);
                });
    }
}
