/*
Classes of Library Management System :

Library Management System class – It manages all operations of Library Management System. It is central part of organization for which software is being designed.
User Class – It manages all operations of user.
Librarian Class – It manages all operations of Librarian.
Book Class – It manages all operations of books. It is basic building block of system.
Account Class – It manages all operations of account.
Library database Class – It manages all operations of library database.
Staff Class – It manages all operations of staff.
Student Class – It manages all operations of student.
Attributes of Library Management System :

Library Management System Attributes – UserType, Username, Password
User Attributes – Name, Id
Librarian Attributes – Name, Id, Password, SearchString
Book Attributes – Title, Author, ISBN, Publication
Account Attributes – no_borrowed_books, no_reserved_books, no_returned_books, no_lost_books fine_amount
Library database Attributes – List_of_books
Staff Class Attributes – Dept
Student Class Attributes – Class
Methods of Library Management System :

Library Management System Methods – Login(), Register(), Logout()
User Methods – Verify(), CheckAccount(), get_book_info()
Librarian Methods – Verify_librarian(), Search()
Book Methods – Show_duedt(), Reservation_status(), Feedback(), Book_request(), Renew_info()
Account Methods – Calculate_fine()
Library database Methods – Add(), Delete(), Update(), Display(), Search()
 Create classes for the given class diagram 
*/

package assignments.assignment8_constructors_and_inheritance;

import java.util.ArrayList;
import java.util.List;

class LibraryManagementSystem {
    private String userType;
    private String username;
    private String password;

    public LibraryManagementSystem(String userType, String username, String password) {
        this.userType = userType;
        this.username = username;
        this.password = password;
    }

    public void login() {
        System.out.println("Logging in...");
    }

    public void register() {
        System.out.println("Registering...");
    }

    public void logout() {
        System.out.println("Logging out...");
    }
}

class User {
    private String name;
    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void verify() {
        System.out.println("Verifying...");
    }

    public void checkAccount() {
        System.out.println("Checking account...");
    }

    public void getBookInfo() {
        System.out.println("Getting book info...");
    }
}

class Librarian {
    private String name;
    private int id;
    private String password;
    private String searchString;

    public Librarian(String name, int id, String password, String searchString) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.searchString = searchString;
    }

    public void verifyLibrarian() {
        System.out.println("Verifying librarian...");
    }

    public void search() {
        System.out.println("Searching...");
    }
}

class Book {
    String title;
    String author;
    String ISBN;
    String publication;

    public Book(String title, String author, String ISBN, String publication) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publication = publication;
    }

    public void showDueDate() {
        System.out.println("Showing due date...");
    }

    public void reservationStatus() {
        System.out.println("Reservation status...");
    }

    public void feedback() {
        System.out.println("Feedback...");
    }

    public void bookRequest() {
        System.out.println("Book request...");
    }

    public void renewInfo() {
        System.out.println("Renew info...");
    }
}

class Account {
    private int noBorrowedBooks;
    private int noReservedBooks;
    private int noReturnedBooks;
    private int noLostBooks;
    private double fineAmount;

    public Account(int noBorrowedBooks, int noReservedBooks, int noReturnedBooks, int noLostBooks, double fineAmount) {
        this.noBorrowedBooks = noBorrowedBooks;
        this.noReservedBooks = noReservedBooks;
        this.noReturnedBooks = noReturnedBooks;
        this.noLostBooks = noLostBooks;
        this.fineAmount = fineAmount;
    }

    public void calculateFine() {
        System.out.println("Calculating fine...");
    }
}

class LibraryDatabase {
    private List<Book> listOfBooks;

    public LibraryDatabase() {
        this.listOfBooks = new ArrayList<>();
    }

    public void add(Book book) {
        listOfBooks.add(book);
    }

    public void delete(Book book) {
        listOfBooks.remove(book);
    }

    public void update(String title, Book book) {
        for (Book b : listOfBooks) {
            if (b.title.equals(title)) {
                listOfBooks.remove(b);
                listOfBooks.add(book);
            }
        }
        System.out.println("Book updated");
    }

    public void display() {
        for (Book book : listOfBooks) {
            System.out.println(book);
        }
    }

    public void search(String keyword) {
        List<Book> searchResults = new ArrayList<>();
        for (Book book : listOfBooks) {
            if (book.toString().contains(keyword)) {
                searchResults.add(book);
            }
        }
        for (Book book : searchResults) {
            System.out.println(book);
        }
    }
}

class Staff extends User{
    private String dept;

    public Staff(String name, int id, String dept) {
        super(name, id);
        this.dept = dept;
    }
}

class Student extends User{
    private String className;

    public Student(String name, int id, String className) {
        super(name, id);
        this.className = className;
    }
}

public class LibraryManagementSystemDemo {
    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem("User", "username", "password");
        libraryManagementSystem.login();
        libraryManagementSystem.register();
        libraryManagementSystem.logout();

        User user = new User("User", 1);
        user.verify();
        user.checkAccount();
        user.getBookInfo();

        Librarian librarian = new Librarian("Librarian", 1, "password", "searchString");
        librarian.verifyLibrarian();
        librarian.search();

        Book book = new Book("Title", "Author", "ISBN", "Publication");
        book.showDueDate();
        book.reservationStatus();
        book.feedback();
        book.bookRequest();
        book.renewInfo();

        Account account = new Account(1, 1, 1, 1, 1.0);
        account.calculateFine();

        LibraryDatabase libraryDatabase = new LibraryDatabase();
        libraryDatabase.add(book);
        libraryDatabase.delete(book);
        libraryDatabase.update("Title", book);
        libraryDatabase.display();
        libraryDatabase.search("Title");

        Staff staff = new Staff("Staff", 1, "Dept");

        Student student = new Student("Student", 1, "Class");
    }
}


