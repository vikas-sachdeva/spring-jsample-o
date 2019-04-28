package spring.jsample.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@SpringBootTest
public class CommandHandlerTest {

	@MockBean
	private HelloWorld helloWorld;

	@InjectMocks
	private CommandHandler handler;
	
	@BeforeEach
	private void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void executeTest1() throws Exception {

		Mockito.doNothing().when(helloWorld).printHello();
		Assertions.assertDoesNotThrow(() -> handler.execute(new String[] {}));
	}
}