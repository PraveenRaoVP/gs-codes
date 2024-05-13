package dsa.number_conversions;

public class HexaDecimalToBinary {
    // Function to convert hexadecimal to binary
    public static String hexaDecimalToBinary(String hexaDecimal) {
        StringBuilder binary = new StringBuilder();
        for(int i = 0; i < hexaDecimal.length(); i++) {
            char ch = hexaDecimal.charAt(i);
            if(ch >= '0' && ch <= '9') {
                binary.append(String.format("%04d", Integer.parseInt(Integer.toBinaryString(ch - '0'))));
            } else {
                binary.append(String.format("%04d", Integer.parseInt(Integer.toBinaryString(ch - 'A' + 10))));
            }
        }
        return binary.toString();
    }

    public static void main(String[] args) {
        String hexaDecimal = "A";
        System.out.println(hexaDecimal + " in binary is " + hexaDecimalToBinary(hexaDecimal));
    }
    
}
