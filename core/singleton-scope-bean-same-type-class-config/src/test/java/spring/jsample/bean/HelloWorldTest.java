package spring.jsample.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import spring.jsample.config.SpringConfiguration;

@SpringJUnitConfig(classes = SpringConfiguration.class)
public class HelloWorldTest {

	@Autowired
	private HelloWorld hello;

	@Test
	public void getNameTest1() {
		String name = "test";

		hello.setName(name);
		Assertions.assertEquals(name, hello.getName());
	}

}
