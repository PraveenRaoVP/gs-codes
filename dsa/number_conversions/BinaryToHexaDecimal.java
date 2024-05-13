package dsa.number_conversions;

public class BinaryToHexaDecimal {
    // Function to convert binary to hexadecimal
    public static String binaryToHexaDecimal(String binary) {
        int decimal = 0;
        for(int i = 0; i < binary.length(); i++) {
            decimal += (binary.charAt(i) - '0') * Math.pow(2, binary.length() - i - 1);
        }
        
        StringBuilder hexaDecimal = new StringBuilder();
        while(decimal > 0) {
            int remainder = decimal % 16;
            if(remainder < 10) {
                hexaDecimal.insert(0, remainder);
            } else {
                hexaDecimal.insert(0, (char)('A' + remainder - 10));
            }
            decimal /= 16;
        }
        
        return hexaDecimal.toString();
    }

    public static void main(String[] args) {
        String binary = "1010";
        System.out.println(binary + " in hexadecimal is " + binaryToHexaDecimal(binary));
    }
    
}
