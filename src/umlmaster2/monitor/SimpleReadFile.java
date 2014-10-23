package umlmaster2.monitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SimpleReadFile{
 private String text ="";	
 public SimpleReadFile(String str) throws IOException	{
	 File file = new File(str);
	 BufferedReader buf = new BufferedReader(new FileReader(file));
	 String linha ; 
	while ((linha = buf.readLine())!=null) {
		text = text + linha ;
		
		
	}
 }
public String getText() {
	return text;
}

}

