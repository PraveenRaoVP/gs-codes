package evals.eval1;

import java.util.Scanner;

public class SeatsInAPlane {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // Number of test cases
        
        while (t-- > 0) {
            int seatNumber = scanner.nextInt();
            calculateSeatPosition(seatNumber);
        }
        
        scanner.close();
    }

    public static void calculateSeatPosition(int seatNumber) {
        int seatMod = seatNumber % 12;
        switch (seatMod) {
            case 1:
            case 6:
            case 7:
            case 0:
                System.out.println(seatNumber + " WS");
                break;
            case 2:
            case 5:
            case 8:
            case 11:
                System.out.println(seatNumber + " MS");
                break;
            case 3:
            case 4:
            case 9:
            case 10:
                System.out.println(seatNumber + " AS");
                break;
        }
    }
}
