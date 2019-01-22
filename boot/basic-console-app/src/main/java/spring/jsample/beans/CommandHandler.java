package spring.jsample.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {

	@Autowired
	private HelloWorld helloWorld;

	public void execute(String... args) throws Exception {
		helloWorld.printHello();
	}
}