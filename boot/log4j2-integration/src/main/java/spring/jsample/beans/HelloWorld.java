package spring.jsample.beans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

	private static final Logger LOGGER = LogManager.getLogger(HelloWorld.class);

	public void printHello() {
		LOGGER.info("Hello Console App");
	}
}
