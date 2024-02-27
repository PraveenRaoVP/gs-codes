// 3) Write a program for the following. Let consider 20 students in a class with the roll no
// 101-120. We are going to split them into four groups. Based on their roll number we are
// going to split them with the following logics.
// Example 1:
// Input : Total Number of students : 20
// Output:
// Group 1:
// 101
// 105
// 109
// 113
// 117
// Group 2:
// 102
// 106
// 110
// 114
// 118
// Group 3:
// 103
// 107
// 111
// 115
// 119
// Group 4:
// 104
// 108
// 112
// 116
// 120

package task2;

public class SplitStudentsToGroups {
    public static void main(String[] args) {
        int totalStudents = 20;
        int studentsPerGroup = totalStudents / 4;
        for(int i=1;i<=4;i++) {
            System.out.println("Group "+i+":");
            for(int j=0;j<studentsPerGroup;j++) {
                System.out.println(100+i+j*4);
            }
        }
    }
}
