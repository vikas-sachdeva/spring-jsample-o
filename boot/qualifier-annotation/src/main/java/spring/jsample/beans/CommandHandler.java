package spring.jsample.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import spring.jsample.models.Person;

@Component
public class CommandHandler {

	@Autowired
	@Qualifier("student")
	private Person p1;

	@Autowired
	@Qualifier("teacher")
	private Person p2;

	public void execute(String... args) throws Exception {
		p1.displayId();
		p2.displayId();
	}
}