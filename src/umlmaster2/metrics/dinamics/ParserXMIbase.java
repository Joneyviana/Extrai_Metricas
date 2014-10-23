package umlmaster2.metrics.dinamics;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import umlmaster2.metrics.*;


public class ParserXMIbase  extends MetricsXMI{
      
	
	private Guia_deBusca guia  ;
	private Matchevent evento_raiz ;
	private Matchevent eventos_aux ;  
    private Matchevent evento_prox ;       
  
	public ParserXMIbase(String texto){
		evento_raiz = new Matchevent(null);
	    context = texto ;
	    eventos_aux = evento_raiz ;
	    evento_raiz.referencia = new ArrayList<>();
	}
	
	private Collection<MatchListener> matchListener = new ArrayList<MatchListener>();
   
	
	public void SearchFather(Guia_deBusca guia ,String filtro , String Context){
		System.out.print("tag1");  
		Matcher match ;
   	    boolean tag = false ;
		if (guia.attr.startsWith("tag:") ){
		   match = SearchTag(guia.attr, context,"");
		   tag = true ;
   	    }
   	    else {
   	    	match = SearchAttr(guia.attr, context);
  		  
   	    }
   	    
   	    while(match.find()!=false){
	    	  
	    	
	    	 for(int a=0;a<guia.Referencia.size();a++){
	    		 if (tag == true )
		    		  eventos_aux.conteudo = match.group();
		    	  else 
		    		  eventos_aux.conteudo = match.group(1); 
	    		 Matchevent eventos1 = new Matchevent(new Object());
	        	  eventos_aux.referencia.add(eventos1);
		    	  evento_prox= eventos_aux ;
	        	  eventos_aux = eventos1;
	              SearchFather(guia.Referencia.get(a),null, evento_prox.conteudo );   
	    	      eventos_aux = evento_prox ;
	    	 }
		           
	    			
	    		} 	
			
			 
	    	 
	      }
	       


    
     

	public void disparevento(){
		 Collection <MatchListener> matchlisteners;  

	     synchronized (this) {  

	    	 matchlisteners = (Collection)(((ArrayList)matchListener).clone());  

	     }  
	     for (MatchListener matchlistener : matchlisteners) {  
	    	 
	    	 matchlistener.matchfind(evento_raiz);  

	     } 
	}

	 public synchronized void addfilelistener(MatchListener m) {  
		  
	     if(!matchListener.contains(m)) {  

	    	 matchListener.add(m);  

	     }  


}

	
}
