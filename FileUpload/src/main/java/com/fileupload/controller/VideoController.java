package com.fileupload.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.dto.VideoDto;
import com.fileupload.entity.Video;
import com.fileupload.service.VideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    // Display video
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> displayVideo(@PathVariable long id) {
        Video video = videoService.viewById(id);
        if (video == null || video.getVideo() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(video.getType())).body(video.getVideo());
    }

    // View all videos
    @GetMapping("/")
    public List<VideoDto> getAllVideos() {
        List<Video> videos = videoService.viewAll();
        List<VideoDto> videoDtos = new ArrayList<>();
        for (Video video : videos) {
            VideoDto dto = new VideoDto(video.getVideoId(), video.getVideoPath());
            videoDtos.add(dto);
        }
        return videoDtos;
    }

    // Add video
    @PostMapping("/add")
    public ResponseEntity<String> addVideo(@RequestParam("video") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        Video video = new Video();
        video.setVideo(bytes);
        video.setType(file.getContentType());

        // Save the video to the database
        Video savedVideo = videoService.create(video);

        // Set the path after saving, as the ID is now generated
        savedVideo.setVideoPath("/api/videos/" + savedVideo.getVideoId());

        // Update the video path in the database
        videoService.create(savedVideo);

        return ResponseEntity.ok(savedVideo.getVideoPath());
    }
    
    // Add multiple videos
    @PostMapping("/multipleadd")
    public ResponseEntity<List<String>> addVideos(@RequestParam("videos") MultipartFile[] files) throws IOException {
        List<String> videoPaths = new ArrayList<>();

        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();

            Video video = new Video();
            video.setVideo(bytes);
            video.setType(file.getContentType());

            // Save the video to the database
            Video savedVideo = videoService.create(video);

            // Set the path after saving, as the ID is now generated
            savedVideo.setVideoPath("/api/videos/" + savedVideo.getVideoId());

            // Update the video path in the database
            videoService.create(savedVideo);

            videoPaths.add(savedVideo.getVideoPath());
        }

        return ResponseEntity.ok(videoPaths);
    }
}
