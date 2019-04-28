package spring.jsample.bean;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
