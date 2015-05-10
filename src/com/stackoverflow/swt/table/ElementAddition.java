package com.stackoverflow.swt.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class ElementAddition {
  private static Shell shell;
  private static Table table;

  /**
   * Launch the application.
   *
   * @param args
   */
  public static void main(String[] args) {
    ElementAddition window = new ElementAddition();
    window.open();
  }

  /**
   * Open the window.
   */
  private void open() {
    Display display = Display.getDefault();
    createContents();
    shell.open();
    shell.layout();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Create contents of the window.
   */
  protected void createContents() {
    shell = new Shell();
    shell.setSize(400, 400);
    shell.setLayout(new FillLayout(SWT.HORIZONTAL));

    Composite composite = new Composite(shell, SWT.NONE);
    composite.setLayout(new GridLayout(1, false));

    table = new Table(composite, SWT.BORDER | SWT.MULTI);
    table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

    final Text text = new Text(composite, SWT.SINGLE | SWT.BORDER);
    text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    text.setText("blahblah text");

    Button button = new Button(composite, SWT.PUSH);
    button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    button.setText("Push me");

    // this is the code you want
    button.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        TableItem item = new TableItem(table, SWT.NONE);
        item.setText(text.getText());
      }
    });

    createDefaultElements();
  }

  /**
   * Created some elements in the table.
   */
  protected void createDefaultElements() {
    for (int i = 0; i < 5; i++) {
      TableItem item = new TableItem(table, SWT.NONE);
      item.setText("*** Item " + i + "***");
    }
  }
}
