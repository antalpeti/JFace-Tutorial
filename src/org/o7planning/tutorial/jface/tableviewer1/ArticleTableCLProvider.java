package org.o7planning.tutorial.jface.tableviewer1;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.o7planning.tutorial.jface.model.Article;
import org.o7planning.tutorial.jface.provider.AbstractTableContentLabelProvider;

public class ArticleTableCLProvider extends AbstractTableContentLabelProvider {

  private Image image;

  public ArticleTableCLProvider() {
    LocalResourceManager jfaceRsManager =
        new LocalResourceManager(JFaceResources.getResources(), Display.getCurrent().getShells()[0]);

    ImageDescriptor imageDescriptor =
        ImageDescriptor.createFromFile(ArticleTableCLProvider.class,
            "/org/o7planning/tutorial/jface/image/check.png");
    image = jfaceRsManager.createImage(imageDescriptor);
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    Article article = (Article) element;
    switch (columnIndex) {

      // 0 - For first column
      case 0:
        return article.getTitle();

        // 1 - For Second column
      case 1:
        return article.getAuthor();
      default:
        return null;
    }
  }

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    Article article = (Article) element;
    switch (columnIndex) {

      // 0 - For first column
      case 0:
        if (article.isPublished()) {
          return image;
        }
      default:
        return null;
    }
  }


  // see: viewer.setInput(..)
  @Override
  public Object[] getElements(Object input) {
    List<Article> list = (List<Article>) input;
    return list.toArray();
  }

}
