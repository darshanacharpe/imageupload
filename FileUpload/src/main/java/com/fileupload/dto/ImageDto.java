package com.fileupload.dto;


public class ImageDto {
    private long id;
    private String imagePath;

    public ImageDto(long id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
