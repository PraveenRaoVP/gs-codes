package assignments.assignment9_polymorphism;
/* Create a Java class MathOperations with methods for basic mathematical operations such as add() , subtract() , multiply() , and divide() . Implement method overloading to allow these operations for integers, doubles, and optionally, other types. */
public class MathOperations {

    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public double divide(double a, double b) {
        return a / b;
    }

    public static void main(String[] args) {
        MathOperations mathOperations = new MathOperations();
        System.out.println("Addition of 5 and 10: " + mathOperations.add(5, 10));
        System.out.println("Addition of 5.5 and 10.5: " + mathOperations.add(5.5, 10.5));
        System.out.println("Subtraction of 10 from 5: " + mathOperations.subtract(5, 10));
        System.out.println("Subtraction of 10.5 from 5.5: " + mathOperations.subtract(5.5, 10.5));
        System.out.println("Multiplication of 5 and 10: " + mathOperations.multiply(5, 10));
        System.out.println("Multiplication of 5.5 and 10.5: " + mathOperations.multiply(5.5, 10.5));
        System.out.println("Division of 10 by 5: " + mathOperations.divide(10, 5));
        System.out.println("Division of 10.5 by 5.5: " + mathOperations.divide(10.5, 5.5));
    }
}
