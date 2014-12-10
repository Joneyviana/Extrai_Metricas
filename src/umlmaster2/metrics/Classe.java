package umlmaster2.metrics;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class Classe {
private String id ;
private String Name;
private  int DIT = -1;
private int NOC = -1  ;
private int CBO = -1 ;
private static HashMap<Element,Classe> elementos_classe = new HashMap<>();
private Element element ; 
public Element getElement() {
	return element;
}

private Classe(Element ele) {
	   element = ele ; 
	   elementos_classe.put(element, this);
		// TODO Auto-generated constructor stub
	}
public int  getNOC() {
	if (NOC == -1)
		NOC = element.parent().select("generalization[general="+getId()+"]").size();
	return NOC ;
}
public static Classe getInstanceNotEqualOther(Element ele){
	if (elementos_classe.containsKey(ele)){
	    return elementos_classe.get(ele);
	}
	else   
	    return new Classe(ele);
	
}
public int getCBO(){
	if (CBO == -1)
	 CBO = element.parent().select("ownedAttribute[type="+getId()+"]").size()+element.select("ownedAttribute[association]").size();
	
	return  CBO;
}

public String getName() {
	return element.attr("name");
}
public String getId() {
	return element.attr("xmi:id") ;
}
public int getDIT() {
	
	
	return recursao_DIT(this.element) ;
 
}
private int recursao_DIT(Element element2) {
	
	String id = element2.select("[xmi:type=\"uml:Generalization\"]").attr("general");
	if (id.isEmpty()){
		return 0 ;
	}
	
	Elements pai = element.parent().select("packagedElement[xmi:id="+id+"]");
	return recursao_DIT(pai.get(0)) + 1;
}

}



