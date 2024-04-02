package assignments.assignment12_exception_handling;

/* 5. Write program to demonstrate ArrayIndexOutOfBoundsException */

public class Problem5 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        try {
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
