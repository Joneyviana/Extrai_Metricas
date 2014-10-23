package umlmaster2.metrics.dinamics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.*;

import umlmaster2.metrics.Exibidor;

public class exibidorhtml implements Exibidor{
  private HTMLDocument document ;
  public FileWriter arquivo ;
public exibidorhtml(String path) throws IOException {
	path = path.substring(0, path.length()-5)+"total_de_mensagens.html";            
    arquivo = new FileWriter(path);
    arquivo.write("<html><head></head><style>td{ text-align: center;} table {margin: 0 auto;}table, th, td {border: 1px solid black;}</style><body><table><th>name</th><th>ocorrencia</th>");
    
    
}  
 public void montartable(DefaultTableModel model) {
      for(int m=0;m<model.getRowCount();m++){ 
        try {
			arquivo.write("<tr><td>"+model.getValueAt(m, 0)+"</td><td>"+model.getValueAt(m, 1)+"</td></tr>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    try {
		arquivo.write("</table></body></html>");
		 arquivo.close();
    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
   
 }
@Override
public void enviarRegistro(String key, int Value) {
	// TODO Auto-generated method stub
	
}
@Override
public void atualizarRegistro(String Name) {
	// TODO Auto-generated method stub
	
}
 }



 