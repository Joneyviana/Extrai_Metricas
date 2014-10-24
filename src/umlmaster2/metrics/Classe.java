package umlmaster2.metrics;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class Classe extends Element {

public Classe(Tag tag, String baseUri) {
		super(tag, baseUri);
		// TODO Auto-generated constructor stub
	}
public int getNOC() {
	return this.select("[xmi:type=\"uml:Generalization\"]").size();
}

public int getCBO() {
	return this.select("ownedAttribute[association]").size();
}


public int DIT ;
private int NOC ;
private int CBO ;
}
