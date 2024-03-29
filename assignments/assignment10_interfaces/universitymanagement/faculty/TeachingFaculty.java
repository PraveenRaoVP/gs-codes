package assignments.assignment10_interfaces.universitymanagement.faculty;

public class TeachingFaculty implements Faculty {

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
