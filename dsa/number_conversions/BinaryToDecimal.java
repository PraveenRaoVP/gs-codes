package dsa.number_conversions;

public class BinaryToDecimal {
    public static void main(String[] args) {
        String binary = "1010";
        System.out.println(binary + " in decimal is " + binaryToDecimal(binary));
    }

    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        for(int i = 0; i < binary.length(); i++) {
            decimal += (binary.charAt(i) - '0') * Math.pow(2, binary.length() - i - 1);
        }
        return decimal;
    }
    
}
