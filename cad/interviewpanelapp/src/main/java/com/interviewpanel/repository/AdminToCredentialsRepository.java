package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AdminToCredentialsRepository {
    private Map<Integer, Integer> adminToCredentials = new HashMap<>();
    private static AdminToCredentialsRepository instance;
    private String fileNamePath = "./src/main/resources/adminToCredentials.json";

    private AdminToCredentialsRepository() {
        pullAdminToCredentialsFromJSON();
    }

    public static AdminToCredentialsRepository getInstance() {
        if(instance == null) {
            instance = new AdminToCredentialsRepository();
        }
        return instance;
    }

    public void pushAdminToCredentialsToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(fileNamePath), adminToCredentials);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pullAdminToCredentialsFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                adminToCredentials.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Integer>>() {}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
