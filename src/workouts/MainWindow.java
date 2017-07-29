package workouts;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainWindow {

  protected Shell shlFileManipulator;
  private Text textSelectedDirectory;
  private ListViewer listViewerFiles;
  private Button btnRename;
  private Button btnDelete;
  private Button btnDuplicate;
  private Button btnPreview;
  private String selectedDirectory;

  /**
   * Launch the application.
   *
   * @param args
   */
  public static void main(String[] args) {
    try {
      MainWindow window = new MainWindow();
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
    shlFileManipulator.open();
    shlFileManipulator.layout();
    while (!shlFileManipulator.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  /**
   * Create contents of the window.
   */
  protected void createContents() {
    shlFileManipulator = new Shell();
    shlFileManipulator.setSize(450, 300);
    shlFileManipulator.setText("File Manipulator");
    shlFileManipulator.setLayout(new FillLayout(SWT.HORIZONTAL));

    Composite compositeParent = new Composite(shlFileManipulator, SWT.NONE);
    compositeParent.setLayout(new GridLayout(2, false));

    Composite compositeFile = new Composite(compositeParent, SWT.NONE);
    compositeFile.setLayout(new GridLayout(1, false));
    compositeFile.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

    Label lblSelectedDirectory = new Label(compositeFile, SWT.NONE);
    lblSelectedDirectory.setText("Selected Directory:");

    textSelectedDirectory = new Text(compositeFile, SWT.BORDER);
    textSelectedDirectory.setEnabled(false);
    textSelectedDirectory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    listViewerFiles =
        new ListViewer(compositeFile, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
    listViewerFiles.setContentProvider(new ArrayContentProvider());
    listViewerFiles.setLabelProvider(new ILabelProvider() {

      @Override
      public void removeListener(ILabelProviderListener listener) {

      }

      @Override
      public boolean isLabelProperty(Object element, String property) {
        return false;
      }

      @Override
      public void dispose() {

      }

      @Override
      public void addListener(ILabelProviderListener listener) {

      }

      @Override
      public String getText(Object element) {
        return ((File) element).getName();
      }

      @Override
      public Image getImage(Object element) {
        return null;
      }
    });
    List listFiles = listViewerFiles.getList();
    listFiles.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        IStructuredSelection selection = (IStructuredSelection) listViewerFiles.getSelection();
        if (selection.size() == 1) {
          btnRename.setEnabled(true);
          btnDelete.setEnabled(true);
          btnDuplicate.setEnabled(true);
          if (((File) selection.getFirstElement()).getName().endsWith(".log")
              || ((File) selection.getFirstElement()).getName().endsWith(".txt")) {
            btnPreview.setEnabled(true);
          }
        } else if (selection.size() == 0) {
          btnRename.setEnabled(false);
          btnDelete.setEnabled(false);
          btnDuplicate.setEnabled(false);
          btnPreview.setEnabled(false);
        } else if (selection.size() > 1) {
          btnRename.setEnabled(false);
          btnDuplicate.setEnabled(false);
          btnPreview.setEnabled(false);
        }
      }
    });
    listFiles.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

    Composite compositeBtn = new Composite(compositeParent, SWT.NONE);
    compositeBtn.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
    compositeBtn.setLayout(new GridLayout(1, false));
    new Label(compositeBtn, SWT.NONE);

    Button btnBrowse = new Button(compositeBtn, SWT.NONE);
    btnBrowse.setToolTipText("Browse a directory");
    btnBrowse.addSelectionListener(new SelectionAdapter() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        String oldDir = textSelectedDirectory.getText();
        DirectoryDialog dlg = new DirectoryDialog(shlFileManipulator);
        if (selectedDirectory != null) {
          dlg.setFilterPath(selectedDirectory);
        }
        selectedDirectory = dlg.open();
        if (selectedDirectory != null && !selectedDirectory.equals(oldDir)) {
          textSelectedDirectory.setText(selectedDirectory);
          File dir = new File(selectedDirectory);
          ArrayList<File> files = new ArrayList<>();
          for (File file : dir.listFiles()) {
            if (file.isFile()) {
              files.add(file);
            }
          }
          listViewerFiles.setInput(files);
        }
        listViewerFiles.refresh();
      }
    });
    btnBrowse.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnBrowse.setBounds(0, 0, 75, 25);
    btnBrowse.setText("Browse...");
    new Label(compositeBtn, SWT.NONE);

    btnRename = new Button(compositeBtn, SWT.NONE);
    btnRename.setToolTipText("Rename the selected file");
    btnRename.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        IStructuredSelection selection = (IStructuredSelection) listViewerFiles.getSelection();
        File file = (File) selection.getFirstElement();
        InputDialog dlg =
            new InputDialog(Display.getCurrent().getActiveShell(), "Rename", "Rename file", file
                .getName(), null);
        dlg.open();
        String newFileName = dlg.getValue();
        File newFile = new File(file.getParent() + "\\" + newFileName);
        boolean success = file.renameTo(newFile);
        if (!success) {
          MessageDialog.openError(shlFileManipulator, "Error", "Wrong filename!");
        } else {
          listViewerFiles.refresh(true);
        }
      }
    });
    btnRename.setEnabled(false);
    btnRename.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnRename.setText("Rename");

    btnDelete = new Button(compositeBtn, SWT.NONE);
    btnDelete.setToolTipText("Delete the selected file(s)");
    btnDelete.setEnabled(false);
    btnDelete.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnDelete.setText("Delete");

    btnDuplicate = new Button(compositeBtn, SWT.NONE);
    btnDuplicate.setToolTipText("Duplicate the selected file");
    btnDuplicate.setEnabled(false);
    btnDuplicate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnDuplicate.setText("Duplicate");

    btnPreview = new Button(compositeBtn, SWT.NONE);
    btnPreview.setToolTipText("Preview the selected .log or .txt file");
    btnPreview.setEnabled(false);
    btnPreview.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnPreview.setText("Preview");
    new Label(compositeBtn, SWT.NONE);

    Button btnExit = new Button(compositeBtn, SWT.NONE);
    btnExit.setToolTipText("Exit from the program");
    btnExit.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        shlFileManipulator.dispose();
      }
    });
    btnExit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnExit.setText("Exit");

  }
}
