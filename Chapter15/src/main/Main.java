package main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import domain.Employee;
import service.EmployeeService;

public class Main {

    public static void main(String... args) {

        Main application = new Main();

        application.demoReadandIterateData();
        application.demoTransactionAndSavePoint();
        application.demoRowSetImplementation();
        application.demoRowSetAdditionExample();
    }

    private void demoRowSetAdditionExample() {
        Employee employee = getEmployeeFromUser();
        EmployeeService employeeService = new EmployeeService();
        try {
            employeeService.addEmployeeFromRowSet(employee);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        demoRowSetImplementation();
    }

    private void demoTransactionAndSavePoint() {
        Employee employee = getEmployeeFromUser();
        EmployeeService employeeService = new EmployeeService();
        try {
            employeeService.addEmployeeFromNormalConnection(employee);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private void demoRowSetImplementation() {
        EmployeeService employeeService = new EmployeeService();

        try {
            List<Employee> employees = employeeService.getEmployeeListFromRowSet();

            printEmployees(employees);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void demoReadandIterateData() {
        EmployeeService employeeService = new EmployeeService();

        try {
            List<Employee> employees = employeeService.getEmployeeListFromNormalResultSet();

            printEmployees(employees);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printEmployees(List<Employee> employees) {

        System.out.println("====================================================================");
        System.out.println();
        System.out.println();
        System.out.println("========================Printing List Of Employees==================");
        System.out.println("Name \t Street");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

    }

    private Employee getEmployeeFromUser() {
        System.out.println();
        System.out.println("====================================================================");
        System.out.println("Enter Employee Name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Enter Employee Address");
        String address = scanner.nextLine();
        System.out.println("====================================================================");
        Employee employee = new Employee();
        employee.setAddress(address);
        employee.setName(name);

        return employee;
    }
}
