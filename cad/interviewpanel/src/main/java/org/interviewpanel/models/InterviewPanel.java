package org.interviewpanel.models;

import java.util.Date;
import java.util.Queue;

public class InterviewPanel {
    private int panelId;
    private Interviewer interviewer;
    private Queue<Candidate> candidates;
    private String jobDescription;
    private Date createdAt;
    private Date endTime;
}
