package org.o7planning.tutorial.jface.treeviewer1;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.o7planning.tutorial.jface.model.DataModel;

public class TreeViewerDemo {

  protected Shell shlTreeViewerDemo;

  /**
   * Launch the application.
   *
   * @param args
   */
  public static void main(String[] args) {
    try {
      TreeViewerDemo window = new TreeViewerDemo();
      window.open();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Open the window.
   */
  public void open() {
    Display display = Display.getDefault();
    createContents();
    shlTreeViewerDemo.open();
    shlTreeViewerDemo.layout();
    while (!shlTreeViewerDemo.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  /**
   * Create contents of the window.
   */
  protected void createContents() {
    shlTreeViewerDemo = new Shell();
    shlTreeViewerDemo.setSize(450, 300);
    shlTreeViewerDemo.setText("TreeViewer Demo");
    shlTreeViewerDemo.setLayout(new FillLayout(SWT.HORIZONTAL));

    TreeViewer treeViewer = new TreeViewer(shlTreeViewerDemo, SWT.BORDER);
    treeViewer.setContentProvider(new AppMenuTreeCLProvider());
    treeViewer.setLabelProvider(new AppMenuTreeCLProvider());

    Tree tree = treeViewer.getTree();
    tree.setLinesVisible(true);
    tree.setHeaderVisible(true);

    treeViewer.setInput(DataModel.getAppMenus());
  }

}
