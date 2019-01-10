package spring.jsample.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import spring.jsample.util.AppConstants;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
public class DownloadServiceTest {

	private static final String TEST_FILE = "G:\\temp\\Completed Docs.rar";

	@InjectMocks
	private DownloadService service;

	@BeforeEach
	public void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getFileAsResourceTest1() throws Exception {

		Resource notSameResource = new FileUrlResource(TEST_FILE);

		Assert.assertNotEquals(service.getFileAsResource(), notSameResource);
	}
	
	@Test
	public void getFileAsResourceTest2() throws Exception {

		Resource expectedResource = new FileUrlResource(AppConstants.FILE_PATH);

		Assert.assertEquals(service.getFileAsResource(), expectedResource);
	}
}