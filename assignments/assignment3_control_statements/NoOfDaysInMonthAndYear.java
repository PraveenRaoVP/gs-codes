package assignments.assignment3_control_statements;

import java.util.Scanner;

public class NoOfDaysInMonthAndYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the month: ");
        int month = sc.nextInt();
        System.out.println("Enter the year: ");
        int year = sc.nextInt();
        int days = 0;
        switch(month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                days=31;
                break;
            case 4: case 6: case 9: case 11:
                days=30;
                break;
            case 2:
                if((year%4==0 && year%100!=0) || year%400==0)
                    days = 29;
                else
                    days = 28;
                break;
            default:
                System.out.println("Invalid month");
                System.exit(0);
        }
        System.out.println("The number of days in the month " + month + " of the year " + year + " is: " + days);
    }
}
