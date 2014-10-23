package umlmaster2.views;

import java.io.BufferedReader;
import java.io.IOException;

public class Diff {

public Diff(BufferedReader buf ,String [] str) throws IOException{
	String diferenca ="";
	String[] auxiliar=new String[]{""};
	int count = 0;
	for (String s:str){
		
		if (auxiliar!=null)
			auxiliar[count] = buf.readLine();
		count = count + 1 ;
		}
	} 
}


