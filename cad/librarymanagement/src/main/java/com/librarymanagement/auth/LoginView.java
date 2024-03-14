package com.librarymanagement.auth;

import com.librarymanagement.setup.LibrarySetupView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginView {
    private LoginModel loginModel;
    public LoginView() {
        loginModel = new LoginModel(this);
    }

    public void init() {
        int attempts = 3;
        do {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the username: ");
            String username = sc.nextLine();
            System.out.print("Enter the password: ");
            String password = sc.nextLine();
            if(loginModel.authenticateUser(username, password)) {
                System.out.println("You have successfully logged in.");
                LibrarySetupView librarySetupView = new LibrarySetupView();
                librarySetupView.init(username);
                return;
            }
        } while(attempts -- > 0);
        System.out.println("You have exceeded the number of attempts. Please try again later.");
    }

    public void showAlert(String message) {
        System.out.println(message);
    }
}
