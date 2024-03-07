package assignments.assignment3_control_statements;

import java.util.Scanner;

public class ScoreToGrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the score: ");
        int score = sc.nextInt();
        if(score > 100 || score < 0) {
            System.out.println("Invalid score");
            return;
        }
        switch(score/10) {
            case 10:
            case 9:
                System.out.println("Grade: A");
                break;
            case 8:
                System.out.println("Grade: B");
                break;
            case 7:
                System.out.println("Grade: C");
                break;
            case 6:
                System.out.println("Grade: D");
                break;
            default:
                System.out.println("Grade: F");
        }
    }
}
