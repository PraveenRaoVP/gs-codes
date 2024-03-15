package com.librarymanagement.UserCreation;

public class UserCreationView {
    private final UserCreationModel userCreationModel;

    public UserCreationView() {
        this.userCreationModel = new UserCreationModel(this);
    }

    public void init() {
        System.out.flush();
        userCreationModel.initiateUserCreation();
    }

}
