package org.interviewpanel.services;

import org.interviewpanel.models.Interviewer;

import java.util.List;

public interface InterviewerService {
    public void addInterviewer(Interviewer interviewer);
    public void removeInterviewer(int interviewerId);
    public void updateInterviewer(int interviewerId, Interviewer interviewer);
    public Interviewer getInterviewerById(int id);
    public List<Interviewer> getInterviewerByName(String name);
    public List<Interviewer> getAllInterviewers();
}
