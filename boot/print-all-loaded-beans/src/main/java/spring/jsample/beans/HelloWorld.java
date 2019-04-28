package spring.jsample.beans;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

	public void printHello() {
		System.out.println("Hello Console App");
	}
}
