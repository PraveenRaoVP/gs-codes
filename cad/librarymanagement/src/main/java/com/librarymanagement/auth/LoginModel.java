package com.librarymanagement.auth;

import com.librarymanagement.setup.LibrarySetupView;

import java.util.HashMap;
import java.util.Map;

class LoginModel {
    private LoginView loginView;
    private Map<String, String> users = new HashMap<>();

    public LoginModel(LoginView loginView) {
        this.loginView = loginView;
        users.put("zsgs", "123");
    }

    public boolean authenticateUser(String username, String password) {
        if(!users.containsKey(username)) {
            loginView.showAlert("Invalid Username.");
            return false;
        }
        return (users.get(username).equals(password));
    }


}
