package assignments.assignment10_interfaces.librarymanagement.members;

public class Staff implements Member {

    int staffId;
    String staffName;
    String staffAddress;
    String staffPhone;

    @Override
    public void issueBook() {
        System.out.println("Staff issues book");
    }

    @Override
    public void returnBook() {
        System.out.println("Staff returnns book");
    }

    @Override
    public void reserveBook() {
        System.out.println("Staff reserves book");
    }

    @Override
    public void payFine() {
        System.out.println("Staff can pay fine");
    }
    
}
