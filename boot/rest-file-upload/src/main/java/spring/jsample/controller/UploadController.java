package spring.jsample.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.jsample.service.UploadService;
import spring.jsample.util.AppConstants;

@RestController
public class UploadController {

	/**
	 * 
	 * Curl command for testing this API -
	 * 
	 * curl -v -F "file=@myFile.PNG" -F "fileDescription=file for testing"
	 * http://localhost:8080/upload/
	 * 
	 */

	@Autowired
	private UploadService uploadService;

	@PostMapping(value = AppConstants.Uri.UPLOAD_URI)
	@ResponseBody
	public ResponseEntity<?> fileUpload(@RequestParam(value = AppConstants.RequestParams.FILE) MultipartFile file,
			@RequestParam(value = AppConstants.RequestParams.DESCRIPTION) String fileDescription) throws IOException {
		uploadService.store(file);
		return new ResponseEntity<>(String.format(AppConstants.Msg.SUCCESS, fileDescription), HttpStatus.OK);
	}
}