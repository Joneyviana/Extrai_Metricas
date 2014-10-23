package umlmaster2.views;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import umlmaster2.metrics.metricsfacade;
import umlmaster2.monitor.FileWatcher;
import umlmaster2.views.*;

import java.nio.channels.*;

import javax.inject.Inject;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.internal.UISynchronizer;
import org.eclipse.ui.internal.ViewSite;
import org.eclipse.ui.part.*;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;


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
	@Inject UISynchronizer sync;
	private ListViewer viewer;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;
   
    private Color color;
    private Color color1 ;
    private Device device;
	private Composite parent ;
    /*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	 
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
		class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			switch(index){
			case 0: return "metrics";
			case 1: return "valor" ;
			}
			return "";
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	class NameSorter extends ViewerSorter {
	}




	     public SampleView() {
	}
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
	public void createPartControl(Composite parent) {
	
		      this.parent = parent;
		      RowLayout rowlayout = new RowLayout();
        rowlayout.type = SWT.VERTICAL;
        IWorkspace work = ResourcesPlugin.getWorkspace() ;
        IResourceChangeListener listener = new IResourceChangeListener() {
        	 public void resourceChanged(IResourceChangeEvent event) {
                 
        		new metricsfacade(new String[]{"CBO","NOC","DIT"});
              }
           };
        
        work.addResourceChangeListener(listener);
        this.parent.setLayout(rowlayout);
        StyledText container = new StyledText(parent, 0);
        
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
		
		 parent.setBackground(new Color(device , 10,10,10));
		container.setEditable(false);
		container.setCaret(null);;
		container.append("CBO  -4");
		StyleRange range1 = new StyleRange(0, "DIT    -3\n".length(), color,null);
		container.setStyleRange(range1);
		StyleRange range = new StyleRange("DIT    -3\n".length(), "CBO  -4".length(), color1,null);
		container.setStyleRange(range);
		
		
        
        
	    
        

     // more code
       
         // do something long running
         //... 
                 
	}
			//makeActions();
			//hookContextMenu();
			//hookDoubleClickAction();
			//contributeToActionBars();
         
        
     private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				SampleView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Sample View",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
