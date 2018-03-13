package io.arukas.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.arukas.dto.UploadModel;

@RestController
@RequestMapping(value = "/api")
public class UploadController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static String UPLOADED_FOLDER = "D:/temp/";

	// 3.1.1 Single file upload
	@PostMapping(value = "/upload")
	public ResponseEntity<?> upload(@RequestParam("files") MultipartFile uploadFile) {

		if (logger.isInfoEnabled()) {
			logger.info("single file upload.");
		}

		if (uploadFile.isEmpty()) {
			return new ResponseEntity<>("please select a file", HttpStatus.OK);
		}
		String originalFilename = uploadFile.getOriginalFilename();
		Map<String, String> data = new HashMap<String, String>();
		try {
			
//			saveUploadedFiles(Arrays.asList(uploadFile));
			
			byte[] bytes = uploadFile.getBytes();
			String fileName = UUID.randomUUID().toString();
		
			//originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
			fileName = fileName + ext;
			Path path = Paths.get(UPLOADED_FOLDER, fileName);
			Files.write(path, bytes);
			data.put("原始文件名", originalFilename);
			data.put("最终文件名", fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<>(
				data, new HttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	// 3.1.2 Multiple file upload
    @PostMapping("/upload/multi")
    public ResponseEntity<?> uploadFileMulti(@RequestParam("extraField") String extraField, @RequestParam("files") MultipartFile[] uploadfiles){
        if (logger.isInfoEnabled()) {
            logger.info("Multiple file upload.");
        }
        Map<String, String> data = new HashMap<String, String>();
        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }
        if (logger.isInfoEnabled()) {
            logger.info("extraField: {}", extraField);
        }
        data.put("extraField", extraField);
        data.put("fiels", uploadedFileName);

        try {
            saveUploadedFiles(Arrays.asList(uploadfiles));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

	
    // 3.1.3 maps html form to a Model
    @PostMapping("/upload/multi/model")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) {
        if(logger.isInfoEnabled()){
            logger.info("Multiple file upload! With UploadModel");
        }

        if (logger.isInfoEnabled()){
            logger.info("extraField: {}", model.getExtraField());
        }

        try {
            saveUploadedFiles(Arrays.asList(model.getFiles()));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully uploaded!", HttpStatus.OK);
    }
    
	private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue;
			}

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

			Files.write(path, bytes);
		}
	}

}
