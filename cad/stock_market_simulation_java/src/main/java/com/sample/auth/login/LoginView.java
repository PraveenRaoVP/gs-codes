package com.sample.auth.login;

import com.sample.MainMenu.MainMenu;

import java.util.Scanner;

public class LoginView {
    private LoginModel loginModel;

    public LoginView() {
        loginModel = new LoginModel(this);
    }

    public void init() {
        loginModel.initLogin();
    }

    public void showLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        if(loginModel.validateLogin(username, password)) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.displayMainMenu();
        }
    }

    public void showWrongUsername() {
        System.out.println("Username is incorrect");
    }

    public void showIncorrectPassword() {
        System.out.println("Password is incorrect");
    }
}
