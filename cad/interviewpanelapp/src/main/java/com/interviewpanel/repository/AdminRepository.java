package com.interviewpanel.repository;

import com.interviewpanel.models.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    private static AdminRepository adminRepository;

    private final List<Admin> adminList = new ArrayList<>();
    private AdminRepository() {}

    public static AdminRepository getInstance() {
        if (adminRepository == null) {
            adminRepository = new AdminRepository();
        }
        return adminRepository;
    }
}
