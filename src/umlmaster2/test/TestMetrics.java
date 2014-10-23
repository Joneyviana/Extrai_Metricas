package umlmaster2.test;

import static org.junit.Assert.*;

import java.util.TimerTask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import umlmaster2.metrics.Exibidor;
import umlmaster2.metrics.ExibidorTable;
import umlmaster2.metrics.Metrics;

public class TestMetrics {

	private Metrics metric;
	private Exibidor TimerTask;

	@Before
	public void setUp() throws Exception {
	 metric = new Metrics();
	 TimerTask = new ExibidorTable();
	 metric.addExibidor(TimerTask); 
	
	}

	@After
	public void tearDown() throws Exception {
	}


	    

  @Test
  public void testsetnamefirtstime(){
	 
	  metric.setName("sdd");
	  assertEquals(metric.frequencia_de_names.get("sdd").intValue(),1);
  }
  @Test
  public void testsetnameSameagain(){
	  metric.setName("sdd");
	  metric.setName("sdd");
	  assertEquals(metric.frequencia_de_names.get("sdd").intValue(),2 );
  }
} 
