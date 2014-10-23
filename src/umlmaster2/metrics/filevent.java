package umlmaster2.metrics;

import java.util.ArrayList;

public class filevent extends java.util.EventObject {
private static ArrayList<Exibidor> exibidores= new ArrayList<>();
	private String namefile;
	public void setNamefile(String namefile) {
		this.namefile = namefile;
	}
	public String getNamefile() {
		return namefile;
	}
	public filevent(String source) {
		
		super(source);
		namefile = source ;
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Exibidor> getExibidores() {
		return exibidores;
	}
	public static void addExibidor(Exibidor exi){
		exibidores.add(exi);
	}
}
