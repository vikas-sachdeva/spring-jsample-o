package spring.jsample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.jsample.bean.HelloWorld;
import spring.jsample.config.SpringConfiguration;

public class App {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)) {

			HelloWorld scope1 = (HelloWorld) ctx.getBean("scopeTest");
			HelloWorld scope2 = (HelloWorld) ctx.getBean("scopeTest");

			HelloWorld scopeDuplicate = (HelloWorld) ctx.getBean("scopeTestDuplicate");

			if (scope1 == scope2) {
				System.out.println("Objects with same id are same.");
			} else {
				System.out.println("Objects with same id are different.");
			}

			if (scope1 == scopeDuplicate) {
				System.out.println("Objects with different id are same.");
			} else {
				System.out.println("Objects with different id are different.");
			}

		}
	}
}
