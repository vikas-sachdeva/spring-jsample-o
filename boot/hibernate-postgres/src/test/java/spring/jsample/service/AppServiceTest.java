package spring.jsample.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import spring.jsample.exceptions.ApplicationNotFoundException;
import spring.jsample.exceptions.ConcurrentModificationException;
import spring.jsample.model.Application;
import spring.jsample.repo.AppDao;

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
		Application app1 = new Application(1L, "Application-1", true);
		Application app2 = new Application(2L, "Application-2", false);
		Application app3 = new Application(3L, "Application-3", true);

		applicationList = new ArrayList<>(Arrays.asList(app1, app2, app3));
	}

	@BeforeEach
	private void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAppsTest1() {

		Mockito.when(dao.findAll()).thenReturn(applicationList);

		List<Application> appList = service.getApps();
		Assertions.assertArrayEquals(applicationList.toArray(), appList.toArray());
	}

	@Test
	public void addAppTest1() {
		Application app4 = new Application(4L, "Application-4", true);
		Mockito.when(dao.save(app4)).thenReturn(app4);

		Application acutalApp = service.addApp(app4);
		Assertions.assertEquals(app4, acutalApp);
	}

	@Test
	public void deleteAppTest1() {
		Mockito.doNothing().when(dao).deleteById(4L);

		service.deleteApp(4L);

	}

	@Test
	public void updateAppTest1() {
		Application app4 = new Application(4L, "Application-4", true);
		app4.setLastModifiedDate(new Date());
		Mockito.when(dao.save(app4)).thenReturn(app4);

		Mockito.when(dao.findById(4L)).thenReturn(Optional.of(app4));

		Application acutalApp = service.updateApp(app4, 4L);
		Assertions.assertEquals(app4, acutalApp);

	}
	
	@Test
	public void updateAppTest2() {
		
		Application app4 = new Application(4L, "Application-4", true);
		app4.setLastModifiedDate(new Date());
		Mockito.when(dao.save(app4)).thenReturn(app4);
		
		Application dbApp = new Application(5L, "Application-4", true);
		dbApp.setLastModifiedDate(new Date(39393939L));
		
		Mockito.when(dao.save(dbApp)).thenReturn(dbApp);
		
		Optional<Application> dbOptional = Optional.of(dbApp);
		
		Mockito.when(dao.findById(4L)).thenReturn(dbOptional);
			
		Assertions.assertThrows(ConcurrentModificationException.class, () -> service.updateApp(app4, 4L));
	}
	
	@Test
	public void updateAppTest3() {
		Application app4 = new Application(4L, "Application-4", true);
		app4.setLastModifiedDate(new Date());
		Mockito.when(dao.save(app4)).thenReturn(app4);

		Mockito.when(dao.findById(4L)).thenReturn(Optional.empty());

		Assertions.assertThrows(ApplicationNotFoundException.class, () -> service.updateApp(app4, 4L));
	}
	

}
