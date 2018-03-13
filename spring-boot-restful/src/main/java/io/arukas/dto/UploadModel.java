package io.arukas.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadModel {

	private String extraField;

    private MultipartFile[] files;
	
}
