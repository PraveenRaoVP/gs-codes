package org.interviewpanel.models;

import org.interviewpanel.models.helpers.ContactInfo;

public class Admin {
    private int adminId;
    private String adminName;
    private String adminUserName;
    private String adminPassword;
    private String adminDesignation;
    private ContactInfo adminContact;

    public Admin(int adminId, String adminName, String adminUserName, String adminPassword, String adminDesignation, ContactInfo adminContact) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
        this.adminDesignation = adminDesignation;
        this.adminContact = adminContact;
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

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminDesignation() {
        return adminDesignation;
    }

    public void setAdminDesignation(String adminDesignation) {
        this.adminDesignation = adminDesignation;
    }

    public ContactInfo getAdminContact() {
        return adminContact;
    }

    public void setAdminContact(ContactInfo adminContact) {
        this.adminContact = adminContact;
    }
}
