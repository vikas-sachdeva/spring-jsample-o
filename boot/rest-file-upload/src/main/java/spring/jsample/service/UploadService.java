package spring.jsample.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

	/* Directory should exist, else, it will throw exception */
	private static final String STORE_PATH = "./upload/";

	public void store(MultipartFile file) throws IOException {

		String filePath = STORE_PATH + file.getOriginalFilename() + "_" + System.currentTimeMillis();

		Files.write(Paths.get(filePath), file.getBytes(), StandardOpenOption.CREATE_NEW);
	}
}
