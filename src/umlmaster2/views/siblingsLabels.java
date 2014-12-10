package umlmaster2.views;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

public class siblingsLabels {

private Label item;
public  ArrayList<Text> items = new ArrayList<>();

public siblingsLabels(String[] texts, Composite comp){
	 for (String text:texts){
		 item  = new Label(comp ,  SWT.SINGLE|SWT.UP);
		 Text texte = new Text(comp, SWT.BORDER | SWT.SINGLE|SWT.UP);
		 item.setText(text);
		 items.add(texte);
     }
}
}