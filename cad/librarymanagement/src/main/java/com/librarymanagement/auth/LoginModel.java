package com.librarymanagement.auth;

import com.librarymanagement.repository.CredentialsDatabase;
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

    public boolean authenticateUser(String username, String password) throws InterruptedException {
//        if(isValidUsername(username)) {
//            if(isValidPassword(username, password)) {
//                loginView.onSuccess();
//                return true;
//            } else {
//                loginView.showAlert("Invalid password. Please try again.");
//                return false;
//            }
//        } else {
//            loginView.showAlert("Invalid username. Please try again.");
//            return false;
//        }
        return CredentialsDatabase.getInstance().validateCredentials(username, password);
    }
    private boolean isValidUsername(String username) {
        return users.containsKey(username);
    }
    private boolean isValidPassword(String username, String password) {
        return users.get(username).equals(password);
    }

}
