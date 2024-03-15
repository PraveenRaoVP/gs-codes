package com.interviewpanel.LobbySetup;

import com.interviewpanel.auth.LoginView;
import com.interviewpanel.models.InterviewPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LobbySetupModel {
    private final LobbySetupView lobbySetupView;
    private List<InterviewPanel> interviewPanels;
    public LobbySetupModel(LobbySetupView lobbySetupView) {
        this.lobbySetupView = lobbySetupView;
    }

    public void startSetup() {
        if(interviewPanels == null || interviewPanels.isEmpty()){ // if no interview panel is present
            interviewPanels = new ArrayList<>();
        }
        // display menu until user exits
        while(true) {
            lobbySetupView.displayMenu();
        }
    }

    public void app(int n) {
        switch(n){
            case 7:
                System.exit(0);
                break;
            case 8:
                // logout
                LoginView loginView = new LoginView();
                loginView.init();
                System.exit(0);
            case 1:
                // add interview panel
                System.out.println("Add Interview Panel");
                break;
            case 2:
                // view interview panels
                System.out.println("View Interview Panels");
                break;
            case 3:
                // clear interview panel
                System.out.println("Clear Interview Panel");
                break;
            case 4:
                // clear all interview panels
                System.out.println("Clear all Interview Panels");
                break;
            case 5:
                // add candidate to interview panel
                System.out.println("Add Candidate to Interview Panel");
                break;
            case 6:
                // remove candidate from interview panel
                System.out.println("Remove Candidate from Interview Panel");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }


}
