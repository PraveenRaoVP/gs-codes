package assignments.assignment2;

import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of people: ");
        int n = sc.nextInt();
        System.out.println("Enter the weight in kgs: ");
        double weight = sc.nextDouble();
        System.out.println("Enter the height in cms: ");
        double height = sc.nextDouble();
        System.out.println(weight/(height*height));
    }
}
