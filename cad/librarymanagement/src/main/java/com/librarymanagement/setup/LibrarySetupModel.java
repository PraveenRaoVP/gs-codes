package com.librarymanagement.setup;

import com.librarymanagement.models.Library;
import com.librarymanagement.repository.LibraryDatabase;

class LibrarySetupModel {
    private final LibrarySetupView librarySetupView;
    public Library library;

    public LibrarySetupModel(LibrarySetupView librarySetupView) {
        this.library = LibraryDatabase.getInstance().getLibrary();
        this.librarySetupView = librarySetupView;
    }

    public void startSetup() throws InterruptedException {
        if (library == null || library.getLibraryId() == 0) {
            librarySetupView.initiateSetup();
        } else {
            librarySetupView.onSetupComplete(library.getLibraryName());
        }
    }

    public void createLibrary(Library newLibrary) throws InterruptedException {
        LibraryDatabase.getInstance().setLibrary(newLibrary);
        librarySetupView.onSetupComplete(newLibrary.getLibraryName());
    }
}
