package spring.jsample.service;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
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

	@BeforeEach
	private void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAppsTest1() {

		Application app1 = new Application("1", "Application-1", true);
		Application app2 = new Application("2", "Application-2", false);
		Application app3 = new Application("3", "Application-3", true);

		Mockito.when(dao.findAll()).thenReturn(Flux.just(app1, app2, app3));

		StepVerifier.create(service.getApps()).assertNext(x -> Assertions.assertEquals(app1, x))
				.assertNext(x -> Assertions.assertEquals(app2, x)).assertNext(x -> Assertions.assertEquals(app3, x))
				.verifyComplete();

	}

	@Test
	public void addAppTest1() {
		Application app4 = new Application("4", "Application-4", true);
		Mockito.when(dao.insert(app4)).thenReturn(Mono.just(app4));

		StepVerifier.create(service.addApp(app4)).assertNext(x -> Assertions.assertEquals(app4, x)).verifyComplete();

	}

	@Test
	public void deleteAppTest1() {

		Application app4 = new Application("4", "Application-4", true);

		Mockito.when(dao.findById("4")).thenReturn(Mono.just((app4)));

		Mockito.when(dao.deleteById("4")).thenReturn(Mono.empty());

		StepVerifier.create(service.deleteApp("4")).expectComplete().verify();

	}

	@Test
	public void deleteAppTest2() {

		Mockito.when(dao.findById("4")).thenReturn(Mono.empty());

		StepVerifier.create(service.deleteApp("4")).expectError(ApplicationNotFoundException.class).verify();

	}

	@Test
	public void updateAppTest1() {
		Application app4 = new Application("4", "Application-4", true);
		app4.setLastModifiedDate(new Date());

		Mockito.when(dao.save(app4)).thenReturn(Mono.just(app4));

		Mockito.when(dao.findById("4")).thenReturn(Mono.just(app4));

		StepVerifier.create(service.updateApp(app4, "4")).assertNext(x -> Assertions.assertEquals(app4, x))
				.verifyComplete();

	}

	@Test
	public void updateAppTest2() {

		Application app4 = new Application("4", "Application-4", true);
		app4.setLastModifiedDate(new Date());
		Mockito.when(dao.save(app4)).thenReturn(Mono.just(app4));

		Application dbApp = new Application("4", "Application-4", true);
		dbApp.setLastModifiedDate(new Date(39393939L));

		Mockito.when(dao.save(dbApp)).thenReturn(Mono.just(dbApp));

		Mockito.when(dao.findById("4")).thenReturn(Mono.just(dbApp));

		StepVerifier.create(service.updateApp(app4, "4")).expectError(ConcurrentModificationException.class).verify();

	}

	@Test
	public void updateAppTest3() {
		Application app4 = new Application("4", "Application-4", true);
		app4.setLastModifiedDate(new Date());
		Mockito.when(dao.save(app4)).thenReturn(Mono.just(app4));

		Mockito.when(dao.findById("4")).thenReturn(Mono.empty());

		StepVerifier.create(service.updateApp(app4, "4")).expectError(ApplicationNotFoundException.class).verify();

	}

}
