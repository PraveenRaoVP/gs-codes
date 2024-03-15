package com.librarymanagement.UserCreation;

class UserCreationModel {
    private final UserCreationView userCreationView;

    public UserCreationModel(UserCreationView userCreationView) {
        this.userCreationView = userCreationView;
    }

    public void initiateUserCreation() {
        System.out.println("User creation initiated");
    }
}
