// Write a program to print the number 7.50 without using String(print 7.50 not 7.5)

package task2;

public class PrintDoubleWithoutString {
    public static void main(String[] args) {
        double original = 7.50;
        double number = Math.floor(original);
        int numberInt = (int)number;
        double decimal = original - number;

        // or
        System.out.println(numberInt + "." + (int)(decimal*100));

        // or
        System.out.println(numberInt + "." + (int)(decimal*10) + (int)(decimal*100)%10);
    }
}
