package evals.eval1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintLeaders {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = sc.nextInt();
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(arr[n-1]);
        for(int i=n-2;i>=0;i--) {
            int flag = 0;
            for(int j=i;j<n;j++) {
                if(arr[i] < arr[j]) {
                    flag = 1;
                    break;
                }
            }
            if(flag == 0 && !ans.contains(arr[i])) {
                ans.add(arr[i]);
            }
        }
        for(int i: ans) {
            System.out.print(i+" ");
        }
    }
}
