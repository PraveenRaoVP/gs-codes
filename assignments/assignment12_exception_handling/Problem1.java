package assignments.assignment12_exception_handling;

public class Problem1 {
    public static void main(String[] args) {
        try{
            System.out.println(0/0);
        } catch(ArithmeticException e) {
            e.printStackTrace();
        }
    }
}
