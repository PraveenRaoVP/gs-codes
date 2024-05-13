package dsa.number_conversions;

import java.util.Scanner;

public class DecimalToBinary {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int decimal = sc.nextInt();
        System.out.println(decimal + " in binary is " + decimalToBinary(decimal));
    }

    public static String decimalToBinary(int decimal) {
        StringBuilder binary = new StringBuilder();
        while(decimal > 0) {
            binary.insert(0, decimal % 2);
            decimal /= 2;
        }
        return binary.toString();
    }
    
}
