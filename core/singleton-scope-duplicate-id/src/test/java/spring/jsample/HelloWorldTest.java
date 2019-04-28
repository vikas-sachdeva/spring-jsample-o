package spring.jsample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import spring.jsample.bean.HelloWorld;

@SpringJUnitConfig(locations = "/spring.xml")
public class HelloWorldTest {

	private static ApplicationContext ctx;

	@BeforeAll
	public static void init(ApplicationContext ctx) {
		HelloWorldTest.ctx = ctx;
	}

	@Test
	public void getNameTest1() {
		String name = "test";
		HelloWorld hello = (HelloWorld) ctx.getBean("scopeTest");
		hello.setName(name);
		Assertions.assertEquals(name, hello.getName());
	}
	
	@Test
	public void getNameTest2() {
		String name = "test";
		HelloWorld hello = (HelloWorld) ctx.getBean("scopeTestDuplicate");
		hello.setName(name);
		Assertions.assertEquals(name, hello.getName());
	}
}
