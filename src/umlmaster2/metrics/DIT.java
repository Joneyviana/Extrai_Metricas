package umlmaster2.metrics;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class DIT {



public DIT(ArrayList <Classe> classes){
	
	for (Classe classe  : classes) {
		 
		String  id = classe.select("[xmi:type=\"uml:Generalization\"]").attr("general");
		
		
 		
		 for (Classe classe1 : classes) {
			classe1.DIT = classe1.select("[xmi:id:" +id+"]").size();
		    
		}
		

	}

}

}
