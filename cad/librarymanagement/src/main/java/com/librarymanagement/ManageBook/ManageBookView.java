package com.librarymanagement.ManageBook;

import java.util.Scanner;

public class ManageBookView {
    private final ManageBookModel manageBookModel;

    public ManageBookView() {
        this.manageBookModel = new ManageBookModel(this);
    }

    public void init() {
        System.out.flush();
        manageBookModel.startSetup();
    }

    public void initiateManageBooks() {
        System.out.println("Enter the book details here: ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of books: ");
        int noOfBooks = sc.nextInt();
        for(int i=1;i<=noOfBooks;i++) {
            getBookDetails(i);
        }
        System.out.println("The books available are: ");
        manageBookModel.showBooks();
    }

    public void getBookDetails(int i) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the data of book "+i+":-");

        System.out.print("Enter the book name: ");
        String bookName = sc.nextLine();
        System.out.print("Enter the book author: ");
        String author = sc.nextLine();
        System.out.print("Enter the publication: ");
        String publication = sc.nextLine();
        System.out.print("Enter the edition: ");
        String edition = sc.nextLine();
        System.out.print("Enter the journal: ");
        String journal = sc.nextLine();
        System.out.print("Enter the available count: ");
        int availableCount = sc.nextInt();
        System.out.print("Enter the volume: ");
        int volume = sc.nextInt();

//        manageBookModel.addBook(id, bookName, author, publication, edition, journal, availableCount, volume);
        manageBookModel.addBook(bookName, author, publication, edition, journal, availableCount, volume);
        System.out.println();
        System.out.println();
    }

    public void onManageBooksComplete() {
        System.out.println("Books have been added successfully!");
    }

    public void showAlert(String message) {
        System.out.println(message);
    }

}
