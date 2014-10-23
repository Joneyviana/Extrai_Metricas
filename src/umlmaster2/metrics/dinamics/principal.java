package umlmaster2.metrics.dinamics;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import umlmaster2.metrics.ExibidorTable;

import umlmaster2.metrics.filelistener;
import umlmaster2.metrics.filevent;
import umlmaster2.monitor.SimpleReadFile;

public class principal {

	private static SimpleReadFile simple;
	private static ParserXMIbase base;
	private static ExibidorTable exi;

	public static void main(String[] args) throws IOException, InterruptedException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					exi = new ExibidorTable();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					exi.setVisible(true);
					exi.addfilelistener(new filelistener() {
						
						@Override
						public void fileselected(filevent file) {
							try {
								simple = new SimpleReadFile(file.getNamefile());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							exi.model.setRowCount(0);
							exi.nomes.clear();
							//base = new ParserXMIbase(simple.getText(),new String[]{"tr","td"});
							//exi.addparser(base);
						    //base.SearchFather("br");
						    for (int i=0; i<file.getExibidores().size();i++){
								System.out.print("exibidor "+file.getExibidores().get(i).getClass().getSimpleName());
						    	if (file.getExibidores().get(i).getClass().getSimpleName().equals("exibidorhtml")){
									((exibidorhtml)file.getExibidores().get(i)).montartable(exi.model);
								}
							} 
						}
					});  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		  
		
		 
	
	}

}
