package com.interviewpanel.InterviewPanelManager;

import com.interviewpanel.models.Candidate;
import com.interviewpanel.models.Interview;
import com.interviewpanel.models.InterviewPanel;
import com.interviewpanel.models.Interviewer;
import com.interviewpanel.repository.CacheMemory;
import com.interviewpanel.repository.CandidatesRepository;
import com.interviewpanel.repository.InterviewerRepository;

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
        int panelId = getPanelIdFromUser();
        interviewPanelModel.clearInterviewPanel(panelId);
        System.out.println("Panel cleared successfully");
    }

    public void clearAllInterviewPanels() {
        interviewPanelModel.clearAllInterviewPanels();
        System.out.println("Cleared all interview panels successfully.");
    }

    public void removeInterviewPanel() {
        int panelId = getPanelIdFromUser();
        interviewPanelModel.removeInterviewPanel(panelId);
    }

    public void viewInterviewPanels(int adminId) {
//        3. View Interview Panels by that admin
//	        -> Display the panels created by that admin

        List<InterviewPanel> interviewPanels = interviewPanelModel.viewInterviewPanels(adminId);
        if(interviewPanels.isEmpty()) {
            System.out.println("No panels created by you");
            return;
        }
        System.out.println("Panel ID \t Interviewer Name \t Interviewer Email \t Candidates");
        for(InterviewPanel interviewPanel : interviewPanels) {
            Interviewer interviewer = InterviewerRepository.getInstance().getInterviewerById(interviewPanel.getInterviewerId());
            System.out.print(interviewPanel.getPanelId() + "\t" + interviewer.getInterviewerName() + "\t" + interviewer.getInterviewerEmail() + "\t");
            if(interviewPanel.getInterviews().isEmpty()) {
                System.out.println("N/A");
                continue;
            }
            for(Interview interview: interviewPanel.getInterviews()) {
                Candidate candidate = CandidatesRepository.getInstance().getCandidateById(interview.getCandidateId());
                assert candidate != null;
                if(interviewPanel.getInterviews().peek() == interview) {
                    System.out.print(candidate.getName()+"(In Progress), ");
                } else {
                    System.out.print(candidate.getName() + ", ");
                }
            }
            System.out.println();
        }
    }

    public void terminateCurrentInterviewInPanel() {
//        2. Terminate Current Interview In a panel:
//	        -> Display the panels created by that admin by referring to admintointerviewpanel database with the candidates' and Interviewer name present in it
//	        -> Dequeue the candidate and change his interview status to UNDER_REVIEW
        int panelId = getPanelIdFromUser();
        if(!interviewPanelModel.checkIfValidPanelId(panelId)) {
            System.out.println("Invalid panel id");
            return;
        }
        interviewPanelModel.terminateCurrentInterviewInPanel(panelId);
    }

    private int getPanelIdFromUser() {
        System.out.println("Do you want to view the panels created by you? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("Y")) {
            viewInterviewPanels(CacheMemory.getInstance().getCurrentAdmin());
        }
        System.out.println("Enter the panel ID: ");
        return scanner.nextInt();
    }
}
