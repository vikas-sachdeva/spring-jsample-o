package spring.jsample.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import spring.jsample.dao.model.ApplicationDao;
import spring.jsample.models.Application;
import spring.jsample.models.Application.StatusEnum;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
public class ApplicationDaoApplicationDtoTest {

	@InjectMocks
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

	@Test
	public void convertApplicationDaoToApplicationTest1() {

		Assertions.assertArrayEquals(applicationList.toArray(),
				dto.convertApplicationDaoToApplication(applicationDaoList).toArray());
	}

	@Test
	public void convertApplicationDaoToApplicationTest2() {
		Assertions.assertEquals(applicationList.get(0),
				dto.convertApplicationDaoToApplication(applicationDaoList.get(0)));
	}

	@Test
	public void convertApplicationToApplicationDaoTest1() {
		Assertions.assertArrayEquals(applicationDaoList.toArray(),
				dto.convertApplicationToApplicationDao(applicationList).toArray());
	}

	@Test
	public void convertApplicationToApplicationDaoTest2() {
		Assertions.assertEquals(applicationDaoList.get(0),
				dto.convertApplicationToApplicationDao(applicationList.get(0)));
	}

}
