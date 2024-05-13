package assignments.assignment7_classesandobjects_encapulation;

import java.util.UUID;

/*2. Create an Employee DTO class. Create appropriate instance variables, static variables and methods with appropriate access modifiers. Maintain encapsulation by giving controlled access to employee's PII.
 */

public class Employee {
    private int employeeId;
    private String employeeName;
    private String employeeDesignation;
    private String employeeDepartment;
    private String employeeAddress;
    private String employeeEmail;
    private String employeePhone;
    private double employeeSalary;
    private static String companyName = "XYZ";

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public static String getCompanyName() {
        return companyName;
    }

    public static void setCompanyName(String companyName) {
        Employee.companyName = companyName;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
}
