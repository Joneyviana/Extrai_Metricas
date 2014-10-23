package umlmaster2.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import umlmaster2.metrics.MetricsXMI;

public class TestMetricsXMI {
	

	private MetricsXMI metrics;

	@Before
	public void setUp() throws Exception {
	    metrics = new MetricsXMI("<UML>fghgfgfgfg<tag>dffddfd</tag><UML>");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSearchTag() {
	    Matcher matcher = metrics.SearchTag("tag",metrics.getContext());
	   
	    assertEquals(matcher.group(),"<tag>dffddfd</tag>" );
	}

    @Test 
    public void testSearchonSearch(){
	 metrics.searchs = new String[]{"UML","tag"};
     assertEquals(metrics.testSearchonSearch(), "<tag>dffddfd</tag>");
    
    }
    @Test
    public void testSearchAttr(){
    	assertEquals(metrics.SearchAttr("name", "<packagedElement xmi:type='uml:Class' xmi:id='_LmsjkDRXEeS6Tf-V6iWM5Q' name='doida'/>"),"doida");
   }
}
