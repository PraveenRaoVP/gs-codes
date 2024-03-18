package com.interviewpanel.repository;

import com.interviewpanel.models.InterviewPanel;
import com.interviewpanel.models.Interviewer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class InterviewPanelRepository {
    private static InterviewPanelRepository interviewPanel;

    private final List<InterviewPanel> interviewPanelList = new ArrayList<>();
    private InterviewPanelRepository() {}

    public static InterviewPanelRepository getInstance() {
        if (interviewPanel == null) {
            interviewPanel = new InterviewPanelRepository();
        }
        return interviewPanel;
    }

    public void addInterviewPanel(int interviewerId) {
        int panelId = interviewPanelList.size() + 1;
        InterviewPanel interviewPanel = new InterviewPanel(panelId, interviewerId, new LinkedList<>());
        interviewPanelList.add(interviewPanel);
    }

    public List<InterviewPanel> getInterviewPanelList() {
        return interviewPanelList;
    }

    public InterviewPanel getInterviewPanelById(int panelId) {
        for (InterviewPanel panel : interviewPanelList) {
            if (panel.getPanelId() == panelId) {
                return panel;
            }
        }
        return null;
    }

    public void removeInterviewPanel(int panelId) {
        interviewPanelList.removeIf(panel -> panel.getPanelId() == panelId);
    }
}
