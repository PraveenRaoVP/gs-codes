package com.interviewpanel.models;

import java.util.Queue;

public class InterviewPanel {
    private int panelId;
    private Interviewer interviewer;
    private Queue<Candidate> candidates;
    private String createdAt;

    public InterviewPanel(int panelId, Interviewer interviewer, Queue<Candidate> candidates, String createdAt) {
        this.panelId = panelId;
        this.interviewer = interviewer;
        this.candidates = candidates;
        this.createdAt = createdAt;
    }

    public int getPanelId() {
        return panelId;
    }

    public void setPanelId(int panelId) {
        this.panelId = panelId;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    public Queue<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Queue<Candidate> candidates) {
        this.candidates = candidates;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
