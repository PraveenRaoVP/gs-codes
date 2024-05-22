package assignments.assignment10_interfaces.universitymanagement.faculty;

public class TeachingFaculty implements Faculty {
    int facultyId;
    String facultyName;
    String qualifications;
    int yearsOfExperience;

    @Override
    public void teach() {
        System.out.println("Teaching faculty is teaching");
    }

    @Override
    public void conductExam() {
        System.out.println("Teaching faculty is conducting exam");
    }

    @Override
    public void evaluate() {
        System.out.println("Teaching faculty is evaluating");
    }
}
