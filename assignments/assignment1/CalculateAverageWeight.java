package assignments.assignment1;

import java.util.Scanner;
// 1. Write a program that calculates the average weight of 10 people. Use descriptive and meaningful variable names following Java naming conventions. Use proper datatypes for the variables.

public class CalculateAverageWeight {
    public double calculateAverageWeight(double[] weights, int noOfPeople) {
        int sum = 0;
        for(int i=0;i<noOfPeople;i++)
            sum+=weights[i];
        return sum/noOfPeople;
    }

    public static void main(String[] args) {
        CalculateAverageWeight obj = new CalculateAverageWeight();
        Scanner sc = new Scanner(System.in);
        double[] weights = new double[10];
        for(int i=0;i<10;i++) {
            weights[i] = sc.nextInt();
        }
        System.out.println("The average weight of the people is: " + obj.calculateAverageWeight(weights, 10));
    }
}
