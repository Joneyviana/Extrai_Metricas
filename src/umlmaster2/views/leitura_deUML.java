package umlmaster2.views;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import umlmaster2.metrics.Classe;

public class leitura_deUML {
public leitura_deUML() throws IOException{
	File input = new File("/home/joney/Downloads/model.uml");
	Document doc = Jsoup.parse(input , "UTF-8");
	Elements classes = doc.select("packagedElement[xmi:type=\"uml:Class\"]");
	for (Element classe  : classes) {
	Classe cla = new Classe(classe);
	
   System.out.print("\t"+ cla.getNOC());
   System.out.print("\t"+ cla.getCBO());
	}	
}
}
