package umlmaster2.test;

import static org.junit.Assert.*;
import umlmaster2.metrics.*;
import umlmaster2.monitor.SimpleReadFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDIT {
	private SimpleReadFile simple;
	private DIT DIT;
	@Before
	public void setUp() throws Exception {
    simple = new SimpleReadFile("/home/joney/workspace1/hhhhhhhhh/model.uml");
	DIT = new DIT(simple.getText());
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testnanes() {
		assertEquals(DIT.name.get(0) , "doida");
	}

}
