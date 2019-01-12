package spring.jsample.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.jsample.model.Application;
import spring.jsample.util.AppConstants;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
public class ControllerExceptionHandlerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void addAppTest1() throws Exception {

		String jsonContent = "{ "
				+ "\"id\": \"dd\","
				+ "\"name\": \"Application-11\","
				+ "\"status\": \"stopped\""
				+ "}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(AppConstants.URI.ADD_APP)
				.accept(MediaType.APPLICATION_JSON).content(jsonContent).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void updateAppTest1() throws Exception {
		Application app5 = new Application(5, "Application-5", "running");

		ObjectMapper mapper = new ObjectMapper();
		String jsonApp = mapper.writeValueAsString(app5);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(AppConstants.URI.UPDATE_APP, 5)
				.accept(MediaType.APPLICATION_JSON).content(jsonApp).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
