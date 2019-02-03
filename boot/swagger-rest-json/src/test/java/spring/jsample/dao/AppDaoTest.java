package spring.jsample.dao;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import spring.jsample.dao.model.ApplicationDao;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
public class AppDaoTest {

	@InjectMocks
	private AppDao dao;

	@Test
	public void addAppTest1() {
		dao.init();

		ApplicationDao app4 = new ApplicationDao(4, "Application-4", "running");

		int maxId = dao.getApps().stream().max((o1, o2) -> {
			return o1.getId() > o2.getId() ? 1 : -1;
		}).get().getId();

		int id = dao.addApp(app4);

		Assertions.assertEquals(maxId + 1, id);

	}

	@Test
	public void deleteAppTest1() {
		dao.init();

		int id = 2;

		dao.deleteApp(id);
		Assertions.assertThrows(NoSuchElementException.class, () -> dao.getAppById(id));

	}

	@Test
	public void updateAppTest1() {
		dao.init();
		
		int id = 1;

		ApplicationDao app1 = new ApplicationDao(id, "Application-14", "running");

		dao.updateApp(app1);
		Assertions.assertEquals(dao.getAppById(id).getName(), app1.getName());

	}
}
