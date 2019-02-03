package spring.jsample.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import spring.jsample.dao.AppDao;
import spring.jsample.dao.model.ApplicationDao;
import spring.jsample.dto.ApplicationDaoApplicationDto;
import spring.jsample.models.Application;
import spring.jsample.models.Application.StatusEnum;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
public class AppServiceTest {

	@MockBean
	private AppDao dao;

	@InjectMocks
	private AppService service;
	
	@MockBean
	private ApplicationDaoApplicationDto dto;

	private static List<ApplicationDao> applicationDaoList;
	
	private static List<Application> applicationList;

	@BeforeAll
	private static void init() {
		ApplicationDao appDao1 = new ApplicationDao(1, "Application-1", "running");
		ApplicationDao appDao2 = new ApplicationDao(2, "Application-2", "stopped");
		ApplicationDao appDao3 = new ApplicationDao(3, "Application-3", "running");

		applicationDaoList = new ArrayList<>(Arrays.asList(appDao1, appDao2, appDao3));
	
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
		

		applicationList = new ArrayList<>(Arrays.asList(app1, app2, app3));

	}

	@BeforeEach
	private void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAppsTest1() {

		Mockito.when(dao.getApps()).thenReturn(applicationDaoList);
		Mockito.when(dto.convertApplicationDaoToApplication(applicationDaoList)).thenReturn(applicationList);
		List<Application> appList = service.getApps();
		Assertions.assertArrayEquals(applicationList.toArray(), appList.toArray());
	}

	@Test
	public void addAppTest1() {
		ApplicationDao appDao4 = new ApplicationDao(4, "Application-4", "running");
		Application app4 = new Application();
		app4.setId(4);
		app4.setName("Application-4");
		app4.setStatus(StatusEnum.RUNNING);
		Mockito.when(dao.addApp(appDao4)).thenReturn(4);
		Mockito.when(dao.getAppById(4)).thenReturn(appDao4);
		
		Mockito.when(dto.convertApplicationDaoToApplication(appDao4)).thenReturn(app4);
		Mockito.when(dto.convertApplicationToApplicationDao(app4)).thenReturn(appDao4);

		Application acutalApp = service.addApp(app4);
		Assertions.assertEquals(app4, acutalApp);
	}

	@Test
	public void deleteAppTest1() {
		Mockito.doNothing().when(dao).deleteApp(4);

		service.deleteApp(4);

	}

	@Test
	public void updateAppTest1() {
		ApplicationDao appDao4 = new ApplicationDao(4, "Application-4", "running");
		Application app4 = new Application();
		app4.setId(4);
		app4.setName("Application-4");
		app4.setStatus(StatusEnum.RUNNING);
		Mockito.doNothing().when(dao).updateApp(appDao4);

		Mockito.when(dao.getAppById(4)).thenReturn(appDao4);

		Mockito.when(dto.convertApplicationDaoToApplication(appDao4)).thenReturn(app4);
		Mockito.when(dto.convertApplicationToApplicationDao(app4)).thenReturn(appDao4);
		
		Application acutalApp = service.updateApp(app4);
		Assertions.assertEquals(app4, acutalApp);

	}
}
