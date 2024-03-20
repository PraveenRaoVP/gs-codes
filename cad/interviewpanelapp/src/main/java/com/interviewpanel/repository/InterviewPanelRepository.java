package com.interviewpanel.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewpanel.models.InterviewPanel;

import java.io.File;
import java.lang.reflect.Type;
import java.util.*;

public class InterviewPanelRepository {
    private static InterviewPanelRepository interviewPanelRepository;

//    private final List<InterviewPanel> interviewPanelList = new ArrayList<>();
    private final Map<Integer, InterviewPanel> interviewPanelMap = new HashMap<>();
    private final String fileNamePath = "./src/main/resources/interviewPanels.json";

    private InterviewPanelRepository() {
        pullInterviewPanelFromJSON();
    }

    public static InterviewPanelRepository getInstance() {
        if (interviewPanelRepository == null) {
            interviewPanelRepository = new InterviewPanelRepository();
        }
        return interviewPanelRepository;
    }

    public void pushInterviewPanelToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(fileNamePath), interviewPanelMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void pullInterviewPanelFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileNamePath);
        if(file.exists()) {
            try {
                interviewPanelMap.putAll(mapper.readValue(file, new TypeReference<Map<Integer, InterviewPanel>>() {}));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addInterviewPanel(int interviewerId) {
        int panelId = interviewPanelMap.size() + 1;
        InterviewPanel interviewPanel = new InterviewPanel(panelId, interviewerId, new LinkedList<>());
        interviewPanelMap.put(panelId, interviewPanel);
    }

    public List<InterviewPanel> getInterviewPanelList() {
        return new ArrayList<>(interviewPanelMap.values());
    }

    public InterviewPanel getInterviewPanelById(int panelId) {
        return interviewPanelMap.get(panelId);
    }

    public void removeInterviewPanel(int panelId) {
        interviewPanelMap.remove(panelId);
    }

    public List<InterviewPanel> getInterviewPanelsByListOfInterviewPanelIds(int adminId) {
        List<InterviewPanel> interviewPanels = new ArrayList<>();
        List<Integer> interviewPanelIds = AdminToInterviewPanelRepository.getInstance().getInterviewPanelsByAdminId(adminId);
        for (int panelId : interviewPanelIds) {
            interviewPanels.add(getInterviewPanelById(panelId));
        }
        return interviewPanels;

    }

    public void removeCandidateFromInterviewPanel(int candidateId) {
        for (InterviewPanel interviewPanel : interviewPanelMap.values()) {
            interviewPanel.getInterviews().removeIf(interview -> interview.getCandidateId() == candidateId);
        }
    }
}
