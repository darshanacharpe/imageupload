package com.fileupload.dto;


public class VideoDto {
    private long id;
    private String videoPath;

    public VideoDto(long id, String videoPath) {
        this.id = id;
        this.videoPath = videoPath;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
