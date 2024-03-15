package com.librarymanagement.repository;

import com.librarymanagement.models.Book;
import com.librarymanagement.models.Library;

import java.util.ArrayList;
import java.util.List;

public class LibraryDatabase {
    private static LibraryDatabase libraryDatabase;
    private Library library;
    List<Book> bookList = new ArrayList<>();
    private LibraryDatabase() {
    }

    public static LibraryDatabase getInstance() {
        if(libraryDatabase == null) {
            libraryDatabase = new LibraryDatabase();
        }
        return libraryDatabase;
    }


    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void insertLibrary(Library library) {
        this.library = library;
        System.out.println("Lib details added");
    }

    public List<Book> getBookList() {
        System.out.println(library.getLibraryName());
        return bookList;
    }

    public Book getBook(int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public List<Book> searchBook(String bookName) {
        return LibraryDatabase.getInstance().getBookList().stream()
                .filter(book -> book.getName().contains(bookName))
                .toList();
    }

    public void addBook(Book book) {
        bookList.add(book);
    }
}
