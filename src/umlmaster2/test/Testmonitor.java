package umlmaster2.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import umlmaster2.monitor.*;
import static org.junit.Assert.*;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.internal.Converter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Testmonitor {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testfilewatcher() throws URISyntaxException, IOException {
		URL path = Platform.getBundle("UMLMaster2").getBundleContext().getBundle().getEntry("build.properties");
		URL url = new URL("platform:/plugin/UMLMaster2/files/notation.txt");
		//URL url = new URL("platform:/plugin/UMLMaster2/umlmaster2/monitor/notation");
		InputStream inputStream = url.openConnection().getInputStream();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
	    String inputLine;
		//assertEquals(convertfiles.convertforstring(filenotation.Returnfile()),"ccc");
	    assertEquals(new File(convertfiles.convertforstring(filenotation.Returnfile())).exists(),true);
		assertEquals(filenotation.Returnfile().getName(),"notation.txt" );
	    assertEquals(new File(FileLocator.resolve(url).toURI()).exists(),true);
	    assertEquals(new File(FileLocator.resolve(url).toURI()).getName() ,"notation.txt" );
	    assertNotEquals( path, null);
		assertEquals(new File(FileLocator.resolve(path).toURI()).exists(), true);
		assertEquals(new File("notation").getName(),"notation");
	    
	}

}
