package spring.jsample.service;

import java.net.MalformedURLException;

import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import spring.jsample.util.AppConstants;

@Service
public class DownloadService {

	public Resource getFileAsResource() throws MalformedURLException {
		Resource resource = new FileUrlResource(AppConstants.FILE_PATH);
		return resource;
	}
}
