package com.fileupload.service;


import org.springframework.stereotype.Service;
import com.fileupload.entity.Video;
import java.util.List;

@Service
public interface VideoService {
    Video create(Video video);
    List<Video> viewAll();
    Video viewById(long id);
}
