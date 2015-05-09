package org.o7planning.tutorial.jface.rs;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class ResourceDemo {

  protected Shell shlResourceDemo;

  /**
   * Launch the application.
   *
   * @param args
   */
  public static void main(String[] args) {
    try {
      ResourceDemo window = new ResourceDemo();
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
    shlResourceDemo.open();
    shlResourceDemo.layout();
    while (!shlResourceDemo.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  /**
   * Create contents of the window.
   */
  protected void createContents() {
    shlResourceDemo = new Shell();
    shlResourceDemo.setImage(ResourceManager.getPluginImage("JFace-Tutorial",
        "bin/org/o7planning/tutorial/jface/image/check.png"));
    shlResourceDemo.setSize(450, 300);
    shlResourceDemo.setText("Resource Demo");
    shlResourceDemo.setLayout(new RowLayout(SWT.HORIZONTAL));

    Button btnButtonUsingSwt = new Button(shlResourceDemo, SWT.NONE);
    btnButtonUsingSwt.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
    btnButtonUsingSwt.setFont(SWTResourceManager.getFont("Arial", 12, SWT.NORMAL));
    btnButtonUsingSwt.setImage(SWTResourceManager.getImage(ResourceDemo.class,
        "/org/o7planning/tutorial/jface/image/check.png"));
    btnButtonUsingSwt.setText("Button using SWT Resource Manager");

    //

    Button button2 = new Button(shlResourceDemo, SWT.NONE);
    button2.setText("Button using JFace Resource Manager");

    LocalResourceManager jfaceRsManager =
        new LocalResourceManager(JFaceResources.getResources(), shlResourceDemo);


    Color color = jfaceRsManager.createColor(new RGB(200, 100, 0));
    Font font = jfaceRsManager.createFont(FontDescriptor.createFrom("Arial", 10, SWT.BOLD));

    ImageDescriptor imageDescriptor =
        ImageDescriptor.createFromFile(ResourceDemo.class,
            "/org/o7planning/tutorial/jface/image/check.png");
    Image image = jfaceRsManager.createImage(imageDescriptor);

    button2.setFont(font);
    button2.setForeground(color);
    button2.setImage(image);

  }
}
