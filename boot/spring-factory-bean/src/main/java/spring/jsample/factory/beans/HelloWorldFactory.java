package spring.jsample.factory.beans;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import spring.jsample.beans.HelloWorld;

public class HelloWorldFactory extends AbstractFactoryBean<HelloWorld> {

	@Override
	public Class<?> getObjectType() {
		return HelloWorld.class;
	}

	@Override
	protected HelloWorld createInstance() throws Exception {
		System.out.println("Creating new instance of HelloWorld");
		return new HelloWorld();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
