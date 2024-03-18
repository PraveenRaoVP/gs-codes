package com.interviewpanel.InterviewPanelManager;

import com.interviewpanel.models.InterviewPanel;

import java.util.List;
import java.util.Scanner;

public class InterviewPanelView {
    private final InterviewPanelModel interviewPanelModel;

    public InterviewPanelView() {
        interviewPanelModel = new InterviewPanelModel(this);
    }

    public void addInterviewPanel() {
//        1. Add new Interview Panel
//                -> Get details of interviewer
//                -> Push interviewer details to the interviewer database
//	              -> Push the admin id and interview panel id to the AdminToInterviewPanelRepository database
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Interviewer Details:-");
        System.out.print("Enter Interviewer Name: ");
        String interviewerName = scanner.nextLine();
        System.out.print("Enter Interviewer Email: ");
        String interviewerEmail = scanner.nextLine();
        System.out.print("Enter Interviewer Phone: ");
        String interviewerPhone = scanner.nextLine();
        System.out.print("Enter Interviewer Designation: ");
        String interviewerDesignation = scanner.nextLine();
        System.out.print("Enter Interviewer Department: ");
        String interviewerDepartment = scanner.nextLine();
        System.out.print("Enter Interviewer Organization: ");
        String interviewerOrganization = scanner.nextLine();
        interviewPanelModel.addInterviewPanel(interviewerName, interviewerEmail, interviewerPhone, interviewerDesignation, interviewerDepartment, interviewerOrganization);
    }

    public void clearInterviewPanel() {
        System.out.println("Do you want to view the panels created by you? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("Y")) {
            viewInterviewPanels(1);
        }
        System.out.println("Enter the panel ID: ");
        int panelId = scanner.nextInt();
        interviewPanelModel.clearInterviewPanel(panelId);
    }

    public void clearAllInterviewPanels() {

    }

    public void removeInterviewPanel() {

    }

    public void viewInterviewPanels(int adminId) {
//        3. View Interview Panels by that admin
//	        -> Display the panels created by that admin
        List<InterviewPanel> interviewPanels = interviewPanelModel.viewInterviewPanels(adminId);
    }

    public void terminateCurrentInterviewInPanel() {
//        2. Terminate Current Interview In a panel:
//	        -> Display the panels created by that admin by referring to admintointerviewpanel database with the candidates' and Interviewer name present in it
//	        -> Dequeue the candidate and change his interview status to UNDER_REVIEW
        System.out.println("Do you want to view the panels created by you? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("Y")) {
            viewInterviewPanels(1);
        }
        System.out.println("Enter the panel ID: ");
        int panelId = scanner.nextInt();
        interviewPanelModel.terminateCurrentInterviewInPanel(panelId);
    }
}
