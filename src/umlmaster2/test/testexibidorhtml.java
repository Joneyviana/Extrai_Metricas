package umlmaster2.test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import umlmaster2.metrics.dinamics.*;
public class testexibidorhtml {

	private String path;
	private exibidorhtml exibidor;

	@Before
	public void setUp() throws Exception {
		path = "/home/joney/autosalvar.xmi";
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("nome");
		model.addColumn("valor");
		model.addRow(new Object[]{"joao",3});
		model.addRow(new Object[]{"joao",3});
	
		exibidor = new exibidorhtml(path,model);
	    
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testcriararquivo() throws IOException {
		
		exibidor.arquivo.close();
	}

}
