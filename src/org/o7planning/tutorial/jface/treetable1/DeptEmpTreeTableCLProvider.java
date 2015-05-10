package org.o7planning.tutorial.jface.treetable1;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.o7planning.tutorial.jface.model.Department;
import org.o7planning.tutorial.jface.model.Employee;
import org.o7planning.tutorial.jface.provider.AbstractTreeTableContentLabelProvider;

public class DeptEmpTreeTableCLProvider extends AbstractTreeTableContentLabelProvider {

  private Image EMP_IMAGE;
  private Image DEPT_IMAGE;

  public DeptEmpTreeTableCLProvider() {

    DEPT_IMAGE = Display.getCurrent().getSystemImage(SWT.ICON_INFORMATION);

    EMP_IMAGE = Display.getCurrent().getSystemImage(SWT.ICON_ERROR);
  }

  @Override
  public Object[] getElements(Object inputElement) {
    List<Department> departments = (List<Department>) inputElement;
    return departments.toArray();
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof Department) {
      Department dept = (Department) parentElement;
      List<Employee> emps = dept.getEmployees();
      return emps == null ? null : emps.toArray();
    } else {
      // Employee has no children
      return null;
    }
  }

  @Override
  public boolean hasChildren(Object element) {
    if (element instanceof Department) {
      Department dept = (Department) element;
      List<Employee> emps = dept.getEmployees();
      return emps != null && !emps.isEmpty();
    } else {
      // Employee has no children
      return false;
    }
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    if (element instanceof Department) {
      Department dept = (Department) element;
      switch (columnIndex) {
        case 0:
          return dept.getDeptNo();
        case 1:
          return dept.getDeptName();
        default:
          return null;
      }
    } else {
      Employee emp = (Employee) element;
      switch (columnIndex) {
        case 2:
          return emp.getEmpNo();
        case 3:
          return emp.getFirstName();
        case 4:
          return emp.getLastName();
        default:
          return null;
      }
    }
  }

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    if (element instanceof Department) {
      switch (columnIndex) {
        case 0:
          return DEPT_IMAGE;
        default:
          return null;
      }
    } else {
      switch (columnIndex) {
        case 2:
          return EMP_IMAGE;
        default:
          return null;
      }
    }
  }

}
