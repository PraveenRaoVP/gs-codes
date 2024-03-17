package com.librarymanagement.MainMenu;

import com.librarymanagement.CustomerManagement.CustomerManagerView;
import com.librarymanagement.ManageBooks.ManageBooksView;
import com.librarymanagement.setup.LibrarySetupView;

import java.util.Scanner;

public class MainMenuView {
    private final MainMenuModel mainMenuModel;

    public MainMenuView() {
        this.mainMenuModel = new MainMenuModel(this);
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        int ch = Integer.MIN_VALUE;
        do{
            displayMainMenu();
            ch = sc.nextInt();
            switch(ch) {
                case 1:
                    handleLibraryOptions();
                    break;
                case 2:
                    handleManageBookOptions();
                    break;
                case 3:
                    handleCustomerManageOptions();
                    break;
                case 4:
                    System.out.println("Admin Options");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while(ch!=5);
    }

    public void displayMainMenu() {
        System.out.flush();
        System.out.println("Select one of the options below:-");
        System.out.println("1. Library Options");
        System.out.println("2. Manage Books");
        System.out.println("3. Customer Options");
        System.out.println("4. Admin Options");
        System.out.println("5. Exit");
        System.out.println("Select an option: ");
    }

    public void displayLibraryOptions() {
        System.out.flush();
        System.out.println("Select one of the options below:-");
        System.out.println("1. Add Library");
        System.out.println("2. Remove Library");
        System.out.println("3. View Libraries");
        System.out.println("4. Update Library");
        System.out.println("5. View all books of the library (Admin only)");
        System.out.println("6. Logout");
        System.out.println("Select an option: ");
    }

    public void displayBooksOptions() {
        System.out.flush();
        System.out.println("Select one of the options below:-");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. View Books");
        System.out.println("4. Back");
        System.out.println("Select an option: ");
    }

    public void displayCustomerOptions() {
        System.out.flush();
        System.out.println("Select one of the options below:-");
        System.out.println("1. Issue Book to Customer");
        System.out.println("2. Return Book");
        System.out.println("3. Check for fine");
        System.out.println("4. Remove Customer");
        System.out.println("5. View Customers (Admin only)");
        System.out.println("6. Back");
        System.out.println("Select an option: ");
    }

    public void displayAdminOptions() {
        System.out.flush();
        System.out.println("Select one of the options below:-");
        System.out.println("1. Add Admin");
        System.out.println("2. Remove Admin");
        System.out.println("3. View Admins");
        System.out.println("4. Back");
        System.out.println("Select an option: ");
    }

    public void handleLibraryOptions() {
        displayLibraryOptions();
        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        LibrarySetupView librarySetupView = new LibrarySetupView();
        switch(ch) {
            case 1:
                librarySetupView.addLibrary();
                break;
            case 2:
                librarySetupView.removeLibrary();
                break;
            case 3:
                librarySetupView.viewLibraries();
                break;
            case 4:
                librarySetupView.updateLibrary();
                break;
            case 5:
                librarySetupView.viewLibraryBooks();
                break;
            case 6:
                System.out.println("Going back...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void handleManageBookOptions() {
        displayBooksOptions();
        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        ManageBooksView manageBooksView = new ManageBooksView();
        switch(ch) {
            case 1:
                manageBooksView.addBook();
                break;
            case 2:
                manageBooksView.removeBook();
                break;
            case 3:
                manageBooksView.viewBooks();
                break;
            case 4:
                System.out.println("Going back...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void handleCustomerManageOptions() {
        displayCustomerOptions();
        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        CustomerManagerView customerManagerView = new CustomerManagerView();
        switch(ch) {
            case 1:
                customerManagerView.issueBook();
                break;
            case 2:
                customerManagerView.returnBook();
                break;
            case 3:
//                customerManagerView.checkForFine();
                System.out.println("Check for fine not implemented yet.");
                break;
            case 4:
                customerManagerView.removeCustomer();
                break;
            case 5:
                customerManagerView.viewCustomer();
                break;
            case 6:
                System.out.println("Going back...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void showAlert(String message) {
        System.out.println(message);
    }
}
