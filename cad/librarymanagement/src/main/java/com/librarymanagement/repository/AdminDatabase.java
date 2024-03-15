package com.librarymanagement.repository;

import com.librarymanagement.models.Admin;

import java.util.List;

public class AdminDatabase {
    private AdminDatabase() {}

    public static AdminDatabase adminDatabase;
    private List<Admin> adminList;

    public static AdminDatabase getInstance() {
        if(adminDatabase == null) {
            adminDatabase = new AdminDatabase();
        }
        return adminDatabase;
    }

    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void insertAdmin(Admin admin) {
        this.admin = admin;
        System.out.println("Admin details added");
    }

    public List<Admin> getAdminList() {
        return adminList;
    }

    public Admin getAdmin(int id) {
        for (Admin admin : adminList) {
            if (admin.getAdminId() == id) {
                return admin;
            }
        }
        return null;
    }
}
