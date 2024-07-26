//package com.fileupload.entity;
//
//import java.sql.Blob;
//import java.util.Date;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Lob;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "video_table")
//public class Video {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long videoId;
//
//    @Lob
//    private Blob video;
//    private String videoPath;
//    private String type;
//
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    private Date postedAt = new Date(); // Use a separate field for posted date/time
//	   
//    public long getId() {
//        return videoId;
//    }
//
//    public void setId(long id) {
//        this.videoId = id;
//    }
//
//    public Blob getVideo() {
//        return video;
//    }
//
//    public void setVideo(Blob video) {
//        this.video = video;
//    }
//
//    public String getPath() {
//        return videoPath;
//    }
//
//    public void setPath(String path) {
//        this.videoPath = path;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public Date getDate() {
//        return postedAt;
//    }
//}

package com.fileupload.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "video_table")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long videoId;

    @Lob
    private byte[] video;
    private String videoPath;
    private String type;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date postedAt = new Date(); // Use a separate field for posted date/time

    // Getters and setters
    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getPostedAt() {
        return postedAt;
    }
}

