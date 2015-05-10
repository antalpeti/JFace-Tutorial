package org.o7planning.tutorial.jface.provider;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

public abstract class AbstractTreeContentLabelProvider implements ITreeContentProvider,
ILabelProvider {

  @Override
  public void dispose() {

  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

  }

  @Override
  public Object getParent(Object element) {
    return null;
  }

  @Override
  public void addListener(ILabelProviderListener listener) {

  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {}

  @Override
  public Image getImage(Object element) {
    return null;
  }

}
