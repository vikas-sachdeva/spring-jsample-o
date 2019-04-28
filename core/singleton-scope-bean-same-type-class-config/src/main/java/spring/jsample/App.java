package spring.jsample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.jsample.config.SpringConfiguration;

public class App {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringConfiguration.class)) {

			BeanOps beanOps = ctx.getBean(BeanOps.class);
			beanOps.compareBeans();
		}
	}
}
