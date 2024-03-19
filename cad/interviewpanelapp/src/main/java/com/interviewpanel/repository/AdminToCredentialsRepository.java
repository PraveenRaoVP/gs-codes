package com.interviewpanel.repository;

import java.util.HashMap;
import java.util.Map;

public class AdminToCredentialsRepository {
    private Map<Integer, Integer> adminToCredentials = new HashMap<>();
    private static AdminToCredentialsRepository instance;

    private AdminToCredentialsRepository() {
        adminToCredentials.put(1,1);
    }

    public static AdminToCredentialsRepository getInstance() {
        if(instance == null) {
            instance = new AdminToCredentialsRepository();
        }
        return instance;
    }

    public void addAdminToCredentials(int adminId, int credentialsId) {
        adminToCredentials.put(adminId, credentialsId);
    }

    public int getAdminId(int credentialsId) {
        for (Map.Entry<Integer, Integer> entry : adminToCredentials.entrySet()) {
            if (entry.getValue() == credentialsId) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
