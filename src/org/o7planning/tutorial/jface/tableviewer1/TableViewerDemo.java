package org.o7planning.tutorial.jface.tableviewer1;

import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.o7planning.tutorial.jface.model.Article;
import org.o7planning.tutorial.jface.model.DataModel;

public class TableViewerDemo {

  protected Shell shell;
  private Table table;
  private Text textArticle;
  private Text textAuthor;

  /**
   * Launch the application.
   *
   * @param args
   */
  public static void main(String[] args) {
    try {
      TableViewerDemo window = new TableViewerDemo();
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
    shell.open();
    shell.layout();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  /**
   * Create contents of the window.
   */
  protected void createContents() {
    shell = new Shell();
    shell.setSize(450, 300);
    shell.setText("SWT Application");
    FillLayout fl_shell = new FillLayout(SWT.VERTICAL);
    shell.setLayout(fl_shell);

    Composite compositeParent = new Composite(shell, SWT.NONE);
    GridLayout gl_compositeParent = new GridLayout(1, false);
    gl_compositeParent.verticalSpacing = 0;
    gl_compositeParent.horizontalSpacing = 0;
    gl_compositeParent.marginWidth = 0;
    gl_compositeParent.marginHeight = 0;
    compositeParent.setLayout(gl_compositeParent);

    Composite compositeTable = new Composite(compositeParent, SWT.NONE);
    compositeTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    compositeTable.setLayout(new FillLayout(SWT.HORIZONTAL));

    TableViewer tableViewer = new TableViewer(compositeTable, SWT.BORDER | SWT.FULL_SELECTION);

    // Content Provider & Label Provider.
    tableViewer.setContentProvider(new ArticleTableCLProvider());
    tableViewer.setLabelProvider(new ArticleTableCLProvider());

    table = tableViewer.getTable();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
    tblclmnNewColumn.setWidth(150);
    tblclmnNewColumn.setText("Article");

    TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
    tblclmnNewColumn_1.setWidth(120);
    tblclmnNewColumn_1.setText("Author");

    // Set input data to TableViewer
    List<Article> articleList = DataModel.getArticles();
    tableViewer.setInput(articleList);

    Composite compositeAdd = new Composite(compositeParent, SWT.NONE);
    compositeAdd.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
    GridLayout gl_compositeAdd = new GridLayout(6, false);
    compositeAdd.setLayout(gl_compositeAdd);

    Label lblArticle = new Label(compositeAdd, SWT.NONE);
    lblArticle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    lblArticle.setText("Article");

    textArticle = new Text(compositeAdd, SWT.BORDER);
    textArticle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

    Label lblAuthor = new Label(compositeAdd, SWT.NONE);
    lblAuthor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    lblAuthor.setText("Author");

    textAuthor = new Text(compositeAdd, SWT.BORDER);

    Button btnPublished = new Button(compositeAdd, SWT.CHECK);
    btnPublished.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    btnPublished.setText("Published");

    Button btnAdd = new Button(compositeAdd, SWT.NONE);
    btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    btnAdd.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        // Get TableViewer data
        List<Article> input = (List<Article>) tableViewer.getInput();
        // Add row
        Article newArticle =
            new Article(textArticle.getText(), textAuthor.getText(), btnPublished.getSelection());
        input.add(newArticle);
        tableViewer.refresh();
      }
    });
    btnAdd.setText("Add");
  }
}
