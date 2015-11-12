package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import dao.EmployeeDao;
import datasource.DataSource;
import datasource.factory.DataSourceFactory;
import domain.Employee;

public class EmployeeDaoNormalCursorImpl implements EmployeeDao {

    private DataSource dataSource;

    public EmployeeDaoNormalCursorImpl() {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        this.dataSource = dataSourceFactory.getDataSource();
    }

    private static final String GET_EMPLOYEES =
        "select emp.id, emp.name, add.street from employee emp, address add where emp.id = add.empid";

    private static final String GET_EMPLOYEE_BY_NAME =
        "select emp.id, emp.name from employee emp where emp.name = ?";

    private static final String INSERT_EMPLOYEE = "insert into employee (name) values (?)";

    private static final String INSERT_ADDRESS = "INSERT into address (street,empid) values (? , ? )";

    @Override
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<Employee>();
        Connection conn = dataSource.getConnection();
        try (PreparedStatement query = conn.prepareStatement(GET_EMPLOYEES);
                ResultSet rs = query.executeQuery();) {
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setName(rs.getString("name"));
                employee.setAddress(rs.getString("street"));
                employee.setId(rs.getLong("id"));
                employees.add(employee);
            }
        }
        return employees;
    }

    @Override
    public void addEmployee(final Employee employee) throws SQLException {
        Savepoint empSave = null;
        Connection conn = dataSource.getConnection();
        ResultSet rs = null;
        Employee addedEmployee = null;
        try (PreparedStatement insertEmployeeQuery = conn.prepareStatement(INSERT_EMPLOYEE);
                PreparedStatement insertAddressQuery = conn.prepareStatement(INSERT_ADDRESS);
                PreparedStatement getEmployeeByNameQuery = conn.prepareStatement(GET_EMPLOYEE_BY_NAME);) {

            conn.setAutoCommit(false);
            insertEmployeeQuery.setString(1, employee.getName());
            insertEmployeeQuery.executeUpdate();
            empSave = conn.setSavepoint();
            getEmployeeByNameQuery.setString(1, employee.getName());
            rs = getEmployeeByNameQuery.executeQuery();

            while (rs.next()) {
                addedEmployee = new Employee();
                addedEmployee.setName(rs.getString("name"));
                addedEmployee.setId(rs.getLong("id"));
            }

            insertAddressQuery.setString(1, employee.getAddress());
            insertAddressQuery.setLong(2, addedEmployee.getId());
            insertAddressQuery.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            if (empSave != null) {
                conn.rollback();
            }
            e.printStackTrace();
        }
    }

}
