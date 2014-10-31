package aprendizado;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class Classe {
private String Name;
public int DIT ;
private int NOC ;
private int CBO ;
private static HashMap<Element,Classe> elementos_classe = new HashMap<>();
private Element element ; 
private Classe(Element ele) {
	   element = ele ; 
	   elementos_classe.put(element, this);
		// TODO Auto-generated constructor stub
	}
public int  getNOC() {
	String id = element.select("[xmi:type=\"uml:Generalization\"]").attr("general");
    return 5 ;
}
public static Classe getInstanceNotEqualOther(Element ele){
	if (elementos_classe.containsKey(ele)){
	    return elementos_classe.get(ele);
	}
	else   
	    return new Classe(ele);
	
}
public int getCBO(){
	Elements elementos_cbo = element.select("ownedAttribute[association]");
	
	for (Element ele  :elementos_cbo) {
	
		Classe classe = Classe.getInstanceNotEqualOther(ele); 
		classe.CBO ++ ;
	}
		return CBO + element.select("ownedAttribute[association]").size();
}

public String getName() {
	return element.attr("name");
}



}
