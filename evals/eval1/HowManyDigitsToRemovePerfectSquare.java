package evals.eval1;

import java.util.Scanner;

/*
 *  Given an integer n, we need to find how many digits remove from the number to
make it a perfect square.
Examples :
Input : 8314
Output: 81 2
Explanation: If we remove 3 and 4 number becomes 81 which is a perfect square.
Input : 57
Output : -1
 */
public class HowManyDigitsToRemovePerfectSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] digits = getDigits(n);

        int remove = -1;
        int sq = -1;

        for(int i=0;i<digits.length;i++) {
            int num = getNumberAfterRemovingDigit(digits, i);
            if(isPerfectSquare(num)) {
                remove = i;
                sq = num;
                break;
            }
        }
        System.out.println(remove==-1 ? -1 : sq + " " + remove);
    }

    public static int[] getDigits(int n ) {
        String number = Integer.toString(n);
        int[] digits = new int[number.length()];
        for(int i=0;i<number.length();i++) {
            digits[i] = number.charAt(i) - '0';
        }
        return digits;
    }

    public static int getNumberAfterRemovingDigit(int[] digits, int index) {
        int num = 0;
        for(int i=0;i<digits.length;i++) {
            if(i!=index) {
                num = num*10 + digits[i];
            }
        }
        return num;
    }

    public static boolean isPerfectSquare(int n) {
        int sqrt = (int)Math.sqrt(n);
        return sqrt*sqrt == n;
    }
}
