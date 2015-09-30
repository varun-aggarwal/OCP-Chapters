package com.varun;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.varun.employee.api.Employee;
import com.varun.employee.service.EmployeeService;
import com.varun.exployee.exceptions.FileTooBigException;
import com.varun.exployee.exceptions.NoDataFoundException;

public class Main {

    String filePath;

    public static void main(String[] args) {
        Main emplopyeeApplication = new Main();
        do {
            emplopyeeApplication.showMenu();
            emplopyeeApplication.processUserInput(emplopyeeApplication.getInputFromUser());
        } while (true);
    }

    private void processUserInput(int inputFromUser) {

        switch (inputFromUser) {
            case 1:
                initiateSetupForUploadFile();
                UploadFile(filePath);
                break;
            case 2:
                printEmployeeList();
                break;
            default:
                System.exit(0);
        }

    }

    private void printEmployeeList() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employeeList = employeeService.getEmployeeList();

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

    }

    public void showMenu() {
        System.out.println("=================Welcome to Upload Employee System====================");
        System.out.println("Please enter one of below option");
        System.out.println("1 for uploading a file");
        System.out.println("2 for printing the Employee list");
        System.out.println("3 to Exit");
        System.out.println("======================================================================");
    }

    public int getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void initiateSetupForUploadFile() {
        System.out.println("Please provide file path");
        Scanner scanner = new Scanner(System.in);
        filePath = scanner.nextLine();
    }

    private void UploadFile(String filePath) {
        EmployeeService employeeService = new EmployeeService();

        assert(!filePath.equals("")) : "File Name is empty";

        try {
            employeeService.uploadEmployeeCsv(new File(filePath));
        } catch (IOException e) {
            System.out.println("Error in reading file.");
        } catch (FileTooBigException | NoDataFoundException e) {
            System.out.println("File not valid.");
        }
    }
}
