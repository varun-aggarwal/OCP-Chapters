package service;

import java.sql.SQLException;
import java.util.List;

import dao.EmployeeDao;
import dao.factory.EmployeeDaoFactory;
import domain.Employee;

public class EmployeeService {

    private EmployeeDao employeeDao;

    private EmployeeDao employeeDaoConnected;

    public EmployeeService() {
        EmployeeDaoFactory employeeDaoFactory = new EmployeeDaoFactory();
        employeeDao = employeeDaoFactory.getEmployeeDao("normal");
        employeeDaoConnected = employeeDaoFactory.getEmployeeDao("connected");
    }

    public List<Employee> getEmployeeListFromNormalResultSet() throws SQLException {
        return employeeDao.getEmployees();
    }

    public void addEmployeeFromNormalConnection(Employee employee) throws SQLException {
        employeeDao.addEmployee(employee);
    }

    public List<Employee> getEmployeeListFromRowSet() throws SQLException {
        return employeeDaoConnected.getEmployees();
    }

    public void addEmployeeFromRowSet(Employee employee) throws SQLException {
        employeeDaoConnected.addEmployee(employee);
    }

}
