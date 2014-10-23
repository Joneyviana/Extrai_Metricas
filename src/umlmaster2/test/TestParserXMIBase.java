package umlmaster2.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import umlmaster2.metrics.dinamics.ParserXMIbase;
import umlmaster2.monitor.SimpleReadFile;

public class TestParserXMIBase {

	private SimpleReadFile simple;
	private ParserXMIbase base;

	@Before
	public void setUp() throws Exception {
		 simple = new SimpleReadFile("/home/joney/workspace1/hhhhhhhhh/model.uml");
	      base = new ParserXMIbase(simple.getText());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testjjj() {
	String [] name = new String []{"Class2","doida","peste","peste_doida_1","gggg","peste_gggg_1","ggg"};
	String [] ID = new String[]{"_ayoogDN2EeSV1pCP7PQcZg","_LmsjkDRXEeS6Tf-V6iWM5Q","_MyUpMDRXEeS6Tf-V6iWM5Q","_PCR5oDRXEeS6Tf-V6iWM5Q" ,"_wLIJ0DRXEeS6Tf-V6iWM5Q",
			"_xqXH8DRXEeS6Tf-V6iWM5Q" ,"_JaQ3EDTtEeSWK_KiO2j0mg"};
	
	int count = 0;
	for (String n :name) {   
	    System.out.print(base.getClasses().get(count).getName());
		assertEquals(base.getClasses().get(count).getName(),n ); 
		assertEquals(base.getClasses().get(count).ID,ID[count] ); 
		count = count + 1;
	}
}}
