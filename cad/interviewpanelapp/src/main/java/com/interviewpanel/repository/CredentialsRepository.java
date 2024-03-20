package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.models.Credentials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CredentialsRepository {
    private final Map<Integer, Credentials> credentialsMap = new HashMap<>();
    private String fileNamePath = "./src/main/resources/credentials.json";
    private static CredentialsRepository credentialsRepository;

    private CredentialsRepository() {
        pullCredentialsFromJSON();
    }

    public static CredentialsRepository getInstance() {
        if (credentialsRepository == null) {
            credentialsRepository = new CredentialsRepository();
        }
        return credentialsRepository;
    }

    public void pushCredentialsToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new java.io.File(fileNamePath), credentialsMap);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pullCredentialsFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<Integer, Credentials> data = mapper.readValue(new java.io.File(fileNamePath), new TypeReference<Map<Integer, Credentials>>() {});
            credentialsMap.putAll(data);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfUsernamePresent(String username) {
        return credentialsMap.values().stream().anyMatch(cred -> cred.getUsername().equals(username));
    }
    public String getPassword(String username) {
        return credentialsMap.values().stream().filter(cred -> cred.getUsername().equals(username)).findFirst().get().getPassword();
    }
    public boolean checkDuplicateUsername(String username) {
        return credentialsMap.values().stream().anyMatch(cred -> cred.getUsername().equals(username));
    }

    public int getCredentialsId(String username) {
        return credentialsMap.values().stream().filter(cred -> cred.getUsername().equals(username)).findFirst().get().getCredId();
    }
}
