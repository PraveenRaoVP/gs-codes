package com.interviewpanel.auth;

import com.interviewpanel.models.helpers.PrintersAndFormatters;
import com.interviewpanel.repository.AdminToCredentialsRepository;
import com.interviewpanel.repository.CacheMemory;
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
                int credentialsId = CredentialsRepository.getInstance().getCredentialsId(username);
                int adminId = AdminToCredentialsRepository.getInstance().getAdminId(credentialsId);
                CacheMemory.getInstance().setCurrentAdmin(adminId);
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
