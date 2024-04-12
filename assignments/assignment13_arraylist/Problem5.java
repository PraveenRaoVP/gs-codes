package assignments.assignment13_arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* Write a program that sorts an ArrayList of custom objects based on a specific property using a custom comparator class implementing the Comparator interface */

class Book {
    private String title;
    private String author;
    private int price;

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", price=" + price + ", title=" + title + "]";
    }
}

class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getPrice() - o2.getPrice();
    }
    
}

public class Problem5 {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book1", "Author1", 100));
        books.add(new Book("Book2", "Author2", 200));
        books.add(new Book("Book3", "Author3", 50));
        books.add(new Book("Book4", "Author4", 150));
        books.add(new Book("Book5", "Author5", 250));

        System.out.println("Before sorting: ");
        for (Book book : books) {
            System.out.println(book);
        }

        Collections.sort(books, new BookComparator());

        System.out.println("\nAfter sorting: ");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
