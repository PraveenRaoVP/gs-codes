package assignments.assignment1;

import java.util.Scanner;

public class BMICalculator {
    public double calculateBMI(double weight, double height) {
        return weight/(height*height);
    }

    public double convertHeightCentimetersToMeter(double heightInCms) {
        return heightInCms/100;
    }

    public void classifyBMI(double bmi) {
        if(bmi<18.5) {
            System.out.println("Underweight");
        } else if(bmi>=18.5 && bmi<24.9) {
            System.out.println("Normal weight");
        } else if(bmi>=24.9 && bmi<29.9) {
            System.out.println("Overweight");
        } else {
            System.out.println("Obesity");
        }
    }

    public static void main(String[] args) {
        BMICalculator obj = new BMICalculator();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of people: ");
        int n = sc.nextInt();
        while(n-- >0) {
            System.out.println("Enter the weight in kgs: ");
            double weight = sc.nextDouble();
            System.out.println("Enter the height in cms: ");
            double height = sc.nextDouble();
            System.out.printf("The BMI of the person is: %.2f\n", obj.calculateBMI(weight, obj.convertHeightCentimetersToMeter(height)));
            obj.classifyBMI(obj.calculateBMI(weight, obj.convertHeightCentimetersToMeter(height)));
        }
    }
}