package com.librarymanagement.models;

import java.util.List;

public class Library {
    private int id;
    private String name;
    private String phoneNo;
    private String emailId;
    private String address;
    private List<Integer> adminUsers;

    public Library(int id, String name, String phoneNo, String emailId, String address, List<Integer> adminUsers) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        this.address = address;
        this.adminUsers = adminUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Integer> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(List<Integer> adminUsers) {
        this.adminUsers = adminUsers;
    }
}
