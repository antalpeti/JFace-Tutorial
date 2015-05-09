package org.o7planning.tutorial.jface.decoration;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.FlowLayout;

public class DecorationDemo {

  protected Shell shlDecorationDemo;
  private Text text;

  /**
   * Launch the application.
   *
   * @param args
   */
  public static void main(String[] args) {
    try {
      DecorationDemo window = new DecorationDemo();
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
    shlDecorationDemo.open();
    shlDecorationDemo.layout();
    while (!shlDecorationDemo.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  /**
   * Create contents of the window.
   */
  protected void createContents() {
    shlDecorationDemo = new Shell();
    shlDecorationDemo.setSize(450, 300);
    shlDecorationDemo.setText("Decoration Demo");
    shlDecorationDemo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    text = new Text(shlDecorationDemo, SWT.BORDER);

    ControlDecoration controlDecoration = new ControlDecoration(text, SWT.LEFT | SWT.TOP);
    controlDecoration.setDescriptionText("Some description");

    Image image =
        FieldDecorationRegistry.getDefault()
        .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR_QUICKFIX).getImage();

    controlDecoration.setImage(image);


  }

}
