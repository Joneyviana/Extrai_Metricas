package umlmaster2.test;

import static org.junit.Assert.*;
import umlmaster2.monitor.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleFileTest {
    private SimpleReadFile simple;
	@Before
	public void setUp() throws Exception {
	 simple = new SimpleReadFile("/home/joney/workspace1/hhhhhhhhh/model.uml");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals(simple.getText().isEmpty(), false);
	}

}
