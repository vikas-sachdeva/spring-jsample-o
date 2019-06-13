package spring.jsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.jsample.beans.HelloWorld;
import spring.jsample.factory.beans.HelloWorldFactory;

@Configuration
public class FactoryConfig {

	@Bean(name = "helloWorld")
	public HelloWorldFactory helloWorldFactory() {
		HelloWorldFactory factory = new HelloWorldFactory();
		return factory;
	}

	@Bean
	public HelloWorld helloWorld() throws Exception {
		return helloWorldFactory().getObject();
	}
}
