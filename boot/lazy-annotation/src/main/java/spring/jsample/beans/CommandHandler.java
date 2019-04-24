package spring.jsample.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
	
	public CommandHandler() {
		System.out.println("CommandHandler Constructor");
	}

	@Autowired
	@Lazy
	private HelloWorld helloWorld;

	public void execute(String... args) throws Exception {
		System.out.println("CommandHandler execute() method");
		helloWorld.printHello();
	}
}