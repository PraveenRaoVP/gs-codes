package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.models.Interview;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterviewRepository {
    private static InterviewRepository interviewRepository;

    private Map<Integer, Interview> interviewMap = new HashMap<>();

    private final String fileNamePath = "./src/main/resources/interviews.json";
    private InterviewRepository() {
        pullInterviewsFromJSON();
    }

    public static InterviewRepository getInstance() {
        if (interviewRepository == null) {
            interviewRepository = new InterviewRepository();
        }
        return interviewRepository;
    }

    public void pushInterviewsToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(fileNamePath), interviewMap);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pullInterviewsFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        try {
            interviewMap.putAll(mapper.readValue(file, new TypeReference<Map<Integer, Interview>>() {}));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Interview> getInterviews() {
        return List.copyOf(interviewMap.values());
    }

    public Interview getInterviewByCandidateId(int candidateId) {
        for(Interview interview: interviewMap.values()) {
            if(interview.getCandidateId() == candidateId) {
                return interview;
            }
        }
        return null;
    }
}
