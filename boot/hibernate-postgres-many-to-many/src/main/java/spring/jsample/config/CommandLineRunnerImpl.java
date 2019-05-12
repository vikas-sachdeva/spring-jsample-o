package spring.jsample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import spring.jsample.beans.CommandHandler;

@Configuration
public class CommandLineRunnerImpl implements CommandLineRunner {

	@Autowired
	private CommandHandler commandHandler;

	@Override
	public void run(String... args) throws Exception {
		commandHandler.execute(args);
	}
}
