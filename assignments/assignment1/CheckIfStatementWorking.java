package assignments.assignment1;

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
