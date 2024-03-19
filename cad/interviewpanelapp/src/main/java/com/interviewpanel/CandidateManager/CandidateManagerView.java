package com.interviewpanel.CandidateManager;

import com.interviewpanel.InterviewPanelManager.InterviewPanelView;
import com.interviewpanel.models.Candidate;
import com.interviewpanel.models.helpers.InterviewStatus;
import com.interviewpanel.repository.CacheMemory;

import java.util.Scanner;

public class CandidateManagerView {
    private final CandidateManagerModel candidateManagerModel;

    public CandidateManagerView() {
        candidateManagerModel = new CandidateManagerModel(this);
    }

    public void addCandidate() {
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the details of the candidate:-");
        System.out.print("Enter the name of the candidate: ");
        String name = scanner.nextLine();
        System.out.print("Enter the email of the candidate: ");
        String email = scanner.nextLine();
        System.out.print("Enter the phone number of the candidate: ");
        String phone = scanner.nextLine();
        System.out.print("Enter the position the candidate is interviewing for: ");
        String position = scanner.nextLine();
        System.out.print("Enter the skills seperated by commas(,): ");
        String skills = scanner.nextLine();
        System.out.println("Enter the address of the employee: ");
        String address = scanner.nextLine();

        InterviewPanelView interviewPanelView = new InterviewPanelView();
        interviewPanelView.viewInterviewPanels(CacheMemory.getInstance().getCurrentAdmin());

        System.out.println("Enter the interviewer id: ");
        int interviewerId = scanner.nextInt();
        candidateManagerModel.addCandidate(name, email, phone, position, skills, address, interviewerId);
        System.out.println("Candidate added successfully");
    }

    public void removeCandidate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the candidate id: ");
        int candidateId = scanner.nextInt();
        candidateManagerModel.removeCandidate(candidateId);
        System.out.println("Candidate removed successfully");
    }

    public void viewCandidates() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the candidate id: ");
        int candidateId = scanner.nextInt();
        Candidate candidate = candidateManagerModel.viewCandidate(candidateId);
        System.out.println("Candidate Id: " + candidate.getCandidateId());
        System.out.println("Candidate Name: " + candidate.getName());
        System.out.println("Candidate Email: " + candidate.getEmail());
        System.out.println("Candidate Phone: " + candidate.getPhone());
        System.out.println("Candidate Position: " + candidate.getPositionInterviewing());
        System.out.println("Candidate Skills: " + candidate.getSkills());
        System.out.println("Candidate Address: " + candidate.getAddress());
    }

    public void changeResultOfCandidate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the candidate id: ");
        int candidateId = scanner.nextInt();
        System.out.println("Enter the result of the candidate: ");
        System.out.println("1. SELECTED");
        System.out.println("2. REJECTED");
        int result = scanner.nextInt();
        InterviewStatus realResult;
        if(result==1) realResult = InterviewStatus.SELECTED;
        else if(result==2) realResult = InterviewStatus.REJECTED;
        else {
            System.out.println("Invalid choice");
            return;
        }
        candidateManagerModel.changeResultOfCandidate(candidateId, realResult);
    }
}
