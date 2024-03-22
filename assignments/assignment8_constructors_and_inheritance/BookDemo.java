package assignments.assignment8_constructors_and_inheritance;

import java.util.ArrayList;
import java.util.List;

// 1. Create Book DTO class with attributes like title, ISBN etc. Create appropriate instance variables, static variables and methods with appropriate access modifiers.
class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String ISBN;
    private static int count = 0;

    public Book(String bookName, String author, String ISBN) {
        this.bookId = ++count;
        this.bookName = bookName;
        this.author = author;
        this.ISBN = ISBN;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Book.count = count;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}

public class BookDemo {
    public void addBooks(List<Book> books, Book book) {
        books.add(book);
    }

    public void displayBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        BookDemo bookDemo = new BookDemo();
        List<Book> books = new ArrayList<>();
        bookDemo.addBooks(books, new Book("The Art of War", "Sun Tzu", "978-0-486-43374-6"));
        bookDemo.addBooks(books, new Book("Mahabharata", "Krishna Dwaipayana Vyasa", "978-0-486-43374-6"));
        bookDemo.addBooks(books, new Book("The Prince", "Niccolo Machiavelli", "978-0-486-43374-6"));
        bookDemo.displayBooks(books);
    }
}

