package spring.jsample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/spring.xml")
public class AppTest {

	@Test
	public void mainTest1() {
		App.main(new String[] {});

	}
}
