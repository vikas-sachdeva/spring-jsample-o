package spring.jsample.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import spring.jsample.beans.CommandHandler;
import spring.jsample.factory.beans.HelloWorldFactory;

@Configuration
public class CommandLineRunnerImpl implements CommandLineRunner {

	@Autowired
	private CommandHandler commandHandler;

	@Resource(name = "&helloWorld")
	private HelloWorldFactory helloWorldFactory;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Getting helloWorld Object " + helloWorldFactory.getObject());

		commandHandler.execute(args);
	}
}
