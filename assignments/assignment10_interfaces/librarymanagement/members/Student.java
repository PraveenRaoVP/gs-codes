package assignments.assignment10_interfaces.librarymanagement.members;

public class Student implements Member{
    private int studentId;
    private String studentName;
    private String studentAddress;
    private String studentPhone;

    public Student(int studentId, String studentName, String studentAddress, String studentPhone) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentPhone = studentPhone;
    }
    

    public int getStudentId() {
        return studentId;
    }



    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }



    public String getStudentName() {
        return studentName;
    }



    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }



    public String getStudentAddress() {
        return studentAddress;
    }



    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }



    public String getStudentPhone() {
        return studentPhone;
    }



    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }



    @Override
    public void issueBook() {
        System.out.println("Issuing book to student");
    }

    @Override
    public void returnBook() {
        System.out.println("Returning book from student");
    }

    @Override
    public void reserveBook() {
        System.out.println("Reserving book for student");
    }

    @Override
    public void payFine() {
        System.out.println("Paying fine for student");
    }
    
}
