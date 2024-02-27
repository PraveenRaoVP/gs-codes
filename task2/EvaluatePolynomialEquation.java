package task2;

import java.util.Scanner;

public class EvaluatePolynomialEquation {
    public static double evaluateExpression(int a, int b, int c, boolean isPlus) {
        if(isPlus) {
            return (-b + Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);
        } else {
            return (-b - Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);
        }
    }

    public static boolean checkIsValidEquation(String equation) {
        // return equation.matches("([+-]?\\d+)x\\^2([+-]\\d+)x([+-]\\d+)");
        return equation.matches("([+-]?\\d+)x\\^2([+-]\\d+)x([+-]\\d+)");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the equation in the form ax^2+bx+c");
        String equation = sc.nextLine();
        if(checkIsValidEquation(equation)) {
            String[] parts = equation.split("x\\^2|\\+|x"); // 1x^2+2x+3 => [1,2,3]
            int a = parts[0].isEmpty() ? 1 : Integer.parseInt(parts[0]);
            int b = parts[1].isEmpty() ? 0 : Integer.parseInt(parts[1]);
            int c = Integer.parseInt(parts[2]);
            System.out.println("Roots are: "+evaluateExpression(a,b,c,true)+" and "+evaluateExpression(a,b,c,false));
        } else {
            System.out.println("Not a valid equation. Please enter a valid equation in the form ax^2+bx+c");
        }
    }
}
