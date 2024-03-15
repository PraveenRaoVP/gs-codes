package com.interviewpanel.PanelSetup;

import com.interviewpanel.models.InterviewPanel;
import com.interviewpanel.models.Interviewer;

import java.util.ArrayList;
import java.util.List;

public class PanelOperationsModel {
    private final PanelOperationsView panelOperationsView;
    public List<InterviewPanel> interviewPanels;

    public PanelOperationsModel(PanelOperationsView panelOperationsView) {
        this.panelOperationsView = panelOperationsView;
    }

    public void initialize() {
        if(interviewPanels==null || interviewPanels.isEmpty()) {
            interviewPanels = new ArrayList<>();
        }
    }

    public void createNewPanel(Interviewer interviewer, String createdAt) {
        initialize();
        InterviewPanel interviewPanel = new InterviewPanel(interviewPanels.size()+1, interviewer, null, createdAt);
        interviewPanels.add(interviewPanel);
        panelOperationsView.showAlert("Panel created successfully");
    }

    public List<InterviewPanel> showPanelDetails() {
        return interviewPanels;
    }

}
