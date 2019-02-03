package spring.jsample.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.jsample.models.Application;
import spring.jsample.models.Application.StatusEnum;
import spring.jsample.service.AppService;
import spring.jsample.util.AppConstants;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
public class AppControllerTest {

	@MockBean
	private AppService service;

	@Autowired
	private MockMvc mockMvc;

	private static List<Application> applicationList;

	@BeforeAll
	private static void init() {
		Application app1 = new Application();
		app1.setId(1);
		app1.setName("Application-1");
		app1.setStatus(StatusEnum.RUNNING);
		
		Application app2 = new Application();
		app2.setId(2);
		app2.setName("Application-2");
		app2.setStatus(StatusEnum.STOPPED);
		
		Application app3 = new Application();
		app3.setId(3);
		app3.setName("Application-3");
		app3.setStatus(StatusEnum.RUNNING);
		

		applicationList = new ArrayList<>(Arrays.asList(app2, app2, app3));
	}

	@Test
	public void getAppsTest1() throws Exception {

		Mockito.when(service.getApps()).thenReturn(applicationList);

		ObjectMapper mapper = new ObjectMapper();
		String expectedResponse = mapper.writeValueAsString(applicationList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(AppConstants.URI.GET_APPS)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(expectedResponse, true));
	}

	@Test
	public void addAppTest1() throws Exception {
		Application app4 = new Application();
		app4.setId(4);
		app4.setName("Application-4");
		app4.setStatus(StatusEnum.RUNNING);

		Mockito.when(service.addApp(Mockito.any(Application.class))).thenReturn(app4);

		ObjectMapper mapper = new ObjectMapper();
		String jsonApp = mapper.writeValueAsString(app4);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(AppConstants.URI.ADD_APP)
				.accept(MediaType.APPLICATION_JSON).content(jsonApp).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonApp, true));
	}

	@Test
	public void updateAppTest1() throws Exception {
		Application app5 = new Application();
		app5.setId(5);
		app5.setName("Application-5");
		app5.setStatus(StatusEnum.RUNNING);

		Mockito.when(service.updateApp(Mockito.any(Application.class))).thenReturn(app5);

		ObjectMapper mapper = new ObjectMapper();
		String jsonApp = mapper.writeValueAsString(app5);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(AppConstants.URI.UPDATE_APP, 5)
				.accept(MediaType.APPLICATION_JSON).content(jsonApp).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonApp, true));
	}

	@Test
	public void deleteAppTest1() throws Exception {

		Mockito.doNothing().when(service).deleteApp(Mockito.anyInt());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(AppConstants.URI.DELETE_APP, 5);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isNoContent());

	}

}
