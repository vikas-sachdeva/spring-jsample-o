package spring.jsample.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import spring.jsample.models.Student;

@SpringJUnitConfig
@SpringBootTest
public class StudentTest {

	@InjectMocks
	private Student student;

	@BeforeEach
	private void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void executeTest1() throws Exception {
		Assertions.assertDoesNotThrow(() -> student.displayId());
	}
}