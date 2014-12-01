package editor.editors;

import org.eclipse.swt.graphics.Rectangle;

import java.io.StringWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.sound.sampled.Line;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tracker;
import org.eclipse.ui.*;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.ide.IDE;
import org.w3c.dom.css.Rect;

/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class MultiPageEditor extends MultiPageEditorPart implements IResourceChangeListener{

	/** The text editor used in page 0. */
	private ArrayList <Ponto> risco = new ArrayList<>();
    private ArrayList <linha> Menu = new ArrayList<>();
	private Ponto posicao_direita_inicio;
	private TextEditor editor;
	private boolean pressionado;
	
	/** The font chosen in page 1. */
	private Font font;

	/** The text widget used in page 2. */
	private Color color;
	private ArrayList<retangulo> retangulos = new ArrayList<>();
	private int Count = 0 ;
	private StyledText text;
	private Canvas canvas;
	private Button fontButton;
	private Point ponto_anterior;
	private Display display ;
	private Style style = new AssociacaoSimples();
	/**
	 * Creates a multi-page editor example.
	 */
	public MultiPageEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}
	/**
	 * Creates page 0 of the multi-page editor,
	 * which contains a text editor.
	 */
	void createPage0() {
		try {
			editor = new TextEditor();
			int index = addPage(editor, getEditorInput());
			setPageText(index, editor.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(
				getSite().getShell(),
				"Error creating nested text editor",
				null,
				e.getStatus());
		}
	}
	/**
	 * Creates page 1 of the multi-page editor,
	 * which allows you to change the font used in page 2.
	 */
	void createPage1() {
         style = new heranca() ;
		 canvas = new Canvas(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		canvas.setLayout(layout);
		layout.numColumns = 2;
	    
		int count = 0;
		
				canvas.addPaintListener(new PaintListener() {
			          private PaintEvent eventodesenho;
			         
			          public void paintControl(PaintEvent e) {
			        	e.gc.setLineAttributes(new LineAttributes(3));
			        	e.gc.textExtent("fdf");
			            
			        	e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_YELLOW));
			            e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLACK));
			            display = e.display;
			            
			      
			            
			           
			            for (linha line : Menu){
			            	    
              
			         e.gc.drawLine( line.ponto.x, line.ponto.y, line.ponto_fim.x,line.ponto_fim.y );
			         if (line.ponto.equals(line.ponto_fim)==false){
			        	
			        	 line.style_linha.addfeature(e.gc, line.ponto,line.ponto_fim);
			         }
			           
			            }
				
			}
		
		
	});
	
	
		Listener listener = new Listener() {
            int count = 0;
        	public void handleEvent(Event e) {
             
            	count +=1;
            	if ((pressionado == true)&&(count%4==0)){
            	Ponto ponto = new Ponto();	
            	ponto.x = e.x ;
            	ponto.y = e.y ;
                risco.add(ponto);
            	if (Menu.isEmpty()==false){
                Menu.get(Menu.size()-1).ponto.x = risco.get(risco.size()-1).x;
            	Menu.get(Menu.size()-1).ponto.y = risco.get(risco.size()-1).y;
            	Menu.get(Menu.size()-1).ponto_fim.x = risco.get(0).x;
            	Menu.get(Menu.size()-1).ponto_fim.y = risco.get(0).y ;
            	canvas.redraw();
            	}
            	}
            
            }
            };
        canvas.addListener(SWT.MouseDown, new	Listener() {
			
			

			@Override
			public void handleEvent(Event arg0) {
				System.out.print("que capeta é esse");
				linha line = new linha();
				line.setstyle(style);
				line.ponto = new Ponto();
				line.ponto_fim =  new Ponto();
				risco.clear();
				Menu.add(line);
				if(arg0.button == 3){
					System.out.print("e de direito");
				    posicao_direita_inicio = new Ponto() ;
					posicao_direita_inicio.x = arg0.x ;
				    posicao_direita_inicio.y = arg0.y ;
				}
				else{    	
				pressionado = true ;
				}
			}
		});
        canvas.addListener(SWT.MouseMove, listener);
        canvas.addListener(SWT.MouseUp, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				pressionado = false ;
				
			}
		});
       
		
         
         int index = addPage(canvas);
		setPageText(index, "Properties");
		  Menu popupMenu = new Menu(canvas);
		  
		  MenuItem newItem = new MenuItem(popupMenu, SWT.NONE);
		    newItem.setText("Class");
		    newItem.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
				
							retangulo ret = new retangulo(canvas,SWT.NONE );
                            ret.definir_ponto(posicao_direita_inicio.x, posicao_direita_inicio.y);
							retangulos.add(ret);  
                            
                            
							int x1 = canvas.getSize().x;
							int y1 = canvas.getSize().y ;
							
							canvas.pack();
						    canvas.setSize(x1, y1);
							
							canvas.layout(true);
							canvas.redraw();
							
							
							
					};
					
							
							
							
							
				
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					
					
				}
			});
		
				
			
				
				
					
			
		    MenuItem refreshItem = new MenuItem(popupMenu, SWT.CASCADE);
		    refreshItem.setText("Line style");
		   
		    Menu lineMenu  = new Menu(popupMenu);
		    refreshItem.setMenu(lineMenu);

		    MenuItem shortcutItem = new MenuItem(lineMenu, SWT.NONE);
		    shortcutItem.setText("herança");
		    MenuItem iconItem = new MenuItem(lineMenu, SWT.NONE);
		    iconItem.setText("Associação Simples");
		    MenuItem deleteItem = new MenuItem(popupMenu, SWT.NONE);
		    deleteItem.setText("Delete");
	
		    canvas.setMenu(popupMenu);
	}
	

