package com.librarymanagement.setup;

import com.librarymanagement.ManageBook.ManageBookView;
import com.librarymanagement.models.Library;

import java.util.Scanner;

public class LibrarySetupView {
    private final LibrarySetupModel librarySetupModel;
    public LibrarySetupView() {
        librarySetupModel = new LibrarySetupModel(this);
    }
    public void init() throws InterruptedException {
        librarySetupModel.startSetup();
    }

    public void initiateSetup() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the library details here: ");
        System.out.println("Enter the library ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the library name: ");
        String libraryName = sc.nextLine();
        System.out.println("Enter the library phone number: ");
        String phoneNo = sc.nextLine();
        System.out.println("Enter the library email ID: ");
        String emailId = sc.nextLine();
        System.out.println("Enter the library address: ");
        String address = sc.nextLine();
        Library library = new Library(id, libraryName, phoneNo, emailId, address);
        librarySetupModel.createLibrary(library);
    }

    public void onSetupComplete(String libraryName) throws InterruptedException {
        System.out.println("Library "+ libraryName +" setup has been completed");
        Thread.sleep(2000);
        new ManageBookView().init();
    }


    public void showAlert(String message) {
        System.out.println(message);
    }
}
