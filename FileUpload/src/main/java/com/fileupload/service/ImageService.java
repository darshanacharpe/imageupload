package com.fileupload.service;

import org.springframework.stereotype.Service;
import com.fileupload.entity.Image;
import java.util.List;

@Service
public interface ImageService {
    Image create(Image image);
    List<Image> viewAll();
    Image viewById(long id);
}