/**
	 * Creates page 2 of the multi-page editor,
	 * which shows the sorted text.
	 */
	void createPage2() {
		Composite composite = new Composite(getContainer(), SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		text = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		text.setEditable(false);

		int index = addPage(composite);
		setPageText(index, "Preview");
	}
	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		//createPage0();
		createPage1();
		//createPage2();
	}
	/**
	 * The <code>MultiPageEditorPart</code> implementation of this 
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	/**
	 * Saves the multi-page editor's document.
	 */
	
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}
	/**
	 * Saves the multi-page editor's document as another file.
	 * Also updates the text for page 0's tab, and updates this multi-page editor's input
	 * to correspond to the nested editor's.
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
		throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {
			sortWords();
		}
	}
	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event){
		if(event.getType() == IResourceChangeEvent.PRE_CLOSE){
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i<pages.length; i++){
						if(((FileEditorInput)editor.getEditorInput()).getFile().getProject().equals(event.getResource())){
							IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
							pages[i].closeEditor(editorPart,true);
						}
					}
				}            
			});
		}
	}
	/**
	 * Sets the font related data to be applied to the text in page 2.
	 */
	void setFont() {
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null)
				font.dispose();
			font = new Font(text.getDisplay(), fontData);
			text.setFont(font);
		}
	}
	/**
	 * Sorts the words in page 0, and shows them in page 2.
	 */
	void sortWords() {

		String editorText =
			editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();

		StringTokenizer tokenizer =
			new StringTokenizer(editorText, " \t\n\r\f!@#\u0024%^&*()-_=+`~[]{};:'\",.<>/?|\\");
		ArrayList editorWords = new ArrayList();
		while (tokenizer.hasMoreTokens()) {
			editorWords.add(tokenizer.nextToken());
		}

		Collections.sort(editorWords, Collator.getInstance());
		StringWriter displayText = new StringWriter();
		for (int i = 0; i < editorWords.size(); i++) {
			displayText.write(((String) editorWords.get(i)));
			displayText.write(System.getProperty("line.separator"));
		}
		text.setText(displayText.toString());
	}
}