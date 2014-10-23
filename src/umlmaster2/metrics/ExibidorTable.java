package umlmaster2.metrics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import umlmaster2.metrics.dinamics.ParserXMIbase;
import umlmaster2.metrics.dinamics.exibidorhtml;

public class ExibidorTable extends JFrame implements Exibidor , ActionListener  {
	public static DefaultTableModel model;
	private static JTable table;
	public ArrayList<String> nomes = new ArrayList<String>();
	private String selectedFile;
	private ExibidorTable exi;
    private Collection <filelistener> filelisteners = new ArrayList<filelistener>();
	private ParserXMIbase parser ;
    public String getSelectedFile() {
		return selectedFile;
	}
	public ExibidorTable() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 600);
		 model = new DefaultTableModel(){
		    	public  boolean isCellEditable(int row, int col) {    
		    		
		    
		    		          return true ;
		    	}
		    };
		    JMenuBar menuBar = new JMenuBar();
		    setJMenuBar(menuBar);
		    JMenu fileMenu = new JMenu("File");
		    menuBar.add(fileMenu);
		    JMenuItem openAction = new JMenuItem("Open");
		    JMenuItem openandExportAction = new JMenuItem("Open and Export Html");
		    fileMenu.add(openAction);
            fileMenu.add(openandExportAction);
		    openAction.addActionListener(new ActionListener() {
				
				

				@Override
				public void actionPerformed(ActionEvent e) {
					    selectedFile = openDialog();
					    filevent evento = new filevent(selectedFile);
					    evento = addeventosaneexibidor(evento);
					    disparafileselected(evento);
					}
				
			});
		    openandExportAction.addActionListener(this);
		    JPanel panel = new JPanel();
		    model.addColumn("Classe");
		    model.addColumn("Ocorrencia");
		    
		    table = new JTable(model);
		    
		    table.getTableHeader().setPreferredSize(new Dimension(130 ,25));
		    table.setSize(600, 250);
		    table.getColumnModel().getColumn(0).setPreferredWidth(320);
		    DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		    centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		   
		    table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		    JScrollPane scrollpanel = new JScrollPane(table);
		   scrollpanel.setVisible(true);
		   panel.add(scrollpanel);
	        add(panel);
	}
	protected filevent addeventosaneexibidor(filevent evento) {
		evento.addExibidor(this);
		return evento ;
	}
	@Override
	public void enviarRegistro(String key, int Value) {
		
		
	}
 public void atualizarRegistro(String key ,int value) {
	 String key1="";
	 if (key.isEmpty()==false){
	      key = key.replace("<wbr />", "");
	 }
	      if (key.indexOf(".")!=-1){
          key1= key.substring(key.lastIndexOf(".")+1);
	 }
         if (nomes.indexOf(key1)!=-1){
	 if (value != 1)
	model.setValueAt(value, nomes.indexOf(key1), 1);
	 else{
		 String antes_do_ponto ;
		 antes_do_ponto = key.substring(0,key.lastIndexOf("."));

		 key1 = antes_do_ponto.substring(antes_do_ponto.lastIndexOf(".")+1)+"."+key1;
		 model.addRow(new Object[]{key1,value});
	 		nomes.add(key1); 
	    
	 }
		 
	 }
     else {
    	 model.addRow(new Object[]{key1,value});
 		nomes.add(key1); 
     }
     
     }

 public synchronized void addfilelistener(filelistener f) {  
	  
     if(!filelisteners.contains(f)) {  

    	 filelisteners.add(f);  

     }  



}
 private void disparafileselected(filevent evento) {  
	  
     Collection <filelistener> tl;  

     synchronized (this) {  

         tl = (Collection)(((ArrayList)filelisteners).clone());  

     }  
     
     
     
     for (filelistener t : tl) {  

         t.fileselected(evento);  

     }          
 }
public String openDialog(){
   JFileChooser fileChooser = new JFileChooser();
   fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("xmi","xml","uml"));
   int result = fileChooser.showOpenDialog(null);
   if (result == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
      return selectedFile;
   }
   return null;  
 }
@Override
public void actionPerformed(ActionEvent e) {
	selectedFile = openDialog();
    filevent evento = new filevent(selectedFile);
    
    evento.addExibidor(this);
	
		try {
			evento.addExibidor(new exibidorhtml(selectedFile));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
    disparafileselected(evento);
} 
public void addevento(JMenuItem item ,ArrayList<Exibidor> exib){
	
	item.addActionListener(this);
}
public void addparser(ParserXMIbase parser){
  this.parser =parser;
		  this.parser.addfilelistener(new MatchListener() {
	
	@Override
	public synchronized void matchfind(Matchevent matchevent) {
		
		atualizarRegistro(matchevent.conteudo, matchevent.repeticoes);
		}
		
		
	
});

}
@Override
public void atualizarRegistro(String Name) {
	// TODO Auto-generated method stub
	
}
}
