package assignments.assignment12_exception_handling;


// 1. Write a program for division with proper divide-by-zero exception handling


public class Problem1 {
    public static void main(String[] args) {
        try{
            System.out.println(0/0);
        } catch(ArithmeticException e) {
            e.printStackTrace();
        }
    }
}
