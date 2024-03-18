package com.interviewpanel.repository;

import com.interviewpanel.models.Interviewer;

import java.util.ArrayList;
import java.util.List;

public class InterviewerRepository {
    private List<Interviewer> interviewers = new ArrayList<>();

    private static InterviewerRepository instance;

    private InterviewerRepository() {}

    public static InterviewerRepository getInstance() {
        if (instance == null) {
            instance = new InterviewerRepository();
        }
        return instance;
    }

    public List<Interviewer> getInterviewers() {
        return interviewers;
    }

    public void addInterviewer(int interviewerId, String interviewerName, String interviewerEmail, String interviewerPhone, String interviewerDesignation, String interviewerDepartment, String interviewerOrganization) {
        Interviewer interviewer = new Interviewer(interviewerId, interviewerName, interviewerEmail, interviewerPhone, interviewerDesignation, interviewerDepartment, interviewerOrganization);
        interviewers.add(interviewer);
    }
}
