package assignments.assignment2;

import java.util.Scanner;

public class TimeConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the time in seconds: ");
        int seconds = sc.nextInt();
        int hours = seconds/3600;
        int minutes = seconds/60;
        int secondsToBePrinted = seconds%60;
        String hh = hours/10==0 ? "0" + hours : hours + "";
        String mm = minutes/10==0 ? "0" + minutes : minutes + "";
        String ss = secondsToBePrinted/10==0 ? "0" + secondsToBePrinted : secondsToBePrinted + "";

        System.out.println("HH:MM:SS - " + hh + ":" + mm + ":" + ss);
    }
}
