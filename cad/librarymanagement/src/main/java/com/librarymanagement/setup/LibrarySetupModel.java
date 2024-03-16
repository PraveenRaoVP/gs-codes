package com.librarymanagement.setup;

import com.librarymanagement.models.Library;
import com.librarymanagement.repository.LibraryDatabase;

import java.util.List;
import java.util.Scanner;

class LibrarySetupModel {
    private final LibrarySetupView librarySetupView;

    public LibrarySetupModel(LibrarySetupView librarySetupView) {
        this.librarySetupView = librarySetupView;
    }


    public void addLibrary(String libraryName, String phoneNo, String emailId, String address) {
        int libraryId = getLibraries().size()+1;
        Library library = new Library(libraryId, libraryName, phoneNo, emailId, address);
        LibraryDatabase.getInstance().insertLibrary(library);
    }

    public List<Library> getLibraries() {
        return LibraryDatabase.getInstance().getLibraries();
    }

    public void removeLibrary(int libraryId) {
        LibraryDatabase.getInstance().removeLibrary(libraryId);
    }

    public void updateLibrary(int libraryId, String libraryName, String phoneNo, String emailId, String address) {
        LibraryDatabase.getInstance().updateLibrary(libraryId, libraryName, phoneNo, emailId, address);
    }
}
