package com.interviewpanel.models;

public class Admin {
    private int adminId;

    private String userName;
    private String adminEmail;
    private String adminPhone;
    private String adminDesignation;
    private String adminDepartment;
    private String adminOrganization;

    public Admin(int adminId, String userName, String adminEmail, String adminPhone, String adminDesignation, String adminDepartment, String adminOrganization) {
        this.adminId = adminId;
        this.userName = userName;
        this.adminEmail = adminEmail;
        this.adminPhone = adminPhone;
        this.adminDesignation = adminDesignation;
        this.adminDepartment = adminDepartment;
        this.adminOrganization = adminOrganization;
    }

    public Admin() {
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminDesignation() {
        return adminDesignation;
    }

    public void setAdminDesignation(String adminDesignation) {
        this.adminDesignation = adminDesignation;
    }

    public String getAdminDepartment() {
        return adminDepartment;
    }

    public void setAdminDepartment(String adminDepartment) {
        this.adminDepartment = adminDepartment;
    }

    public String getAdminOrganization() {
        return adminOrganization;
    }

    public void setAdminOrganization(String adminOrganization) {
        this.adminOrganization = adminOrganization;
    }
}
