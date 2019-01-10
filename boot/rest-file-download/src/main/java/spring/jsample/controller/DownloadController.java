package spring.jsample.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.jsample.service.DownloadService;
import spring.jsample.util.AppConstants;

@RestController
public class DownloadController {

	@Autowired
	private DownloadService service;

	@GetMapping(AppConstants.URI.EXPORT)
	public ResponseEntity<Resource> exporFile(HttpServletRequest request) {

		Resource resource;
		try {
			resource = service.getFileAsResource();
			String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

			// Fallback to the default content type if type could not be determined
			if (contentType == null) {
				contentType = "application/octet-stream";
			}

			MultiValueMap<String, String> responseHeaders = new HttpHeaders();
			responseHeaders.put(HttpHeaders.CONTENT_TYPE, Arrays.asList(contentType));
			responseHeaders.put(HttpHeaders.CONTENT_DISPOSITION,
					Arrays.asList("attachment; filename=\"" + resource.getFilename() + "\""));

			return new ResponseEntity<Resource>(resource, responseHeaders, HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
