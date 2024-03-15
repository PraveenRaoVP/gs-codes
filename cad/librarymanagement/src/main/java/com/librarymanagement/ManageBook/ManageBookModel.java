package com.librarymanagement.ManageBook;

import com.librarymanagement.models.Book;
import com.librarymanagement.repository.LibraryDatabase;

import java.util.ArrayList;
import java.util.List;

class ManageBookModel {
    private final ManageBookView manageBookView;
    public List<Book> books;
    public ManageBookModel(ManageBookView manageBookView) {
        this.manageBookView = manageBookView;
    }

    public void startSetup() {
        if(books == null || books.isEmpty()) {
            books = new ArrayList<>();
        }
        manageBookView.initiateManageBooks();
    }

    public void addBook(String bookName, String author, String publication, String edition, String journal, int availableCount, int volume) {
        Book book = new Book();
        book.setId(books.size() + 1);
        book.setName(bookName);
        book.setAuthor(author);
        book.setPublication(publication);
        book.setEdition(edition);
        book.setJournal(journal);
        book.setAvailableCount(availableCount);
        book.setVolume(volume);
        LibraryDatabase.getInstance().addBook(book);
    }
}
