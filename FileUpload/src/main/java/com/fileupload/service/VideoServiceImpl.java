package com.fileupload.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fileupload.entity.Video;
import com.fileupload.repo.VideoRepository;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Override
    @Transactional
    public Video create(Video video) {
        return videoRepository.save(video);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Video> viewAll() {
        return (List<Video>) videoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Video viewById(long id) {
        return videoRepository.findById(id).orElse(null);
    }
}
