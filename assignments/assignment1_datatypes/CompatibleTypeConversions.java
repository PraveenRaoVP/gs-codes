package assignments.assignment1_datatypes;

/*1. Write a program to demonstrate compatible type conversions. For eg., float to int, double to float, int to short
 */

public class CompatibleTypeConversions {
    public static void main(String[] args) {
        float f = 10.5f;
        int i = (int)f;
        System.out.println("Float to int: " + i);

        double d = 10.5;
        float f1 = (float)d;
        System.out.println("Double to float: " + f1);

        int i1 = 10;
        short s = (short)i1;
        System.out.println("Int to short: " + s);

        
    }    
}
