package assignments.assignment10_interfaces.universitymanagement.courses;

import java.util.List;

public class Course {
    private String courseName;
    private int courseId;
    private int facultyId;
    private List<Integer> studentIds;
    
    public Course(String courseName, int courseId, int facultyId, List<Integer> studentIds) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.facultyId = facultyId;
        this.studentIds = studentIds;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public List<Integer> getStudentIds() {
        return studentIds;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", courseName=" + courseName + ", facultyId=" + facultyId
                + ", studentIds=" + studentIds + "]";
    }
}
