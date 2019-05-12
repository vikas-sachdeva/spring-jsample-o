package spring.jsample.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import spring.jsample.db.DbManager;
import spring.jsample.model.App;
import spring.jsample.model.Vm;

@SpringJUnitConfig
@SpringBootTest
public class CommandHandlerTest {

	@InjectMocks
	private CommandHandler handler;

	@SpyBean
	private DbManager dbManager;

	private App application1;

	private App application2;

	@BeforeEach
	private void initTest() {
		MockitoAnnotations.initMocks(this);

		application1 = new App();
		application1.setName("App-1");
		application1.setRunning(false);

		application2 = new App();
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
	}

	@Test
	public void executeTest1() throws Exception {

		Assertions.assertDoesNotThrow(() -> handler.execute(new String[] {}));

	}
}