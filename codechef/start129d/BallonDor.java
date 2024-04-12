package codechef.start129d;

import java.util.Scanner;

/*
Ballon d'Or

Leo has already won the Ballon d'Or 8 times, so he is really impressed with it (is he?).

Leo has an array A containing N integers. Each element of this array is either 1 or 2.
He wants to figure out if the product of all the elements of the array can be written as an 8-th power of some integer, i.e., 8^k for some integer k.

Print "Yes" if it can and "No" if it can't.
 */

public class BallonDor {
    public static boolean isEighthPower(double n) {
        // using log
        double x = Math.log(n) / Math.log(8);
        return Math.pow(8, Math.round(x)) == n;
    }
        public static void main (String[] args) throws java.lang.Exception
        {
            // your code goes here
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-- > 0) {
                int n =sc.nextInt();
                double product = 1;
                for(int i=0;i<n;i++) {
                    int a = sc.nextInt();
                    product*=a;
                }
                if(isEighthPower(product)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }    
}
