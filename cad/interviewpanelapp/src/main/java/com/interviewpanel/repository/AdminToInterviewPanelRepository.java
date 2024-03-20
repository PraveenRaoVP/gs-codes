package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminToInterviewPanelRepository {
    // stores the adminid and the ids of interview panel created by that admin
    private Map<Integer, List<Integer>> adminToInterviewPanel = new HashMap<>();
    private static AdminToInterviewPanelRepository instance;
    private String fileNamePath = "./src/main/resources/adminToInterviewPanel.json";
    private AdminToInterviewPanelRepository() {
        pullAdminToInteriewPanelFromJSON();
    }

    public static AdminToInterviewPanelRepository getInstance() {
        if(instance == null) {
            instance = new AdminToInterviewPanelRepository();
        }
        return instance;
    }

    public void pushAdminToInterviewPanelToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(fileNamePath), adminToInterviewPanel);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pullAdminToInteriewPanelFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                adminToInterviewPanel.putAll(mapper.readValue(file, new TypeReference<Map<Integer, List<Integer>>>() {}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addAdminToInterviewPanel(int adminId, int interviewPanelId) {
        if(adminToInterviewPanel.containsKey(adminId)) {
            adminToInterviewPanel.get(adminId).add(interviewPanelId);
        } else {
            List<Integer> interviewPanelIds = new ArrayList<>();
            interviewPanelIds.add(interviewPanelId);
            adminToInterviewPanel.put(adminId, interviewPanelIds);
        }
    }

    public List<Integer> getInterviewPanelsByAdminId(int adminId) {
        return adminToInterviewPanel.get(adminId);
    }
}
