/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: IBM Corporation - initial API and implementation Jeanderson Candido
 * <http://jeandersonbc.github.io> - Bug 414565
 *******************************************************************************/
package org.eclipse.jface.snippets.dialogs;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

public class Snippet058VistaProgressBars {

  /**
   * Open a progress monitor dialog and switch the blocking.
   *
   * @param args
   * @wbp.parser.entryPoint
   */
  public static void main(String[] args) {

    Display display = new Display();
    // Shell shell = new Shell(display);
    // shell.setLayout(new FillLayout());
    //
    // shell.open();


    final ProgressMonitorDialog dialog = new ProgressMonitorDialog(null);
    IRunnableWithProgress runnable = createRunnableFor(dialog);
    try {
      dialog.run(true, true, runnable);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // while (!shell.isDisposed()) {
    // if (!display.readAndDispatch()) {
    // display.sleep();
    // }
    // }

    display.dispose();
  }

  private static IRunnableWithProgress createRunnableFor(final ProgressMonitorDialog dialog) {

    return new IRunnableWithProgress() {

      @Override
      public void run(IProgressMonitor monitor) throws InvocationTargetException,
      InterruptedException {

        IProgressMonitorWithBlocking blocking = (IProgressMonitorWithBlocking) monitor;

        blocking.beginTask("Vista Coolness", 100);
        for (int i = 0; i < 10; i++) {
          blocking.setBlocked(new Status(IStatus.WARNING, "Blocked", "This is blocked on Vista"));
          blocking.worked(5);
          spin(dialog.getShell().getDisplay());
          blocking.clearBlocked();
          blocking.worked(5);
          spin(dialog.getShell().getDisplay());
          if (monitor.isCanceled()) {
            return;
          }
        }
        blocking.done();
      }
    };
  }

  private static void spin(final Display display) {
    display.syncExec(new Runnable() {

      @Override
      public void run() {
        long endTime = System.currentTimeMillis() + 1000;

        while (System.currentTimeMillis() < endTime) {
          display.readAndDispatch();
        }

      }
    });

  }
}
