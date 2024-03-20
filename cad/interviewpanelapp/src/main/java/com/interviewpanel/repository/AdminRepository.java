package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.models.Admin;
import com.interviewpanel.models.Credentials;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminRepository {
    private static AdminRepository adminRepository;

    private final Map<Integer, Admin> adminMap = new HashMap<>();
    private String fileNamePath = "admins.json"; // "admins.json
    private AdminRepository() {
        pullAdminsToJSON();
    }

    public static AdminRepository getInstance() {
        if (adminRepository == null) {
            adminRepository = new AdminRepository();
        }
        return adminRepository;
    }

    public void pushAdminsToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new java.io.File(fileNamePath), adminMap);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pullAdminsToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                adminMap.putAll(mapper.readValue(file,new TypeReference<Map<Integer, Admin>>() {}));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
