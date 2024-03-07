package assignments.assignment1_datatypes;

import java.util.Scanner;

// 2. Write a Java program that gets a number from the user and displays the name of the weekday. Use enum.



public class WeekDayPrinter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = sc.nextInt();

        switch(n) {
            case 1:
                System.out.println(WeekDay.SUNDAY);
                break;
            case 2:
                System.out.println(WeekDay.MONDAY);
                break;
            case 3:
                System.out.println(WeekDay.TUESDAY);
                break;
            case 4:
                System.out.println(WeekDay.WEDNESDAY);
                break;
            case 5:
                System.out.println(WeekDay.THURSDAY);
                break;
            case 6:
                System.out.println(WeekDay.FRIDAY);
                break;
            case 7:
                System.out.println(WeekDay.SATURDAY);
                break;
            default:
                System.out.println("Invalid input");
        }
    }
    
    enum WeekDay {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY 
    }
}
