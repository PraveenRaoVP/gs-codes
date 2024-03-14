package org.interviewpanel.services;

import org.interviewpanel.models.Candidate;
import org.interviewpanel.models.InterviewPanel;

import java.util.List;

public interface InterviewPanelService {
    public void addInterviewPanel();
    public void removeInterviewPanel();
    public void updateInterviewPanel();
    public InterviewPanel getInterviewPanelById(int id);
    public List<InterviewPanel> getInterviewPanelByName(String name);
    public List<InterviewPanel> getAllInterviewPanels();
    public void addCandidateToQueue(Candidate candidate);
    public void removeCandidateFromQueue(Candidate candidate);
    public void updateCandidateInQueue(Candidate candidate);

}
