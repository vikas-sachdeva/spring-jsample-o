package spring.jsample;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.jsample.bean.HelloWorld;

public class App {
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml")) {

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
