package com.interviewpanel.MainMenu;

import java.util.Scanner;

public class MainMenuView {
    private MainMenuModel mainMenuModel;

    public MainMenuView() {
        mainMenuModel = new MainMenuModel(this);
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.MIN_VALUE;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Interview Panel Options
                    handleInterviewPanelOptions();
                    break;
                case 2:
                    // Candidate Options
                    handleCandidateOptions();
                    break;
                case 3:
                    // Admin Options
                    handleAdminOptions();
                    break;
                case 4: // Exit
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice != 4) ;
    }

    public void displayMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Interview Panel Options");
        System.out.println("2. Candidate Options");
        System.out.println("3. Admin Options");
        System.out.println("4. Exit");
    }

    public void handleInterviewPanelOptions() {
        // Interview Panel Options
        System.out.flush();
        System.out.println("Interview Panel Options");
        System.out.println("1. Add Interview Panel");
        System.out.println("2. Terminate Current Interview In Panel");
        System.out.println("3. Clear an Interview Panel");
        System.out.println("4. Clear all Interview Panels");
        System.out.println("5. Remove Interview Panel");
        System.out.println("6. View Interview Panels");
        System.out.println("7. Back to Main Menu");
        System.out.println("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        mainMenuModel.handleInterviewPanelOptions(choice);
    }

    public void handleCandidateOptions() {
        // Candidate Options
        System.out.flush();
        System.out.println("Candidate Options:-");
        System.out.println("1. Add Candidate to Panel");
        System.out.println("2. Remove Candidate from Panel");
        System.out.println("3. Change Result of Candidate");
        System.out.println("6. Back to Main Menu");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        mainMenuModel.handleCandidateOptions(choice);
    }

    public void handleAdminOptions() {
        // Admin Options
        System.out.println("Admin Options:-");
    }
}


