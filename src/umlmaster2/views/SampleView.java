package umlmaster2.views;


import java.io.File;
import java.io.IOException;
import java.net.URI;

import umlmaster2.metrics.Classe;
import umlmaster2.metrics.extendmetricas;
import umlmaster2.monitor.*;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.*;
import org.eclipse.core.internal.events.ResourceDelta;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class SampleView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "umlmaster2.views.SampleView";
	ListViewer viewer;
    private URI arquivo_uml ;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;
	private IWorkspaceRoot root;
    private Color color;
    private Color color1 ;
    private Device device;
	private Composite parent ;

	private StyledText container;
    /*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	private StyleRange range;
	private GridData gd;
	 
	//class ViewContentProvider implements IStructuredContentProvider {
		//public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		//}
		//public void dispose() {
		//}
		//public Object[] getElements(Object parent) {
		//ArrayList metric = new ArrayList<Metrics>();
		//Metrics CBO = new Metrics();
		//CBO.setMetrics("CBO 3");
		//metric.add(CBO);
		//return metric;
		//}
	//}
	




	
	public void init(IViewSite site) {
        try {
			super.init(site);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(final Composite parent1) {
	          
		      parent = parent1;
		      RowLayout rowlayout = new RowLayout();
		      final ScrolledComposite comp = new ScrolledComposite(parent, SWT.V_SCROLL) ;
		      GridLayout layoutparent = new GridLayout();
		      GridLayout layoutcomposite = new GridLayout();
		      layoutparent.numColumns = 2 ;
		      layoutcomposite.numColumns = 12 ;
		     // parent.setSize(1000, parent.getSize().y);
		      container = new StyledText(comp, 0);
		      container.setSize(400,200);
		      comp.setContent(container);
		      Composite composite = new Composite(parent ,SWT.SINGLE|SWT.BORDER);
		      composite.setSize(500, 300);
		      composite.setLocation(420 ,0);
		      siblingsLabels label_and_text = new siblingsLabels(new String[]{"CBO ","DIT ", "NOC ","CS ","NOO ","NOA"}, composite);
		      composite.setLayout(layoutcomposite);
				//CBOtext.setLayoutData(gd);
		      rowlayout.type = SWT.VERTICAL;
        IWorkspace work = ResourcesPlugin.getWorkspace();
        IResourceChangeListener listener = new IResourceChangeListener() {
        	 

			

			private IResource resource;
			private StyleRange range1;
			private StyleRange range;
			private StyleRange corname;
			private StyleRange range2;

			public void resourceChanged(IResourceChangeEvent event) {
        	
				root = ResourcesPlugin.getWorkspace().getRoot();
        		IResourceDelta[]  recurso  = event.getDelta().getAffectedChildren();
        		
        		make_path(recurso);
        	
        		
        		
        		
        		
				try {
					
					File input = new File(arquivo_uml);

					Document doc = Jsoup.parse(input , "UTF-8");
					
					Elements classes = doc.select("packagedElement[xmi:type=\"uml:Class\"]");
					System.out.println("tamanho!" + classes.size());
					
					container.setText("");
					container.setLineSpacing(3);
					int lastchar = 0;
					for (Element classe  : classes) {
		        		Classe cl = Classe.getInstanceNotEqualOther(classe);
		        		extendmetricas cla  = new extendmetricas(cl);
		        		if ((cla.getCBO()>=2)||(cla.getNOC()>=2)){ 
		        			setcolorRangeLine(color1, "\n"+cla.getName()+"\n");
		        		    System.out.println("sim vermelho");
		        		}
		        		    else 
		        		setcolorRangeLine(color, "\n"+cla.getName()+"\n");
		        		Decision_color(2,cla.getNOC(),"\t NOC: ");
		        		Decision_color(2,cla.getCBO(),"\t CBO: ");
		        		Decision_color(2,cla.getDIT(),"\t  DIT: ");
		        		Decision_color(2,cla.getCS(),"\t   CS: ");	
		        		Decision_color(2,cla.getNOO(),"\t  NOO: ");	
		        		Decision_color(2,cla.getNOA(),"\t  NOA: ");
			        	 
		        		 
		        			
				          
					} }catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		container.pack();
					
				
        			
        		
			
        		
        		
        	 }

			private void  make_path(IResourceDelta[]  recurso) {
				IResourceDelta[]  recurso1  = recurso[0].getAffectedChildren();
				if (recurso1[0].getFullPath().getFileExtension().equals("uml")){
					resource = root.findMember(new Path("/"));
        		    IContainer contain = (IContainer) resource;
					IFile file = contain.getFile(new Path(recurso1[0].getFullPath().toString()));
			        arquivo_uml =  file.getLocationURI();
				   System.out.println(file.getLocationURI());
				}
				else {
				if (recurso1.length!=0){
					 make_path(recurso1);
			   }
			
				}
				}
                 
		
           };
           
        work.addResourceChangeListener(listener);
        parent.setLayout(layoutparent);
        comp.setLayout(rowlayout);
        
        container.append("DIT    -3\n");
        
      device = new Device() {
			
			@Override
			public long internal_new_GC(GCData arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void internal_dispose_GC(long arg0, GCData arg1) {
				// TODO Auto-generated method stub
				
			}
		};
		
		 color = new Color(device, 80, 180, 80);
		 color1 = new Color(device, 230, 40, 40);
		
		comp.setBackground(new Color(device , 10,10,10));
	     
		 container.setEditable(false);
		container.setCaret(null);;
	
		container.setBackground(new Color(device , 10,10,10));
	}      
        
	    
       
        
	






	

	protected void Decision_color(int restricao, int valor  , String string) {
		if (valor>=restricao) 
    		setcolorRangeLine(color1 ,string + valor);
    	     
    		else 
    			setcolorRangeLine(color ,string+ valor);
		
	}
	protected void setcolorRangeLine(Color color2, String line) {
		int lastchar = container.getCharCount();
		container.append(line);
		range = new StyleRange(lastchar, container.getCharCount()-lastchar, color2,null);
		container.setStyleRange(range);
		
	}
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		
	}
}
