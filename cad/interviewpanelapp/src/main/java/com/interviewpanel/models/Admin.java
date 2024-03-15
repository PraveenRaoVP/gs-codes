package com.interviewpanel.models;

public class Admin {
    private int adminId;
    private String adminName;
    private String adminEmail;
    private String adminPhone;
    private String adminDesignation;

    public Admin(int adminId, String adminName, String adminEmail, String adminPhone, String adminDesignation) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPhone = adminPhone;
        this.adminDesignation = adminDesignation;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
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
}
