package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import dao.EmployeeDao;
import domain.Employee;
import listeners.impl.RowSetListenerImpl;

public class EmployeeDaoConnectedRowSet implements EmployeeDao {

    private final String url = "jdbc:postgresql://localhost:5432/employee_demo";

    private final String user = "postgres";

    private final String password = "qafe";

    private static final String GET_EMPLOYEES = "select emp.id, emp.name from employee emp";

    JdbcRowSet jrs = null;

    public EmployeeDaoConnectedRowSet() {
        initiateJDBCRowSet();
    }

    private void initiateJDBCRowSet() {
        try {
            jrs = RowSetProvider.newFactory().createJdbcRowSet();
            jrs.setCommand(GET_EMPLOYEES);
            jrs.setUrl(url);
            jrs.setUsername(user);
            jrs.setPassword(password);
            jrs.execute();
            jrs.addRowSetListener(new RowSetListenerImpl());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<Employee>();

        while (jrs.next()) {
            Employee employee = new Employee();
            employee.setName(jrs.getString("name"));
            employee.setId(jrs.getLong("id"));
            employees.add(employee);
        }
        jrs.first();
        return employees;
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        jrs.moveToInsertRow();
        jrs.updateString("name", employee.getName());
        jrs.insertRow();
        jrs.moveToCurrentRow();
    }

}
