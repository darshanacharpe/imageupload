package com.fileupload.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fileupload.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}
