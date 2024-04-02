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
        int num = sc.nextInt();
        int ans = 0;
        int n = num;
        int count = 0;
        while(n > 0) {
            count ++;
            n/=10;
        }
        int[] arr = new int[count];
        n = num;
        int i=0;
        while(n > 0) {
            arr[i++] = n%10;
            n/=10;
        }
        int[] arr1 = new int[count];
        int index = 0;
        for(i=count-1;i>=0;i--) {
            arr1[index++] = arr[i];
        }
        for(i=0;i<count;i++) {
            int temp = 0;
            
            for(int j=0;j<count;j++) {
                if(i == j) {
                    continue;
                }
                temp = temp*10 + arr1[j];
            }

            int root = (int)Math.sqrt(temp);
            if(root*root == temp) {
                ans = temp;
                break;
            }
        }
        n = num;
        int ans2 = 0;
        while(n > 0) {
            int digit = n%10;
            n/=10;
            if(digit == 0 || digit == 1 || digit == 4 || digit == 9) {
                continue;
            }
            System.out.println("digit: " + digit);
            ans2++;
        }
        System.out.println(ans+" "+ans2);
    }
}
