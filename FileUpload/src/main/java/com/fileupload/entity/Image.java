package com.fileupload.entity;

import java.sql.Blob;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "image_table")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long imageId;

	@Lob
	private Blob image;
	private String imagePath;
	private String type;
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	private Date date = new Date();

	   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date postedAt = new Date(); // Use a separate field for posted date/time
	   
	public long getId() {
		return imageId;
	}

	public void setId(long id) {
		this.imageId = id;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getPath() {
		return imagePath;
	}

	public void setPath(String path) {
		this.imagePath = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return postedAt;
	}
}

