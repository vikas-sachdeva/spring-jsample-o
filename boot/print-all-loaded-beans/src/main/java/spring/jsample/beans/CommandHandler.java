package spring.jsample.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {

	@Autowired
	private HelloWorld helloWorld;

	@Autowired
	private ApplicationContext context;

	public void execute(String... args) throws Exception {
		helloWorld.printHello();
		printAllLoadedBeans();
	}

	private void printAllLoadedBeans() {
		String[] beanNames = context.getBeanDefinitionNames();
		System.out.println("\nName of all loaded beans -\n");
		for (String name : beanNames) {
			System.out.println(name);
		}
	}
}