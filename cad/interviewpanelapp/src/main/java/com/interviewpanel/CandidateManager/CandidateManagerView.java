package com.interviewpanel.CandidateManager;

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

        candidateManagerModel.addCandidate(name, email, phone, position, skills, address);
    }

    public void removeCandidate() {

    }

    public void viewCandidates() {

    }

    public void changeResultOfCandidate() {

    }
}
