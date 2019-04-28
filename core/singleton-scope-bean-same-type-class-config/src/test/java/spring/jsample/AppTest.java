package spring.jsample;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import spring.jsample.config.SpringConfiguration;

@SpringJUnitConfig(classes = SpringConfiguration.class)
public class AppTest {

	@Test
	public void mainTest1() {
		App.main(new String[] {});

	}
}
