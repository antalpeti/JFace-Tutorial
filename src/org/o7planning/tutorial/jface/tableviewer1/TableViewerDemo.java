package org.o7planning.tutorial.jface.tableviewer1;

import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.o7planning.tutorial.jface.model.Article;
import org.o7planning.tutorial.jface.model.DataModel;

public class TableViewerDemo {

  protected Shell shell;
  private Table table;

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
    shell.setLayout(new FillLayout(SWT.HORIZONTAL));

    TableViewer tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);

    // Content Provider & Label Provider.
    ArticleTableCLProvider provider = new ArticleTableCLProvider();
    tableViewer.setContentProvider(provider);
    tableViewer.setLabelProvider(provider);

    table = tableViewer.getTable();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
    tblclmnNewColumn.setWidth(150);
    tblclmnNewColumn.setText("Article");

    TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
    tblclmnNewColumn_1.setMoveable(true);
    tblclmnNewColumn_1.setWidth(120);
    tblclmnNewColumn_1.setText("Author");

    // Set InputData for TableViewer.
    List<Article> articleList = DataModel.getArticles();
    tableViewer.setInput(articleList);

  }

}
