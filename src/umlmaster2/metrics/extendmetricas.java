package umlmaster2.metrics;

import java.util.HashMap;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class extendmetricas {
private Element element;
private Classe cla ;
private int NOO ;
private int heranca ;
private static HashMap<Classe,extendmetricas> metricas_classe = new HashMap<>();
public extendmetricas(Classe cla){
	this.cla = cla ;
    element = cla.getElement() ;
    recursao_superclasse(element);
    metricas_classe.put(cla, this);
}
private void recursao_superclasse(Element tag) {
	Elements elements = tag.select("[xmi:type=\"uml:Generalization\"]");
	for (Element elemento :elements){
		Element elemento_pai = element.parent().select("packagedElement[xmi:id="+elements.attr("general")+"]").get(0);
	    NOO += gettodosAtributosPropios(elemento_pai);
	    heranca += cla.getCBO();
	    recursao_superclasse(elemento_pai);
	}
}
public int getCBO() {
	return cla.getCBO();
}
public int getNOC(){
	return cla.getNOC();
}
public int getDIT(){
	return cla.getDIT() ;
}
public String getName() {
	return cla.getName();
}
public int getCS(){
	
	return gettodosAtributosPropios(element) +NOO;
}
public int getNOO(){
	
    return NOO ;
}
public int getNOA(){
	if (element.select("generalization").size()==0){
	  return 0 ;
	}
	  return getCS() - getNOO();
}
public int gettodosAtributosPropios(Element elemento){
	
	return elemento.select("ownedOperation").size() +element.select("ownedAttribute[visibility]").size();
}
}
