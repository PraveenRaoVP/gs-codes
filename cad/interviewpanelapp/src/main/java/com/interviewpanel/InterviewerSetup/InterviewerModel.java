package com.interviewpanel.InterviewerSetup;

import com.interviewpanel.models.Interviewer;

import java.util.ArrayList;
import java.util.List;

public class InterviewerModel {
    private final InterviewerView interviewerView;
    private List<Interviewer> interviewers;

    public InterviewerModel(InterviewerView interviewerView) {
        this.interviewerView = interviewerView;
    }

    public void initialize() {
        if(interviewers==null || interviewers.isEmpty()) {
            interviewers = new ArrayList<>();
        }
    }

    public Interviewer addInterviewer(String interviewerName, String interviewerEmail, String interviewerPhone, String interviewerDesignation, String interviewerDepartment) {
        initialize();
        Interviewer interviewer = new Interviewer(interviewers.size()+1, interviewerName, interviewerEmail, interviewerPhone, interviewerDesignation, interviewerDepartment);
        interviewers.add(interviewer);
        return interviewer;
    }

    public void showInterviewerDetails() {
        for(Interviewer interviewer : interviewers) {
            interviewerView.showAlert(interviewer.toString());
        }
    }

    public void showInterviewerDetailsById(int interviewerId) {
        for(Interviewer interviewer : interviewers) {
            if(interviewer.getInterviewerId() == interviewerId) {
                interviewerView.showAlert(interviewer.toString());
            }
        }
    }

}
