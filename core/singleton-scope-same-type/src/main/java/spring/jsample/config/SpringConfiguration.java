package spring.jsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.jsample.bean.HelloWorld;

@Configuration
public class SpringConfiguration {

	@Bean("scopeTest")
	public HelloWorld getScope() {
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.setName("Hello jsample");
		return helloWorld;
	}

	@Bean("scopeTestDuplicate")
	public HelloWorld getDuplicateScope() {
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.setName("Hello jsample");
		return helloWorld;
	}
}
