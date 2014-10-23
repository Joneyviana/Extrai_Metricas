package umlmaster2.monitor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;

public class filenotation {

public static File Returnfile(){
	
	try {
    	URL url = new URL("platform:/plugin/UMLMaster2/files/notation.txt");
    	File file = new File(FileLocator.resolve(url).toURI());
	    return file ;
	} catch (URISyntaxException | IOException e) {
		// TODO Auto-generated catch block
		System.console().writer().print("arquivo não encontrado");
    
}
 return null;
}
}