package workouts;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainWindow {

  protected Shell shlFileManipulator;
  private Text textSelectedDirectory;

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
    shlFileManipulator.setLayout(new FillLayout(SWT.HORIZONTAL));

    Composite compositeParent = new Composite(shlFileManipulator, SWT.NONE);
    compositeParent.setLayout(new GridLayout(2, false));

    Composite compositeFiles = new Composite(compositeParent, SWT.NONE);
    compositeFiles.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    compositeFiles.setLayout(new GridLayout(1, false));

    Label lblSelectedDirectory = new Label(compositeFiles, SWT.NONE);
    lblSelectedDirectory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    lblSelectedDirectory.setText("Selected Directory:");

    textSelectedDirectory = new Text(compositeFiles, SWT.BORDER);
    textSelectedDirectory.setEnabled(false);
    textSelectedDirectory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    ListViewer listViewer = new ListViewer(compositeFiles, SWT.BORDER | SWT.V_SCROLL);
    List list = listViewer.getList();
    list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

    Composite compositeBtn = new Composite(compositeParent, SWT.NONE);
    compositeBtn.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
    compositeBtn.setSize(225, 270);
    compositeBtn.setLayout(new GridLayout(1, false));
    new Label(compositeBtn, SWT.NONE);

    Button btnBrowse = new Button(compositeBtn, SWT.NONE);
    btnBrowse.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnBrowse.setText("Browse...");
    new Label(compositeBtn, SWT.NONE);

    Button btnRename = new Button(compositeBtn, SWT.NONE);
    btnRename.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnRename.setText("Rename");

    Button btnDelete = new Button(compositeBtn, SWT.NONE);
    btnDelete.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnDelete.setText("Delete");

    Button btnDuplicate = new Button(compositeBtn, SWT.NONE);
    btnDuplicate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnDuplicate.setText("Duplicate");
    new Label(compositeBtn, SWT.NONE);

    Button btnPreview = new Button(compositeBtn, SWT.NONE);
    btnPreview.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnPreview.setText("Preview");
    new Label(compositeBtn, SWT.NONE);

    Button btnExit = new Button(compositeBtn, SWT.NONE);
    btnExit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnExit.setText("Exit");

  }
}
