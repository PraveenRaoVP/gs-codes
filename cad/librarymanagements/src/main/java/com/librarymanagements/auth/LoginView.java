package com.librarymanagements.auth;

public class LoginView {
    public void init() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the username: ");
        String username = sc.nextLine();
        System.out.print("Enter the password: ");
        String password = sc.nextLine();
        if (username.equals("zsgs") && password.equals("123")) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }
    }
}
