package assignments.assignment7_classesandobjects_encapulation;
// 1. Create Book DTO class with attributes like title, ISBN etc. Create appropriate instance variables, static variables and methods with appropriate access modifiers.
public class Book {
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
