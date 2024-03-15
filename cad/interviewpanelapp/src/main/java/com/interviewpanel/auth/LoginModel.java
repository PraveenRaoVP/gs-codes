package com.interviewpanel.auth;

import java.util.HashMap;
import java.util.Map;

class LoginModel {
    private LoginView loginView;
    private Map<String, String> users = new HashMap<>();
    public LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public boolean authenticateUser(String username, String password) {
        users.put("admin", "admin");
        if(isValidUserName(username)) {
            if(isValidUser(username, password)) {
                loginView.onSuccess();
                return true;
            } else {
                loginView.showAlert("Invalid password");
                return false;
            }
        } else {
            loginView.showAlert("Invalid username");
            return false;
        }
    }

    private boolean isValidUserName(String username) {
        return users.containsKey(username);
    }
    private boolean isValidUser(String username, String password) {
        return users.get(username).equals(password);
    }
}
