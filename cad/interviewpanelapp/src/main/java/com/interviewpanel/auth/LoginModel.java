package com.interviewpanel.auth;

import com.interviewpanel.models.helpers.PrintersAndFormatters;
import com.interviewpanel.repository.CredentialsRepository;

class LoginModel {
    private final LoginView loginView;

    public LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void initLogin() {
        int attempts = 0;
        do {
            loginView.loginDetails();
        } while(++attempts < 3);
        PrintersAndFormatters.showMessage("Too many attempts. Exiting...");
    }

    public boolean authenticateUser(String username, String password) {
        if(CredentialsRepository.getInstance().checkIfUsernamePresent(username)) {
            if(CredentialsRepository.getInstance().getPassword(username).equals(password)) {
                return true;
            } else {
                PrintersAndFormatters.showMessage("Incorrect password");
            }
        } else {
            PrintersAndFormatters.showMessage("Username not found");
        }
        return false;
    }
}
