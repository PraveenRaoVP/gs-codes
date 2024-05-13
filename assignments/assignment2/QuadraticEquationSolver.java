package assignments.assignment2;

// 2. Write a program to solve a quadratic equation (ax^2+bx+c). Take a,b and c from user and use quadratic formula.


public class QuadraticEquationSolver {
    public void solve(double a, double b, double c) {
        double determinant = b*b - 4*a*c;
        if(determinant > 0) {
            double root1 = (-b + Math.sqrt(determinant))/(2*a);
            double root2 = (-b - Math.sqrt(determinant))/(2*a);

            System.out.println("The roots are : "+ root1 + " and " + root2);
        } else if(determinant == 0) {
            double root = -b/(2*a);
            System.out.println("The root is: " + root); 
        } else {
            System.out.println("There are no real roots for this equation.");
        }
    }

    public static void main(String[] args) {
        QuadraticEquationSolver obj = new QuadraticEquationSolver();
        obj.solve(1, 5, 6);    
    }
}
