package assignments.assignment10_interfaces.universitymanagement.faculty;

public class NonTeachingFaculty implements Faculty {
    int facultyId;
    String facultyName;
    String occupation;

    @Override
    public void teach() {
        System.out.println("Non-teaching faculty is teaching");
    }

    @Override
    public void conductExam() {
        System.out.println("Non-teaching faculty is conducting exam");
    }

    @Override
    public void evaluate() {
        System.out.println("Non-teaching faculty is evaluating");
    }
    
}
