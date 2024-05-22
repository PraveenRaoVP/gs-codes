package com.sample.auth.signup;

import com.sample.utils.ValidationUtil;

import java.util.Scanner;

public class SignUpView {
    private SignUpModel signUpModel;

    public SignUpView() {
        this.signUpModel = new SignUpModel(this);
    }

    public void signUpPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        String email;
        do{
            System.out.println("Enter your email: ");
            email = scanner.nextLine();
        } while(!ValidationUtil.validateEmail(email));

        String username;
        do {
            System.out.println("Enter your username: ");
            username = scanner.nextLine();
        } while(!ValidationUtil.validateUserName(username));
        String password;
        do {
            System.out.println("Enter your password: ");
            password = scanner.nextLine();
        } while(!ValidationUtil.validatePassword(password));

    }
}
