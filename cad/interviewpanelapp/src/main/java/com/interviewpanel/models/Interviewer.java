package com.interviewpanel.models;

public class Interviewer {
    private int interviewerId;
    private String interviewerName;
    private String interviewerEmail;
    private String interviewerPhone;
    private String interviewerDesignation;
    private String interviewerDepartment;

    public Interviewer(int interviewerId, String interviewerName, String interviewerEmail, String interviewerPhone, String interviewerDesignation, String interviewerDepartment) {
        this.interviewerId = interviewerId;
        this.interviewerName = interviewerName;
        this.interviewerEmail = interviewerEmail;
        this.interviewerPhone = interviewerPhone;
        this.interviewerDesignation = interviewerDesignation;
        this.interviewerDepartment = interviewerDepartment;
    }

    public int getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(int interviewerId) {
        this.interviewerId = interviewerId;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public String getInterviewerEmail() {
        return interviewerEmail;
    }

    public void setInterviewerEmail(String interviewerEmail) {
        this.interviewerEmail = interviewerEmail;
    }

    public String getInterviewerPhone() {
        return interviewerPhone;
    }

    public void setInterviewerPhone(String interviewerPhone) {
        this.interviewerPhone = interviewerPhone;
    }

    public String getInterviewerDesignation() {
        return interviewerDesignation;
    }

    public void setInterviewerDesignation(String interviewerDesignation) {
        this.interviewerDesignation = interviewerDesignation;
    }

    public String getInterviewerDepartment() {
        return interviewerDepartment;
    }

    public void setInterviewerDepartment(String interviewerDepartment) {
        this.interviewerDepartment = interviewerDepartment;
    }

    @Override
    public String toString() {
        return "Interviewer{" +
                "interviewerId=" + interviewerId +
                ", interviewerName='" + interviewerName + '\'' +
                ", interviewerEmail='" + interviewerEmail + '\'' +
                ", interviewerPhone='" + interviewerPhone + '\'' +
                ", interviewerDesignation='" + interviewerDesignation + '\'' +
                ", interviewerDepartment='" + interviewerDepartment + '\'' +
                '}';
    }
}
