package umlmaster2.metrics;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.osgi.framework.internal.core.SystemBundleActivator;

public class MetricsXMI {

protected	Pattern pattern ;
protected String context;
public String[] searchs;
protected Matcher searchTag;


public Matcher SearchTag(String tags , String context,String filtro){
	if (filtro.isEmpty()){
	pattern = Pattern.compile("(<"+ tags +"([^<>]+)?>(?<text>"+filtro+".+?)?)?</?"+ tags + "/?\\s*>",0);
	}
	else {
		pattern = Pattern.compile("(<"+ tags +"([^<>]+)?>(?<text>"+filtro+".+?))</?"+ tags + "/?\\s*>",0);
	}
	return buildmatcher(pattern, context);
	
	
}

public  Matcher  SearchAttr(String find, String context) {
	pattern = Pattern.compile(find+"\\s*=\\s*.([^\\s/>]*).(?:/>|\\s)");
	
	return buildmatcher(pattern, context);
	
}
public String getContext() {
	return context;
}
public Matcher buildmatcher(Pattern pattern ,String context){
	Matcher matcher = pattern.matcher(context) ;
	
	return matcher;
	
}
}
