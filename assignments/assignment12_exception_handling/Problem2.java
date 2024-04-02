package assignments.assignment12_exception_handling;

/*Create a custom Exception by yourself and try throwing and catching the same. */

class CustomException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("This is a custom exception");
    }
}

public class Problem2 {
    public static void main(String[] args) {
        try {
            throw new CustomException();
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }    
}
