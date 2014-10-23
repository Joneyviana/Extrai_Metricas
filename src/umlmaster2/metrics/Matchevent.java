package umlmaster2.metrics;

import java.util.ArrayList;
import java.util.HashMap;

public class Matchevent extends java.util.EventObject{
    public Matchevent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public String conteudo ;
    public int repeticoes ;
	public ArrayList<Matchevent> referencia ;
	
}
