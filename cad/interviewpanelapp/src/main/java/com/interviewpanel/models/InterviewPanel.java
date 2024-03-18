package com.interviewpanel.models;

import java.util.Queue;

public class InterviewPanel {
    private int panelId;
    private int interviewerId;
    private Queue<Interview> candidates;

    public InterviewPanel(int panelId, int interviewerId, Queue<Interview> candidates) {
        this.panelId = panelId;
        this.interviewerId = interviewerId;
        this.candidates = candidates;
    }

    public int getPanelId() {
        return panelId;
    }

    public void setPanelId(int panelId) {
        this.panelId = panelId;
    }

    public int getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(int interviewerId) {
        this.interviewerId = interviewerId;
    }

    public Queue<Interview> getCandidates() {
        return candidates;
    }

    public void setCandidates(Queue<Interview> candidates) {
        this.candidates = candidates;
    }
}
