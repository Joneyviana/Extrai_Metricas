package umlmaster2.metrics;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class Classe {
private String Name;
public int DIT ;
private int NOC ;
private int CBO ;
private static ArrayList<Element> elementos = new ArrayList<>();
private Element element ; 
public Classe(Element ele) {
	   element = ele ; 
	   elementos.add(ele) ;
		// TODO Auto-generated constructor stub
	}
public int  getNOC() {
	String id = element.select("[xmi:type=\"uml:Generalization\"]").attr("general");
    
}

public int getCBO(){
	Elements elementos_cbo = element.select("ownedAttribute[association]");
	for (Element ele  :elementos_cbo) {
		
		Classe classe = new Classe(ele); 
		
	}
		return CBO + element.select("ownedAttribute[association]").size();
}

public String getName() {
	return element.attr("name");
}



}
