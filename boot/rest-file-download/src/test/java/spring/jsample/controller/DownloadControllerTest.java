package spring.jsample.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import spring.jsample.service.DownloadService;
import spring.jsample.util.AppConstants;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
public class DownloadControllerTest {

	private static final String TEST_FILE = "G:\\temp\\Completed Docs.rar";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DownloadService service;

	@BeforeEach
	public void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void exporFileTest1() throws Exception {

		Resource resource = new FileUrlResource(TEST_FILE);

		Mockito.when(service.getFileAsResource()).thenReturn(resource);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(AppConstants.URI.EXPORT);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().bytes(Files.readAllBytes(Paths.get(TEST_FILE))));

	}
}