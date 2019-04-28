package spring.jsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.jsample.bean.HelloWorld;

@Component
public class BeanOps {

	@Autowired
	private HelloWorld helloWorld1;

	@Autowired
	private HelloWorld helloWorld2;

	public void compareBeans() {
		if (helloWorld1 == helloWorld2) {
			System.out.println("Objects with same id are same.");
		} else {
			System.out.println("Objects with same id are different.");
		}
	}

}
