package org.interviewpanel.models;

import org.interviewpanel.models.helpers.ContactInfo;

public class Interviewer {
    private int employeeId;
    private String employeeName;
    private String employeeDesignation;
    private String employeeDepartment;
    private ContactInfo employeeContact;
    private int yearsOfExperience;

    public Interviewer(int employeeId, String employeeName, String employeeDesignation, String employeeDepartment, ContactInfo employeeContact, int yearsOfExperience) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDesignation = employeeDesignation;
        this.employeeDepartment = employeeDepartment;
        this.employeeContact = employeeContact;
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public ContactInfo getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(ContactInfo employeeContact) {
        this.employeeContact = employeeContact;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return "Interviewer{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeDesignation='" + employeeDesignation + '\'' +
                ", employeeDepartment='" + employeeDepartment + '\'' +
                ", employeeContact=" + employeeContact +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
