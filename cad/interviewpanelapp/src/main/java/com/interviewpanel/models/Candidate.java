package com.interviewpanel.models;

import com.interviewpanel.models.helpers.InterviewStatus;

public class Candidate {
    private int candidateId;
    private String candidateName;
    private String candidateEmail;
    private String candidatePhone;
    private String skills;
    private String qualification;

    private InterviewStatus interviewStatus;

    public Candidate(int candidateId, String candidateName, String candidateEmail, String candidatePhone, String skills, String qualification, InterviewStatus interviewStatus) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.candidateEmail = candidateEmail;
        this.candidatePhone = candidatePhone;
        this.skills = skills;
        this.qualification = qualification;
        this.interviewStatus = interviewStatus;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidatePhone() {
        return candidatePhone;
    }

    public void setCandidatePhone(String candidatePhone) {
        this.candidatePhone = candidatePhone;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public InterviewStatus getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(InterviewStatus interviewStatus) {
        this.interviewStatus = interviewStatus;
    }
}
