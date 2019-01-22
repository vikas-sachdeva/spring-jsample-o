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
import spring.jsample.model.Application;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
public class AppServiceTest {

	@MockBean
	private AppDao dao;

	@InjectMocks
	private AppService service;

	private static List<Application> applicationList;

	@BeforeAll
	private static void init() {
		Application app1 = new Application(1, "Application-1", "running");
		Application app2 = new Application(2, "Application-2", "stopped");
		Application app3 = new Application(3, "Application-3", "running");

		applicationList = new ArrayList<>(Arrays.asList(app1, app2, app3));
	}

	@BeforeEach
	private void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAppsTest1() {

		Mockito.when(dao.getApps()).thenReturn(applicationList);

		List<Application> appList = service.getApps();
		Assertions.assertArrayEquals(applicationList.toArray(), appList.toArray());
	}

	@Test
	public void addAppTest1() {
		Application app4 = new Application(4, "Application-4", "running");
		Mockito.when(dao.addApp(app4)).thenReturn(4);
		Mockito.when(dao.getAppById(4)).thenReturn(app4);

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
		Application app4 = new Application(4, "Application-4", "running");
		Mockito.doNothing().when(dao).updateApp(app4);

		Mockito.when(dao.getAppById(4)).thenReturn(app4);

		Application acutalApp = service.updateApp(app4);
		Assertions.assertEquals(app4, acutalApp);

	}

	public Application updateApp(Application app) {
		dao.updateApp(app);
		return dao.getAppById(app.getId());
	}
}
