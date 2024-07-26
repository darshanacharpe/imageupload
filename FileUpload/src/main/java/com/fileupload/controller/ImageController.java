package com.fileupload.controller;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

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

import com.fileupload.dto.ImageDto;
import com.fileupload.entity.Image;
import com.fileupload.service.ImageService;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

  
    // Display image
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> displayImage(@PathVariable long id) throws IOException, SQLException {
        Image image = imageService.viewById(id);
        if (image == null || image.getImage() == null) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getType())).body(imageBytes);
    }

    // View all images
    @GetMapping("/")
    public List<ImageDto> getAllImages() {
        List<Image> images = imageService.viewAll();
        List<ImageDto> imageDtos = new ArrayList<>();
        for (Image image : images) {
            ImageDto dto = new ImageDto(image.getId(), image.getPath());
            imageDtos.add(dto);
        }
        return imageDtos;
    }

    // Add image
    @PostMapping("/add")
    public ResponseEntity<String> addImage(@RequestParam("image") MultipartFile file) throws IOException, SerialException, SQLException {
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        Image image = new Image();
        image.setImage(blob);
        image.setType(file.getContentType());
        

        // Save the image to the database
        Image savedImage = imageService.create(image);

        // Set the path after saving, as the ID is now generated
        savedImage.setPath("/api/images/" + savedImage.getId());

        // Update the image path in the database
        imageService.create(savedImage);

        return ResponseEntity.ok(savedImage.getPath());
    }
    
  
    // Add multiple images
    @PostMapping("/multipleadd")
    public ResponseEntity<List<String>> addImages(@RequestParam("images") MultipartFile[] files) throws IOException, SerialException, SQLException {
        List<String> imagePaths = new ArrayList<>();

        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();
            Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

            Image image = new Image();
            image.setImage(blob);
            image.setType(file.getContentType());

            // Save the image to the database
            Image savedImage = imageService.create(image);

            // Set the path after saving, as the ID is now generated
            savedImage.setPath("/api/images/" + savedImage.getId());

            // Update the image path in the database
            imageService.create(savedImage);

            imagePaths.add(savedImage.getPath());
        }

        return ResponseEntity.ok(imagePaths);
    }
}