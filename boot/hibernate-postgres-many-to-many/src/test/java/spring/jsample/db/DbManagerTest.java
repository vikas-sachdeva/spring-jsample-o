package spring.jsample.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import spring.jsample.exceptions.ApplicationNotFoundException;
import spring.jsample.exceptions.ConcurrentModificationException;
import spring.jsample.model.App;
import spring.jsample.model.Vm;
import spring.jsample.repo.ApplicationDao;

@SpringJUnitConfig
@SpringBootTest
@Disabled
public class DbManagerTest {

	@MockBean
	private ApplicationDao dao;

	@InjectMocks
	private DbManager dbManager;

	private static List<App> apps = new ArrayList<>();

	@BeforeAll
	private static void init() {
		App application1 = new App();
		application1.setName("App-1");
		application1.setRunning(false);

		App application2 = new App();
		application2.setName("App-2");
		application2.setRunning(false);

		Vm vm1 = new Vm();
		vm1.setHostName("virtualmachine1.in");

		vm1.getApps().add(application1);

		Vm vm2 = new Vm();
		vm2.setHostName("virtualmachine2.in");
		vm2.getApps().add(application1);
		vm2.getApps().add(application2);

		Vm vm3 = new Vm();
		vm3.setHostName("virtualmachine3.in");
		vm3.getApps().add(application1);
		vm3.getApps().add(application2);

		Vm vm4 = new Vm();
		vm4.setHostName("virtualmachine4.in");
		vm4.getApps().add(application2);

		application1.getVMs().add(vm1);
		application1.getVMs().add(vm2);
		application1.getVMs().add(vm3);

		application2.getVMs().add(vm2);
		application2.getVMs().add(vm3);
		application2.getVMs().add(vm4);

		apps.add(application1);
		apps.add(application2);
	}

	@BeforeEach
	private void initTest() {
		MockitoAnnotations.initMocks(this);

	}

	@Test

	public void getAllApplicationsTest1() {

		Mockito.when(dao.findAll()).thenReturn(apps);

		List<App> appList = dbManager.getAllApplications();
		Assertions.assertArrayEquals(apps.toArray(), appList.toArray());
	}

	@Test

	public void insertApplicationTest1() {
		App app4 = new App();
		app4.setId(4L);
		app4.setName("App-4");
		app4.setRunning(false);

		Mockito.when(dao.save(app4)).thenReturn(app4);

		App acutalApp = dbManager.insertApplication(app4);
		Assertions.assertEquals(app4, acutalApp);
	}

	@Test

	public void deleteByIdTest1() {
		Mockito.doNothing().when(dao).deleteById(4L);

		dbManager.deleteApplicationById(4L);

	}

	@Test

	public void updateApplicationTest1() {
		App app4 = new App();
		app4.setId(4L);
		app4.setName("App-4");
		app4.setRunning(false);
		app4.setLastModifiedDate(new Date());
		Mockito.when(dao.save(app4)).thenReturn(app4);

		Mockito.when(dao.findById(4L)).thenReturn(Optional.of(app4));

		App acutalApp = dbManager.updateApplication(app4);
		Assertions.assertEquals(app4, acutalApp);

	}

	@Test

	public void updateApplicationTest2() {

		App app4 = new App();
		app4.setId(4L);
		app4.setName("App-4");
		app4.setRunning(false);

		app4.setLastModifiedDate(new Date());
		Mockito.when(dao.save(app4)).thenReturn(app4);

		App dbApp = new App();
		app4.setId(4L);
		dbApp.setName("App-4");
		dbApp.setRunning(false);
		dbApp.setLastModifiedDate(new Date(39393939L));

		Mockito.when(dao.save(dbApp)).thenReturn(dbApp);

		Optional<App> dbOptional = Optional.of(dbApp);

		Mockito.when(dao.findById(4L)).thenReturn(dbOptional);

		Assertions.assertThrows(ConcurrentModificationException.class, () -> dbManager.updateApplication(app4));
	}

	@Test

	public void updateApplicationTest3() {
		App app4 = new App();
		app4.setId(4L);
		app4.setName("App-4");
		app4.setRunning(false);

		app4.setLastModifiedDate(new Date());
		Mockito.when(dao.save(app4)).thenReturn(app4);

		Mockito.when(dao.findById(4L)).thenReturn(Optional.empty());

		Assertions.assertThrows(ApplicationNotFoundException.class, () -> dbManager.updateApplication(app4));
	}

}
