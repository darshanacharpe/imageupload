package com.fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fileupload.entity.Image;
import com.fileupload.repo.ImageRepository;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    @Transactional
    public Image create(Image image) {
        return imageRepository.save(image);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Image> viewAll() {
        return (List<Image>) imageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Image viewById(long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
