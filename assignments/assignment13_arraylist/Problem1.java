package assignments.assignment13_arraylist;

import java.util.ArrayList;

/* Create ArrayList of the book class (ArrayList<Book>) in main method. Add methods to add books to the ArrayList */

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

public class Problem1 {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book1", "Author1", 100));
        books.add(new Book("Book2", "Author2", 200));
        books.add(new Book("Book3", "Author3", 300));
        books.add(new Book("Book4", "Author4", 400));
        books.add(new Book("Book5", "Author5", 500));

        for (Book book : books) {
            System.out.println(book);
        }
    }
}
