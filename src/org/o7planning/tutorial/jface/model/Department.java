package org.o7planning.tutorial.jface.model;

import java.util.List;

public class Department {

  private String deptNo;
  private String deptName;
  private List<Employee> employees;

  public Department(String deptNo, String deptName, List<Employee> employees) {
    this.deptNo = deptNo;
    this.deptName = deptName;
    this.employees = employees;
  }

  public String getDeptNo() {
    return deptNo;
  }

  public void setDeptNo(String deptNo) {
    this.deptNo = deptNo;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

}
