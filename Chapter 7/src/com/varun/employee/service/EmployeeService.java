package com.varun.employee.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import com.varun.employee.api.Employee;
import com.varun.employee.dao.EmployeeDao;
import com.varun.employee.util.Constants;
import com.varun.employee.util.CsvUtils;
import com.varun.exployee.exceptions.EmployeeNotValidException;
import com.varun.exployee.exceptions.FileTooBigException;
import com.varun.exployee.exceptions.NoDataFoundException;

public class EmployeeService {

    private EmployeeDao emplopyeeDao = new EmployeeDao();

    public void uploadEmployeeCsv(File file) throws IOException, FileTooBigException, NoDataFoundException {

        isFileValid(file);

        try (InputStream ios = new FileInputStream(file); Reader reader = new InputStreamReader(ios);) {

            final List<String[]> csvRows = CsvUtils.parseCsv(reader);

            for (String[] csvRow : csvRows) {
                Employee employee;
                employee = createEmployeeFromStringArray(csvRow);
                try {
                    isEmployeeValid(employee);
                } catch (EmployeeNotValidException e) {
                    System.out.println("Not a valid Employee details. Skipping this record and continuing.");
                    continue;
                }
                emplopyeeDao.addEmployee(employee);
            }
        }
    }

    private boolean isFileValid(File file) throws FileTooBigException, NoDataFoundException {

        if (file.length() < Constants.HEADER_LENGTH) {
            throw new NoDataFoundException("File contains no records.");
        }

        if (file.length() > Constants.MAX_FILE_SIZE) {
            throw new FileTooBigException("File contains too many records.");
        }

        return true;
    }

    private boolean isEmployeeValid(Employee employee) throws EmployeeNotValidException {

        if (employee.getName() == null || employee.getName().equals("")) {
            throw new EmployeeNotValidException("File contains too many records.");
        }
        return true;
    }

    private Employee createEmployeeFromStringArray(String[] data) {
        String name = data[0];
        String address = data[1];
        String postcode = data[2];
        Employee employee = new Employee(name, address, postcode);
        return employee;
    }

    public List<Employee> getEmployeeList() {

        return emplopyeeDao.getEmployesList();

    }
}
