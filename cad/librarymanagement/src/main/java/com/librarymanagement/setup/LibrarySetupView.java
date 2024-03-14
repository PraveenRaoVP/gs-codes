package com.librarymanagement.setup;

import com.librarymanagement.models.Library;

import java.util.Scanner;

public class LibrarySetupView {
    private LibrarySetupModel librarySetupModel;
    public LibrarySetupView() {
        librarySetupModel = new LibrarySetupModel(this);
    }
//    String username;
    public void init(String username) {
//        this.username = username;
        System.out.println("Welcome " + username + " to the Library Management System.");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the details of the library:-");
        System.out.print("Library Name: ");
        String libraryName = sc.nextLine();
        System.out.print("Phone Number: ");
        String phoneNo = sc.nextLine();
        System.out.print("Email Id: ");
        String emailId = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();

        librarySetupModel.createLibrary(libraryName, phoneNo, emailId, address);

    }

    public void showAlert(String message) {
        System.out.println(message);
    }
}
