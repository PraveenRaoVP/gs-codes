package com.librarymanagement.setup;

import com.librarymanagement.models.Library;

import java.util.ArrayList;
import java.util.List;

class LibrarySetupModel {
    private final LibrarySetupView librarySetupView;
    public Library library = new Library();

    public LibrarySetupModel(LibrarySetupView librarySetupView) {
        this.librarySetupView = librarySetupView;
    }

    public void startSetup() throws InterruptedException {
        if (library == null || library.getLibraryId() == 0) {
            librarySetupView.initiateSetup();
        } else {
            librarySetupView.onSetupComplete();
        }
    }

    public void createLibrary(int id, String libraryName, String phoneNo, String emailId, String address) throws InterruptedException {
        library.setLibraryId(id);
        library.setLibraryName(libraryName);
        library.setPhoneNo(phoneNo);
        library.setEmailId(emailId);
        library.setAddress(address);
        librarySetupView.onSetupComplete();
    }
}
