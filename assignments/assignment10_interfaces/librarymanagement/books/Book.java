package assignments.assignment10_interfaces.librarymanagement.books;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private int year;

    public Book(int bookId, String title, String author, int year) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", bookId=" + bookId + ", title=" + title + ", year=" + year + "]";
    }
}
