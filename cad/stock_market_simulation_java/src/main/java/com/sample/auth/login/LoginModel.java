package com.sample.auth.login;

import com.sample.models.Credentials;
import com.sample.repository.CredentialsRepository;

public class LoginModel {
    private LoginView loginView;

    public LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void initLogin() {
        int attempts = 3;
        while(attempts-- > 0) {
            loginView.showLogin();
        }
    }

    public boolean validateLogin(String username, String password) {
        Credentials cred = CredentialsRepository.getInstance().getCredentialByUsername(username);
        if(cred == null) {
            loginView.showWrongUsername();
            return false;
        }
        if(!cred.getPassword().equals(password)) {
            loginView.showIncorrectPassword();
            return false;
        }
        return true;
    }
}
