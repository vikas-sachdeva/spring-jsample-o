package spring.jsample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import spring.jsample.bean.HelloWorld;
import spring.jsample.config.SpringConfiguration;

@SpringJUnitConfig(classes = SpringConfiguration.class)
public class BeanOpsTest {

	@Autowired
	private BeanOps beanOps;

	@Mock
	private HelloWorld helloWorld;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void compareBeansTest() {
		Mockito.when(helloWorld.getName()).thenReturn("hello");
	
		beanOps.compareBeans();
	}

}
