package assignments.assignment3_control_statements;

import java.util.Scanner;

public class PrintGreatestNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a=sc.nextInt(),b=sc.nextInt(),c=sc.nextInt();
        if(a>b && a>c)
            System.out.println("The greatest number out of the three is: " + a);
        else if(b>c)
            System.out.println("The greatest number out of the three is: " + b);
        else
            System.out.println("The greatest number out of the three is: " + c);
    }
}
