package com.interviewpanel.PanelSetup;

import com.interviewpanel.InterviewerSetup.InterviewerView;
import com.interviewpanel.models.InterviewPanel;
import com.interviewpanel.models.Interviewer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PanelOperationsView {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private PanelOperationsModel panelOperationsModel;

    public PanelOperationsView() {
        this.panelOperationsModel = new PanelOperationsModel(this);
    }

    public void createNewPanel() {
        InterviewerView interviewerView = new InterviewerView();
        System.out.println("Enter the following details:-");
        Interviewer interviewer = interviewerView.addInterviewer();
        Calendar cal = Calendar.getInstance();
        String createdAt = cal.getTime().toString();
        panelOperationsModel.createNewPanel(interviewer, createdAt);
    }

    public void showPanelDetails() {
        List<InterviewPanel> panels = panelOperationsModel.showPanelDetails();
        if(panels==null){
            System.out.println("There are no panels.");
            return;
        }
        for(InterviewPanel panel: panels) {
            System.out.println(panel);
        }
    }

    public void showAlert(String message) {
        System.out.println(message);
    }
}
