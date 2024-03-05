package task2;

import java.util.Scanner;



public class Program24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 1;
        int i=1;
        while(i<=n) {
            for(int j=1;j<=n-i;j++) {
                System.out.print(" ");
            }
            int j=0;
            while(j<count && j<=i) {
                System.out.print(i+" ");
                j++;
                i++;
            }
            System.out.println();
            count++;
        }
        sc.close();
    }
}
