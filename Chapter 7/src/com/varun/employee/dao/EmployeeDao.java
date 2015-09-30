package com.varun.employee.dao;

import java.util.ArrayList;
import java.util.List;

import com.varun.employee.api.Employee;

public class EmployeeDao {

    private static List<Employee> EmployeeList = new ArrayList<Employee>();

    public void addEmployee(Employee employee) {
        EmployeeList.add(employee);
    }

    public List<Employee> getEmployesList() {
        return EmployeeList;
    }
}
