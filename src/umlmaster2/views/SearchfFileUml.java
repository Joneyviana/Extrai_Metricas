package umlmaster2.views;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import umlmaster2.monitor.*;
import umlmaster2.wizards.SampleNewWizard;
public class SearchfFileUml {
 
public URI SearchfFile() throws URISyntaxException{
	String IntoNotation = searchfileintonotation();
	if (IntoNotation==null){
		if (searchfileinDefaultUmLPath()==null)
		    return null;
		else
		return searchfileinDefaultUmLPath().getURIFileUML();
	}
	else{
		return new URI(IntoNotation) ;
	}
}

private SampleNewWizard searchfileinDefaultUmLPath() {
	 return (SampleNewWizard) new CreateInstanceWatcher().ConsulteInstance("SampleNewWizard");
	
}

private String searchfileintonotation() {
	String str = new File("notation").toString().trim();
	if( str.isEmpty()){
		return null ;
	}
	else{
		return str;
	}
	// TODO Auto-generated method stub
	
}
}
