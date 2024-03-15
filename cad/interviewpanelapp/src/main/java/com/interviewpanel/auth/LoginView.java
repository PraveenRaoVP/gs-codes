package com.interviewpanel.auth;

import com.interviewpanel.InterviewPanelApplication;
import com.interviewpanel.LobbySetup.LobbySetupView;

import java.util.Scanner;

public class LoginView {
    private LoginModel loginModel;

    public LoginView() {
        this.loginModel = new LoginModel(this);
    }

    public void init() {
        System.out.flush();
        int attempts = 3;
        Scanner sc = new Scanner(System.in);
        System.out.println("LOGIN");
        do {
            System.out.println("Enter your username: ");
            String username = sc.nextLine();
            System.out.println("Enter your password: ");
            String password = sc.nextLine();
            if(loginModel.authenticateUser(username, password)) {
                return;
            }
        } while(--attempts > 0);
        System.out.println("You have exceeded the maximum number of attempts. Please try again later.");
    }

    public void onSuccess() {
        System.out.println("Login successful...");
        System.out.println("Welcome to "+ InterviewPanelApplication.getInstance().getAppName()+ " -v"+InterviewPanelApplication.getInstance().getAppVersion());
        LobbySetupView lobbySetupView = new LobbySetupView();
        lobbySetupView.init();
    }

    public void showAlert(String message) {
        System.out.println("Alert: " + message);
    }
}
