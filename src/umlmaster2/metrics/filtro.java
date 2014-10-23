package umlmaster2.metrics;

public class filtro {

private String text ;
public void setText(String text) {
	this.text = text;
}
public void setValue(String value) {
	this.value = value;
}
public void setAttr(String attr) {
	this.attr = attr+"\\s*=\\s*.([^\\s/>]*).(?:/>|\\s)";
}
public void setTag(String tag) {
	this.tag_inicial = "(<"+ tag;
    this.tag_final = tag + "?/?\\s*>";
}
private String value ="";
private String attr = "([^<>]+)?>?";

public String tag_inicial ;
public String tag_final ;
}
