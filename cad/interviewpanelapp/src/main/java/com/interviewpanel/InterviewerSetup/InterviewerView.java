package com.interviewpanel.InterviewerSetup;

import com.interviewpanel.models.Interviewer;

import java.util.Scanner;

public class InterviewerView {
    private InterviewerModel interviewerModel;

    public InterviewerView() {
        this.interviewerModel = new InterviewerModel(this);
    }

    public void init() {
        interviewerModel.initialize();
    }

    public Interviewer addInterviewer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the details of the interviewer: ");
        System.out.println("Enter the interviewer name: ");
        String interviwerName = sc.nextLine();
        System.out.println("Enter the interviewer email: ");
        String interviewerEmail = sc.nextLine();
        System.out.println("Enter the interviewer phone: ");
        String interviewerPhone = sc.nextLine();
        System.out.println("Enter the interviewer designation: ");
        String interviewerDesignation = sc.nextLine();
        System.out.println("Enter the interviewer department: ");
        String interviewerDepartment = sc.nextLine();
        return interviewerModel.addInterviewer(interviwerName, interviewerEmail, interviewerPhone, interviewerDesignation, interviewerDepartment);
    }

    public void showAlert(String message) {
        System.out.println(message);
    }
}
