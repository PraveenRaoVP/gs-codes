package assignments.assignment8_constructors_and_inheritance;

// Design a Student Parent class and ZSGSStudent Child class. Create constructors in both class with varying number of parameters. Call parent constructor from child constructor. Try experimenting by creating child object using parent constructor.

class Student {
    private int studentId;
    private String studentName;
    private String studentClass;
    private String studentSection;

    public Student(int studentId, String studentName, String studentClass, String studentSection) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentSection = studentSection;
    }

    public Student(int studentId, String studentName, String studentClass) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentClass = studentClass;
    }
}

public class ZSGSStudent extends Student {
    private String schoolName;

    public ZSGSStudent(int studentId, String studentName, String studentClass, String studentSection, String schoolName) {
        super(studentId, studentName, studentClass, studentSection);
        this.schoolName = schoolName;
    }

    public ZSGSStudent(int studentId, String studentName, String studentClass, String schoolName) {
        super(studentId, studentName, studentClass);
        this.schoolName = schoolName;
    }

    public static void main(String[] args) {
        Student student = new Student(1, "John Doe", "10", "A");
        ZSGSStudent zsgsStudent = new ZSGSStudent(2, "Jane Doe", "10", "A", "ZSGS");
    }
}
