package spring.jsample.dao;

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

import spring.jsample.exceptions.ApplicationInsertionException;
import spring.jsample.exceptions.ApplicationNotFoundException;
import spring.jsample.mapper.AppMapper;
import spring.jsample.model.Application;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
public class AppDaoTest {

	@MockBean
	private AppMapper appMapper;

	@InjectMocks
	private AppDao dao;

	@BeforeEach
	private void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	private static List<Application> applicationList;

	@BeforeAll
	private static void init() {
		Application app1 = new Application(1, "Application-1", true);
		Application app2 = new Application(2, "Application-2", false);
		Application app3 = new Application(3, "Application-3", true);

		applicationList = new ArrayList<>(Arrays.asList(app1, app2, app3));
	}

	@Test
	public void addAppTest1() {

		Application app4 = new Application(4, "Application-4", true);

		Mockito.when(appMapper.insertApp(app4)).thenReturn(1);
		Mockito.when(appMapper.getApps()).thenReturn(applicationList);

		int maxId = dao.getApps().stream().max((o1, o2) -> {
			return o1.getId() > o2.getId() ? 1 : -1;
		}).get().getId();

		int id = dao.addApp(app4);

		Assertions.assertEquals(maxId + 1, id);

	}

	@Test
	public void addAppTest2() {

		Application app4 = new Application(4, "Application-4", true);

		Mockito.when(appMapper.insertApp(app4)).thenReturn(0);

		Assertions.assertThrows(ApplicationInsertionException.class, () -> dao.addApp(app4));

	}

	@Test
	public void deleteAppTest1() {

		int id = 2;

		Mockito.when(appMapper.deleteApp(id)).thenReturn(1);

		Mockito.when(appMapper.getAppById(id)).thenReturn(null);

		dao.deleteApp(id);
		Assertions.assertNull(dao.getAppById(id));

	}
	
	@Test
	public void deleteAppTest2() {

		int id = 2;

		Mockito.when(appMapper.deleteApp(id)).thenReturn(0);

		Assertions.assertThrows(ApplicationNotFoundException.class, () -> dao.deleteApp(id));

	}

	@Test
	public void updateAppTest1() {

		int id = 1;

		Application app1 = new Application(id, "Application-14", true);

		Mockito.when(appMapper.updateApp(app1)).thenReturn(1);
		Mockito.when(appMapper.getAppById(id)).thenReturn(app1);

		dao.updateApp(app1);
		Assertions.assertEquals(dao.getAppById(id).getName(), app1.getName());

	}
	
	@Test
	public void updateAppTest2() {

		int id = 1;

		Application app1 = new Application(id, "Application-14", true);

		Mockito.when(appMapper.updateApp(app1)).thenReturn(0);

		Assertions.assertThrows(ApplicationNotFoundException.class, () -> dao.updateApp(app1));

	}
}
