package assignments.assignment1;

/*
 * Write a program with an if statement. Declare one variable inside the if block and another one outside. Print both variables inside and outside the if block to understand variable scope. (Comment the compiler error in your program while submitting)
 */

public class CheckIfStatementWorking {
    public static void main(String[] args) {
        int i=1;

        if(i==1) {
            int j = 5;
            System.out.println("i: " + i+ ",j: "+j);
        }
        System.out.println("i: " + i+ ",j: "+j); //j cannot be resolved to a variable
    }
}
