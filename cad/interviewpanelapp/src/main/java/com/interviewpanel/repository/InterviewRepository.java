package com.interviewpanel.repository;

import com.interviewpanel.models.Interview;

import java.util.ArrayList;
import java.util.List;

public class InterviewRepository {
    private static InterviewRepository interviewRepository;

    private final List<Interview> interviewList = new ArrayList<>();
    private InterviewRepository() {}

    public static InterviewRepository getInstance() {
        if (interviewRepository == null) {
            interviewRepository = new InterviewRepository();
        }
        return interviewRepository;
    }

    public List<Interview> getInterviews() {
        return interviewList;
    }

    public Interview getInterviewByCandidateId(int candidateId) {
        for (Interview interview : interviewList) {
            if (interview.getCandidateId() == candidateId) {
                return interview;
            }
        }
        return null;
    }
}
