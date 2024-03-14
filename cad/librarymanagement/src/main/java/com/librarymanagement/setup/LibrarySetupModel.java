package com.librarymanagement.setup;

import com.librarymanagement.models.Library;

import java.util.ArrayList;
import java.util.List;

class LibrarySetupModel {
    private LibrarySetupView librarySetupView;

    public LibrarySetupModel(LibrarySetupView librarySetupView) {
        this.librarySetupView = librarySetupView;
    }

    public Library createLibrary(String libraryName, String phoneNo, String emailId, String address) {
        List<Integer> adminUsers = new ArrayList<>();
        // adminUsers.add();
        Library newLibrary = new Library(1, libraryName, phoneNo, emailId, address, null);
        librarySetupView.showAlert("Library created successfully.");
        return newLibrary;
    }

    public void addAdminUser(int adminId) {
        // adminUsers.add(adminId);
    }


}
