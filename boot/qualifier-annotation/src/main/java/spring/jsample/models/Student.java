package spring.jsample.models;

import org.springframework.stereotype.Component;

@Component
public class Student implements Person {

	public void displayId() {
		System.out.println("Student Id");
	}
}
