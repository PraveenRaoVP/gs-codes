package codechef.start128d;

import java.util.Scanner;

public class XORandMultiply {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            long x = sc.nextInt();
            long y = (long) (Math.log(x) / Math.log(2)) + 1;
            long a = x << y;
            long b = (x << y) + x;
            System.out.println(a + " " + b);
        }
    }
}
