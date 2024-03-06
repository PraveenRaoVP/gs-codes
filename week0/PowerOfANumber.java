package week0;

public class PowerOfANumber {
    public static int powerN(int base, int power) {
        if(power == 0) return 1;
        return base * powerN(base, power-1);
    }
    public static void main(String[] args) {
        System.out.println(powerN(2, 16));
    }
}