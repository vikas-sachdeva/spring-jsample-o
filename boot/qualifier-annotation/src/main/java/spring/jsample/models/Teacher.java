package spring.jsample.models;

import org.springframework.stereotype.Component;

@Component
public class Teacher implements Person {

	@Override
	public void displayId() {
		System.out.println("Teacher Id");
	}
}
