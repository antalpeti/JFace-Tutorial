package org.o7planning.tutorial.jface.treetable1;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.o7planning.tutorial.jface.model.DataModel;

public class TreeTableViewerDemo {
  private static class TreeContentProvider implements ITreeContentProvider {
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

    @Override
    public void dispose() {}

    @Override
    public Object[] getElements(Object inputElement) {
      return getChildren(inputElement);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
      return new Object[] {"item_0", "item_1", "item_2"};
    }

    @Override
    public Object getParent(Object element) {
      return null;
    }

    @Override
    public boolean hasChildren(Object element) {
      return getChildren(element).length > 0;
    }
  }

  protected Shell shlTreeTableViewer;

  /**
   * Launch the application.
   *
   * @param args
   */
  public static void main(String[] args) {
    try {
      TreeTableViewerDemo window = new TreeTableViewerDemo();
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
    shlTreeTableViewer.open();
    shlTreeTableViewer.layout();
    while (!shlTreeTableViewer.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  /**
   * Create contents of the window.
   */
  protected void createContents() {
    shlTreeTableViewer = new Shell();
    shlTreeTableViewer.setSize(590, 300);
    shlTreeTableViewer.setText("Tree Table Viewer");
    shlTreeTableViewer.setLayout(new FillLayout(SWT.HORIZONTAL));

    TreeViewer treeViewer = new TreeViewer(shlTreeTableViewer, SWT.BORDER);
    Tree tree = treeViewer.getTree();
    tree.setLinesVisible(true);
    tree.setHeaderVisible(true);

    treeViewer.setLabelProvider(new DeptEmpTreeTableCLProvider());
    treeViewer.setContentProvider(new DeptEmpTreeTableCLProvider());

    TreeColumn trclmnDeptNo = new TreeColumn(tree, SWT.NONE);
    trclmnDeptNo.setWidth(100);
    trclmnDeptNo.setText("Dept No");

    TreeColumn trclmnDeptName = new TreeColumn(tree, SWT.NONE);
    trclmnDeptName.setWidth(100);
    trclmnDeptName.setText("Dept Name");

    TreeColumn trclmnEmpNo = new TreeColumn(tree, SWT.NONE);
    trclmnEmpNo.setWidth(100);
    trclmnEmpNo.setText("Emp No");

    TreeColumn trclmnFirstName = new TreeColumn(tree, SWT.NONE);
    trclmnFirstName.setWidth(100);
    trclmnFirstName.setText("First Name");

    TreeColumn trclmnLastName = new TreeColumn(tree, SWT.NONE);
    trclmnLastName.setWidth(100);
    trclmnLastName.setText("Last Name");

    treeViewer.setInput(DataModel.getDepartments());
  }

}
