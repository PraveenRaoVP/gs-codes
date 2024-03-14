package org.interviewpanel.services.impl;

import org.interviewpanel.models.Interviewer;
import org.interviewpanel.services.InterviewerService;

import java.util.ArrayList;
import java.util.List;

public class InterviewerServiceImpl implements InterviewerService {
    List<Interviewer> interviewers;

    public InterviewerServiceImpl(List<Interviewer> interviewers) {
        this.interviewers = interviewers;
    }

    @Override
    public void addInterviewer(Interviewer interviewer) {

    }

    @Override
    public void removeInterviewer() {

    }

    @Override
    public void updateInterviewer() {

    }

    @Override
    public Interviewer getInterviewerById(int id) {
        return null;
    }

    @Override
    public List<Interviewer> getInterviewerByName(String name) {
        return null;
    }

    @Override
    public List<Interviewer> getAllInterviewers() {
        return null;
    }
}
