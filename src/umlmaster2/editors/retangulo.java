package editor.editors;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.GestureListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tracker;

public class retangulo extends Composite implements PaintListener, MouseListener, MouseMoveListener {
    private Composite composite ;
    private boolean pressionando ; 
    public int x ;
	public  int y ;
	public int width =80;
    public int height = 100;
	private Cursor busyCursor;
	public Text text;
	public Label label;
	private String string = "Class";
	
	public retangulo(Composite parent, int style) {
	
		super(parent, style);
        composite = parent ;
	}

	
	
  public void definir_ponto(int x ,int y ){
	  this.x = x ;
	  this.y = y ;
	   busyCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_SIZEWE);
	   setLocation(x, y);
	   setSize(width, height);
	   setFocus();
	   addPaintListener(this);
	  addMouseListener(this);
	  addMouseMoveListener(this);
	  
       
  }
public void checkSubclass(){
	
}



@Override
public void paintControl(PaintEvent arg0) {
	
	

	arg0.gc.setLineAttributes(new LineAttributes(3));
	arg0.gc.setBackground(arg0.display.getSystemColor(SWT.COLOR_YELLOW));
    arg0.gc.setForeground(arg0.display.getSystemColor(SWT.COLOR_BLACK));
	arg0.gc.drawRectangle(0, 0, width, height);
    arg0.gc.fillRectangle(1, 1, width-2, height-2);
    arg0.gc.drawLine(0, (int)(height *0.25),width, (int)(height *0.25));
    arg0.gc.drawLine(0, (int)(height *0.65),width, (int)(height *0.65));
   
    arg0.gc.drawText(string, (int) width/8, (int)(height *0.03));
    setLocation(x, y);
	setSize(width, height);
    //label = new Label(this, SWT.NONE);
    //label.setText("Class");
    
    //label.setBackground(arg0.gc.getBackground());
    //label.setLocation(0+10,3);
    //label.setSize(55, 20);
    
    //text = new Text(this, SWT.SINGLE);
    //text.setBackground(arg0.gc.getBackground()); 
    //text.setLocation(0+10,3);
 	
 // text.setSize(55, 20);
	
 /*text.addFocusListener(new FocusListener() {
	 	
		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	*/
	

}



@Override
public void mouseDoubleClick(MouseEvent arg0) {
	
	
    redraw();
	if ((arg0.x>= 10)&&(arg0.y<=25)){
		text = new Text(this,SWT.SINGLE);
	    text.setSize(55, 20);
		text.setLocation(0+10,3);
		text.setFocus();
	    
	}
	}



@Override
public void mouseDown(MouseEvent arg0) {
	pressionando= true ;
	if (text !=null){
		string = text.getText();  
	    text.dispose();
		redraw();
		//text.dispose();
	}
		
		
}
  


@Override
public void mouseUp(MouseEvent arg0) {
	
	
	pressionando = false ;

}

@Override
public void mouseMove(MouseEvent arg0) {
// if(getCursor().equals(busyCursor)){
	 
 //}
	if ((arg0.x>= width-5)||(arg0.x<=5)){
		this.setCursor(busyCursor);
		if (pressionando){
			
			       
		        Tracker tracker = new Tracker(composite, SWT.RESIZE|SWT.LEFT|SWT.RIGHT);
		    	tracker.setRectangles(new Rectangle[] { new Rectangle(x-1, y-1, width+1,height+1), });
		    	tracker.setCursor(busyCursor);
		    	tracker.open();
		    	
		        width = tracker.getRectangles()[0].width;
		    	x = tracker.getRectangles()[0].x;
		    	
		    	getCursor();
		    	redraw();
			
	}
	}
		else {
		this.setCursor(null);
	
	if (pressionando){ 
		Tracker tracker = new Tracker(composite, SWT.NONE);
        tracker.setRectangles(new Rectangle[] { new Rectangle( this.getLocation().x-1 ,this.getLocation().y-1, width+1, height+1), });
        this.setCursor(null);
        tracker.open();
	    
       this.setLocation(tracker.getRectangles()[0].x,tracker.getRectangles()[0].y);
	    x = tracker.getRectangles()[0].x ;
	    y = tracker.getRectangles()[0].y;
        tracker.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				
				System.out.println("soltei");
				
			}
		});
        
		}
		}
	pressionando = false ;
	

}

}

