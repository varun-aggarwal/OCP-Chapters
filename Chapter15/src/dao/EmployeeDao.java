package dao;

import java.sql.SQLException;
import java.util.List;

import domain.Employee;

public interface EmployeeDao {

    public List<Employee> getEmployees() throws SQLException;

    public void addEmployee(Employee employee) throws SQLException;

}
