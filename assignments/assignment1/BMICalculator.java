package assignments.assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Person implements Comparable {
    public String name;
    public double height;
    public double weight;
    public double BMI;
    Person(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.BMI = 0;
    }
    
    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        if(this.BMI < p.BMI) {
            return -1;
        } else if(this.BMI > p.BMI) {
            return 1;
        } else {
            return 0;
        }
    }
}

public class BMICalculator {

    public double calculateBMI(double weight, double height) {
        return weight/(height*height);
    }

    public double convertHeightCentimetersToMeter(double heightInCms) {
        return heightInCms/100;
    }

    public String classifyBMI(double bmi) {
        if(bmi<18.5) {
            return "Underweight";
        } else if(bmi>=18.5 && bmi<24.9) {
            return "Normal weight";
        } else if(bmi>=24.9 && bmi<29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    public static void main(String[] args) {
        BMICalculator obj = new BMICalculator();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of people: ");
        int n = sc.nextInt();
        List<Person> people = new ArrayList<>();
        while(n-- >0) {
            System.out.println("Enter the name of the person: ");
            String name = sc.next();
            System.out.println("Enter the weight in kgs: ");
            double weight = sc.nextDouble();
            System.out.println("Enter the height in cms: ");
            double height = sc.nextDouble();
            Person p = new Person(name, height, weight);
            //System.out.printf("The BMI of the person is: %.2f\n", obj.calculateBMI(weight, obj.convertHeightCentimetersToMeter(height)));
            p.BMI = obj.calculateBMI(weight, obj.convertHeightCentimetersToMeter(height));
            people.add(p);
            // obj.classifyBMI(obj.calculateBMI(weight, obj.convertHeightCentimetersToMeter(height)));
        }
        Collections.sort(people);
        System.out.println("The sorted list of people based on BMI is: ");
        System.out.println("Name\tBMI\tClassification");
        
        for(Person p: people) {
            System.out.printf(p.name + "\t" + "%.2f" + "\t" + obj.classifyBMI(p.BMI)+"\n", p.BMI);
        }       
    }
}