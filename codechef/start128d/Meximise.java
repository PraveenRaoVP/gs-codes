package codechef.start128d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Meximise {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            long n = sc.nextInt();
            int[] arr = new int[(int) (n + 1)];
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr, 1, (int) (n + 1));
            long result = 0;
            for (int i = 1; i <= n; i++) {
                result += Math.abs((i - 1) - arr[i]);
            }
            System.out.println(result);
        }
        sc.close();
    }

}