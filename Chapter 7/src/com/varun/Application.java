package com.varun;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.varun.employee.api.Employee;
import com.varun.employee.service.EmployeeService;
import com.varun.exployee.exceptions.FileTooBigException;
import com.varun.exployee.exceptions.NoDataFoundException;

public class Application {

    private String filePath;

    private Locale locale;

    ResourceBundle rb;

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public static void main(String[] args) {
        Application emplopyeeApplication = new Application();
        do {
            initiateApplicationProperties(emplopyeeApplication);

            emplopyeeApplication.showMenu();
            emplopyeeApplication.processUserInput(emplopyeeApplication.getInputFromUser());

        } while (true);
    }

    private static void initiateApplicationProperties(Application emplopyeeApplication) {

        if (emplopyeeApplication.getLocale() == null) {
            emplopyeeApplication.setLocale(new Locale("en", "US"));
            emplopyeeApplication.rb = ResourceBundle.getBundle("Labels", emplopyeeApplication.getLocale());
        }

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
            case 3:
                changeUserPreference();
                break;
            default:
                System.out.println(rb.getString("ExitLine"));
                System.exit(0);
        }

    }

    private void changeUserPreference() {

        showUserPreferenceMenu();
        int userInput = getInputFromUser();
        processUserPreferenceOption(userInput);

    }

    private void processUserPreferenceOption(int userInput) {

        switch (userInput) {
            case 1:
                setupDucthUserPreference();
                break;
            case 2:
                setupEnglishUserPreference();
                ;
            default:
                return;
        }

    }

    private void setupDucthUserPreference() {
        this.locale = new Locale("nl", "NL");
        this.rb = ResourceBundle.getBundle("Labels", locale);
    }

    private void setupEnglishUserPreference() {
        this.locale = new Locale("en", "US");
        this.rb = ResourceBundle.getBundle("Labels", locale);
    }

    private void showUserPreferenceMenu() {

        System.out.println(rb.getString("Options"));
        System.out.println(rb.getString("DutchLanguage"));
        System.out.println(rb.getString("EnglishLanguage"));

    }

    private void printEmployeeList() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employeeList = employeeService.getEmployeeList();

        System.out.println("Employee \t Address \t Postcode \t Date of Birth \t Salary \t Email");
        for (Employee employee : employeeList) {
            printEmployeeInUserPreference(employee);
        }

    }

    private void printEmployeeInUserPreference(Employee employee) {

        DateFormat userPreferenceDateFormat = DateFormat.getDateInstance(DateFormat.LONG, this.locale);
        NumberFormat userPreferenceNumberFormat = NumberFormat.getInstance(this.locale);
        System.out.println(employee.getName() + "\t " + employee.getAddress() + "\t " + employee.getPostcode()
                + "\t " + userPreferenceDateFormat.format(employee.getDateOfBirth()) + "\t "
                + userPreferenceNumberFormat.format(employee.getSalary()) + "\t " + employee.getEmail()
                + "\n");

    }

    public void showMenu() {
        System.out.println(rb.getString("Welcome"));
        System.out.println(rb.getString("Options"));
        System.out.println(rb.getString("Option1"));
        System.out.println(rb.getString("Opyion2"));
        System.out.println(rb.getString("Option3"));
        System.out.println(rb.getString("Option4"));
        System.out.println(rb.getString("Endline"));
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
