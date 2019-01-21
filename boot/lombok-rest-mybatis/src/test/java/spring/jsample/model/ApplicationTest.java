package spring.jsample.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

	@Test
	public void equalsTest1() {
		Application app1 = new Application(1, "app-2", true);
		Application app2 = new Application(1, "app-2", true);

		Assertions.assertTrue(app1.equals(app2));
	}

	@Test
	public void equalsTest2() {
		Application app1 = new Application(1, "app-1", true);
		Application app2 = new Application(2, "app-2", false);

		Assertions.assertFalse(app1.equals(app2));
	}
	
	@Test
	public void equalsTest3() {
		Application app1 = new Application(1, "app-1", true);
		Assertions.assertTrue(app1.equals(app1));
	}
	
	@Test
	public void equalsTest4() {
		Application app1 = new Application(1, "app-1", true);
		Assertions.assertFalse(app1.equals(new Object()));
	}
	
	@Test
	public void equalsTest5() {
		Application app1 = new Application(1, null, true);
		Application app2 = new Application(2, "app-2", false);

		Assertions.assertFalse(app1.equals(app2));
	}
	
	
	@Test
	public void equalsTest6() {
		Application app1 = new Application(1, "app-1", null);
		Application app2 = new Application(2, "app-2", false);

		Assertions.assertFalse(app1.equals(app2));
	}

	@Test
	public void hashCodeTest1() {
		Application app1 = new Application(1, "app-2", true);
		Application app2 = new Application(1, "app-2", true);

		Assertions.assertEquals(app1.hashCode(), app2.hashCode());
	}

	@Test
	public void hashCodeTest2() {
		Application app1 = new Application(1, "app-1", true);
		Application app2 = new Application(2, "app-2", false);

		Assertions.assertNotEquals(app1.hashCode(), app2.hashCode());
	}

	@Test
	public void toStringTest() {
		Application app1 = new Application(1, "app-2", true);
		Application app2 = new Application(1, "app-2", true);

		Assertions.assertEquals(app1.toString(), app2.toString());
	}

}
