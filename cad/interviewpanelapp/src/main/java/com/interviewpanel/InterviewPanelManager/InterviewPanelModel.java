package com.interviewpanel.InterviewPanelManager;

import com.interviewpanel.models.Interview;
import com.interviewpanel.models.InterviewPanel;
import com.interviewpanel.models.helpers.InterviewStatus;
import com.interviewpanel.models.helpers.PrintersAndFormatters;
import com.interviewpanel.repository.AdminToInterviewPanelRepository;
import com.interviewpanel.repository.InterviewPanelRepository;
import com.interviewpanel.repository.InterviewerRepository;

import java.util.List;
import java.util.stream.Collectors;

class InterviewPanelModel {
    private final InterviewPanelView interviewPanelView;

    public InterviewPanelModel(InterviewPanelView interviewPanelView) {
        this.interviewPanelView = interviewPanelView;
    }

    public void addInterviewPanel(String interviewerName, String interviewerEmail, String interviewerPhone, String interviewerDesignation, String interviewerDepartment, String interviewerOrganization) {
        // Add new Interview Panel
        // -> Push interviewer details to the interviewer database
        // -> Push the admin id and interview panel id to the AdminToInterviewPanelRepository database
        int interviewerId = InterviewerRepository.getInstance().getInterviewers().size() + 1;
        InterviewerRepository.getInstance().addInterviewer(interviewerId, interviewerName, interviewerEmail, interviewerPhone, interviewerDesignation, interviewerDepartment, interviewerOrganization);
        InterviewPanelRepository.getInstance().addInterviewPanel(interviewerId);
        AdminToInterviewPanelRepository.getInstance().addAdminToInterviewPanel(1, InterviewPanelRepository.getInstance().getInterviewPanelList().size());
        PrintersAndFormatters.showMessage("Interview Panel added successfully");
    }

    public List<InterviewPanel> viewInterviewPanels(int adminId) {
        // View Interview Panels by that admin
        // -> Display the panels created by that admin by referring to admintointerviewpanel database with the candidates' and Interviewer name present in it
        // -> Dequeue the candidate and change his interview status to UNDER_REVIEW
        List<InterviewPanel> interviewPanels = InterviewPanelRepository.getInstance().getInterviewPanelList();
        List<Integer> interviewPanelIds = AdminToInterviewPanelRepository.getInstance().getInterviewPanelsByAdminId(adminId);

        return interviewPanels.stream()
                .filter(interviewPanel -> interviewPanelIds.contains(interviewPanel.getPanelId()))
                .collect(Collectors.toList());
    }

    public void terminateCurrentInterviewInPanel(int panelId) {
        InterviewPanel interviewPanel = InterviewPanelRepository.getInstance().getInterviewPanelById(panelId);
        if(!interviewPanel.getInterviews().isEmpty()) {
            Interview interview = interviewPanel.getInterviews().poll();
            if(interview != null)
                interview.setStatus(InterviewStatus.UNDER_REVIEW);
            assert interviewPanel.getInterviews().peek() != null;
            interviewPanel.getInterviews().peek().setStatus(InterviewStatus.IN_PROGRESS);

        } else {
            PrintersAndFormatters.showMessage("No candidates in the panel");
        }
    }

    public void clearInterviewPanel(int panelId) {
        InterviewPanel interviewPanel = InterviewPanelRepository.getInstance().getInterviewPanelById(panelId);
        interviewPanel.getInterviews().clear();
    }


    public void clearAllInterviewPanels() {
        List<InterviewPanel> interviewPanels = viewInterviewPanels(1);
        for (InterviewPanel interviewPanel : interviewPanels) {
            clearInterviewPanel(interviewPanel.getPanelId());
        }
    }

    public void removeInterviewPanel(int panelId) {
        InterviewPanelRepository.getInstance().removeInterviewPanel(panelId);
        PrintersAndFormatters.showMessage("Interview Panel removed successfully");
    }
}
