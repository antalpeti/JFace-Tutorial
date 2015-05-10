package org.o7planning.tutorial.jface.model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

  private static List<Article> articleList;
  private static List<Department> departmentList;
  private static List<AppMenu> appMenuList;

  public static List<Article> getArticles() {
    if (articleList == null) {
      articleList = new ArrayList<Article>();
      articleList.add(new Article("Java basic", "Tom", true));
      articleList.add(new Article("Hibernate for beginners", "Tran", true));
      articleList.add(new Article("Maven for beginners", "Smith", false));
    }
    return articleList;
  }

  public static List<Department> getDepartments() {
    if (departmentList == null) {

      Employee emp11 = new Employee("E11", "Michael", "Smith");
      Employee emp12 = new Employee("E12", "Susan", "Barker");
      List<Employee> empList1 = new ArrayList<Employee>();
      empList1.add(emp11);
      empList1.add(emp12);

      Department dept1 = new Department("D01", "Operation", empList1);
      //
      Employee emp21 = new Employee("E21", "Robert", "Tyler");
      List<Employee> empList2 = new ArrayList<Employee>();
      empList2.add(emp21);

      Department dept2 = new Department("D02", "Adminstration", empList2);
      //
      departmentList = new ArrayList<Department>();

      departmentList.add(dept1);
      departmentList.add(dept2);
    }
    return departmentList;
  }

  public static List<AppMenu> getAppMenus() {
    if (appMenuList == null) {
      appMenuList = new ArrayList<AppMenu>();

      AppMenu appMenu31 = new AppMenu("ErrorLog", "Error Log", null);
      AppMenu appMenu32 = new AppMenu("ProjectExplorer", "Project Explorer", null);

      List<AppMenu> list3 = new ArrayList<AppMenu>();
      list3.add(appMenu31);
      list3.add(appMenu32);

      AppMenu appMenu21 = new AppMenu("ShowView", "Show View", list3);
      AppMenu appMenu22 = new AppMenu("ClosePerspective", "Close perspective...", null);
      List<AppMenu> list2 = new ArrayList<AppMenu>();
      list2.add(appMenu21);
      list2.add(appMenu22);

      AppMenu appMenu1 = new AppMenu("Window", "Window", list2);

      appMenuList.add(appMenu1);
    }
    return appMenuList;
  }

}
