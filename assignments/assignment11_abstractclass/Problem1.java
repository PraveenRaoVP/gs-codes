package assignments.assignment11_abstractclass;

/* In the book class, add methods to prevent unauthorized modification of book information. Clue: Use login to check authorized personal while updating book details. (use abstract class) */

abstract class Book {
    private String bookName;
    private String author;
    private String genre;
    private double price;

    public Book(String bookName, String author, String genre, double price) {
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    public abstract void updateBookDetails(String bookName, String author, String genre, double price, String username, String password);

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


public class Problem1 extends Book {

    public Problem1() {
        super("", "", "", 0.0);
    }

    @Override
    public void updateBookDetails(String bookName, String author, String genre, double price, String username,
            String password) {
        if (username.equals("admin") && password.equals("admin")) {
            super.setBookName(bookName);
            super.setAuthor(author);
            super.setGenre(genre);
            super.setPrice(price);
        } else {
            System.out.println("Unauthorized access");
        }
    }
    
    public static void main(String[] args) {
        Problem1 p = new Problem1();
        p.updateBookDetails("Book1", "Author1", "Genre1", 100.0, "admin", "admin");
        System.out.println(p.getBookName()); // Book1
        Problem1 p1 = new Problem1();
        p1.updateBookDetails("Book2", "Author2", "Genre2", 200.0, "user", "user"); // Unauthorized access
        System.out.println(p1.getBookName());
    }
}
