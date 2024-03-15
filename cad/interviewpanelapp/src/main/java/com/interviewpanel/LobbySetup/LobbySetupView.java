package com.interviewpanel.LobbySetup;

import java.util.Scanner;

public class LobbySetupView {
    private final LobbySetupModel lobbySetupModel;

    public LobbySetupView() {
        this.lobbySetupModel = new LobbySetupModel(this);
    }
    public void init() {
        System.out.flush();
        lobbySetupModel.startSetup();
    }
    // display the menu and take the choice of the user
    public void displayMenu() {
        System.out.flush();
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu:-");
        System.out.println("1. Add Interview Panel");
        System.out.println("2. View Interview Panels");
        System.out.println("3. Clear Interview Panel");
        System.out.println("4. Clear all Interview Panels");
        System.out.println("5. Add Candidate to Interview Panel");
        System.out.println("6. Remove Candidate from Interview Panel");
        System.out.println("7. Exit");
        System.out.println("8. Logout");
        System.out.println("Enter your choice: ");
        int n = sc.nextInt();
        lobbySetupModel.app(n);
    }

}
