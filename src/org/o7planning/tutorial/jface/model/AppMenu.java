package org.o7planning.tutorial.jface.model;

import java.util.List;

public class AppMenu {

  private String menuName;
  private String menuTitle;
  private List<AppMenu> children;

  public AppMenu(String menuName, String menuTitle, List<AppMenu> children) {
    this.menuName = menuName;
    this.menuTitle = menuTitle;
    this.children = children;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getMenuTitle() {
    return menuTitle;
  }

  public void setMenuTitle(String menuTitle) {
    this.menuTitle = menuTitle;
  }

  public List<AppMenu> getChildren() {
    return children;
  }

}
