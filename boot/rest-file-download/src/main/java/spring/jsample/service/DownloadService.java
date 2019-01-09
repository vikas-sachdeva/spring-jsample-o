package spring.jsample.service;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class DownloadService {

	private static final String FILE_PATH = "file:G:\\temp\\file.jpg";

	public Resource getFileAsResource() throws MalformedURLException {
		Resource resource = new UrlResource(FILE_PATH);
		return resource;
	}
}
