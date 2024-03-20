package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.models.Interviewer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterviewerRepository {
//    private List<Interviewer> interviewers = new ArrayList<>();
    private Map<Integer, Interviewer> interviewerMap = new HashMap<>();
    private static InterviewerRepository instance;

    private String fileNamePath = "./src/main/resources/interviewers.json";

    private InterviewerRepository() {
        pullInterviewersFromJSON();
    }

    public static InterviewerRepository getInstance() {
        if (instance == null) {
            instance = new InterviewerRepository();
        }
        return instance;
    }

    public List<Interviewer> getInterviewers() {
        return List.copyOf(interviewerMap.values());
    }

    public void addInterviewer(int interviewerId, String interviewerName, String interviewerEmail, String interviewerPhone, String interviewerDesignation, String interviewerDepartment, String interviewerOrganization) {
        Interviewer interviewer = new Interviewer(interviewerId, interviewerName, interviewerEmail, interviewerPhone, interviewerDesignation, interviewerDepartment, interviewerOrganization);
        interviewerMap.put(interviewerId, interviewer);
    }

    public Interviewer getInterviewerById(int interviewerId) {
        return interviewerMap.get(interviewerId);
    }

    public void pushInterviewersToJSON() {
        ObjectMapper mapper = new ObjectMapper();

        try {
           mapper.writeValue(new File(fileNamePath), interviewerMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pullInterviewersFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                interviewerMap.putAll(mapper.readValue(new File(fileNamePath), new TypeReference<Map<Integer, Interviewer>>() {}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
