package spring.jsample.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class HelloWorld {

	public HelloWorld() {
		System.out.println("HelloWorld Constructor");
	}

	public void printHello() {
		System.out.println("Hello Console App");
	}
}
