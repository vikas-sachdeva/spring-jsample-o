package spring.jsample.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import spring.jsample.util.AppConstants;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
class UploadServiceTest {

	@InjectMocks
	private UploadService service;

	@BeforeEach
	private void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void storeTest1() throws Exception {

		MockMultipartFile multipartFile = new MockMultipartFile(AppConstants.RequestParams.FILE, "testFile.txt",
				"text/plain", "Test File Upload".getBytes());

		Assertions.assertDoesNotThrow(() -> service.store(multipartFile));

	}
}