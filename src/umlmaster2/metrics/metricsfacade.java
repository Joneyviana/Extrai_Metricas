package umlmaster2.metrics;

import java.util.ArrayList;
import java.lang.reflect.*; 

import umlmaster2.metrics.dinamics.ParserXMIbase;

public class metricsfacade {

public String[] metricas ;
public metricsfacade (String [] metricas){
	this.metricas = metricas;
}
public void Chamar_Metricas(){
	for (int i = 0; i < metricas.length; i++) {
		try {
			Class classe = Class.forName(metricas[i]);
			 Class partypes[] = new Class[1];
			 partypes[0] = MetricsXMI.class;
			 Constructor ct = classe.getConstructor(partypes); 
		     //ParserXMIbase parser = new ParserXMIbase() ;
			 //MetricsXMI metrica = (MetricsXMI) ct.newInstance(parser);
		     //parser.disparevento();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

}
