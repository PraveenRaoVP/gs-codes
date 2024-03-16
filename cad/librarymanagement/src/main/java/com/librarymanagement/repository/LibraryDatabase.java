package com.librarymanagement.repository;

import com.librarymanagement.models.Book;
import com.librarymanagement.models.Library;

import java.util.ArrayList;
import java.util.List;

public class LibraryDatabase {
    private static LibraryDatabase libraryDatabase;
    private final List<Library> libraries = new ArrayList<>();
    private LibraryDatabase() {
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public static LibraryDatabase getInstance() {
        if(libraryDatabase == null) {
            libraryDatabase = new LibraryDatabase();
        }
        return libraryDatabase;
    }

    public void insertLibrary(Library library) {
        libraries.add(library);
        System.out.println("Lib details added");
    }

    public boolean checkDuplicateLibrary(String libraryName) {
        for(Library library: libraries) {
            if(library.getLibraryName().equals(libraryName)) {
                return true;
            }
        }
        return false;
    }

    public void removeLibrary(int libraryId) {
        for(Library library: libraries) {
            if(library.getLibraryId() == libraryId) {
                libraries.remove(library);
                System.out.println("Lib details removed");
                return;
            }
        }
        System.out.println("Library not found");
    }

    public boolean checkIfLibrariesExist() {
        return !libraries.isEmpty();
    }

    public void updateLibrary(int libraryId, String libraryName, String phoneNo, String emailId, String address) {
        for(Library library: libraries) {
            if(library.getLibraryId() == libraryId) {
                library.setLibraryName(libraryName);
                library.setPhoneNo(phoneNo);
                library.setEmailId(emailId);
                library.setAddress(address);
                System.out.println("Lib details updated");
                return;
            }
        }
        System.out.println("Library not found");
    }
}
