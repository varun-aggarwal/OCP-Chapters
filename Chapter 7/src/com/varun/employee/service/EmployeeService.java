package com.varun.employee.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.varun.employee.api.Employee;
import com.varun.employee.dao.EmployeeDao;
import com.varun.employee.util.Constants;
import com.varun.employee.util.CsvUtils;
import com.varun.exployee.exceptions.EmployeeNotValidException;
import com.varun.exployee.exceptions.FileTooBigException;
import com.varun.exployee.exceptions.NoDataFoundException;

public class EmployeeService {

    private EmployeeDao emplopyeeDao = new EmployeeDao();

    private static final String EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /*
     * ^ #start of the line [_A-Za-z0-9-\\+]+ # must start with string in the bracket [ ], must contains one
     * or more (+) ( # start of group #1 \\.[_A-Za-z0-9-]+ # follow by a dot "." and string in the bracket [
     * ], must contains one or more (+) )* # end of group #1, this group is optional (*)
     * 
     * @ # must contains a "@" symbol [A-Za-z0-9-]+ # follow by string in the bracket [ ], must contains one
     * or more (+) ( # start of group #2 - first level TLD checking \\.[A-Za-z0-9]+ # follow by a dot "." and
     * string in the bracket [ ], must contains one or more (+) )* # end of group #2, this group is optional
     * (*) ( # start of group #3 - second level TLD checking \\.[A-Za-z]{2,} # follow by a dot "." and string
     * in the bracket [ ], with minimum length of 2 ) # end of group #3 $ #end of the line
     */
    public void uploadEmployeeCsv(File file) throws IOException, FileTooBigException, NoDataFoundException {

        isFileValid(file);

        try (InputStream ios = new FileInputStream(file); Reader reader = new InputStreamReader(ios);) {

            final List<String[]> csvRows = CsvUtils.parseCsv(reader);

            for (String[] csvRow : csvRows) {
                Employee employee;
                try {
                    employee = createEmployeeFromStringArray(csvRow);
                    isEmployeeValid(employee);
                } catch (EmployeeNotValidException | ParseException e) {
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

    private Employee createEmployeeFromStringArray(String[] data) throws ParseException {

        DateFormat df = new SimpleDateFormat("DD/MM/YYYY");

        String name = data[0];
        String address = data[1];
        String postcode = data[2];
        String dateOfBirth = data[3];
        String salary = data[4];
        String email = data[5];

        Employee employee = new Employee(name, address, postcode);
        if (dateOfBirth != null && !dateOfBirth.equals("")) {
            employee.setDateOfBirth(df.parse(dateOfBirth));
        }
        if (salary != null && !salary.equals("")) {
            employee.setSalary(Double.parseDouble(salary));
        }

        if (email != null && !email.equals("") && isEmailValid(email)) {
            employee.setEmail(email);
        }

        return employee;
    }

    public List<Employee> getEmployeeList() {
        return emplopyeeDao.getEmployesList();
    }

    private boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
