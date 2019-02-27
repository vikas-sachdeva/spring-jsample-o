package spring.jsample.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import spring.jsample.service.UploadService;
import spring.jsample.util.AppConstants;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
class UploadControllerTest {

	@MockBean
	private UploadService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void fileUploadTest1() throws Exception {

		MockMultipartFile multipartFile = new MockMultipartFile(AppConstants.RequestParams.FILE,
				"Test File Upload".getBytes());

		Mockito.doNothing().when(service).store((multipartFile));

		String fileDesription = "test file";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart(AppConstants.Uri.UPLOAD_URI)
				.file(multipartFile).param(AppConstants.RequestParams.DESCRIPTION, fileDesription);

		String expectedMsg = String.format(AppConstants.Msg.SUCCESS, fileDesription);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(expectedMsg));

	}
}