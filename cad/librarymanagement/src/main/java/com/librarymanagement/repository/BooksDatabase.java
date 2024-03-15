package com.librarymanagement.repository;

import com.librarymanagement.models.Book;

import java.util.List;

public class BooksDatabase {
    private BooksDatabase() {}

    public static BooksDatabase booksDatabase;

    public static BooksDatabase getInstance() {
        if(booksDatabase == null) {
            booksDatabase = new BooksDatabase();
        }
        return booksDatabase;
    }

    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
