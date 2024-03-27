package leetcode.strivers_sheet.patterns;

public class NumberCrown {
    public static void numberCrown(int n) {
        int spaces = (n-1)*2;

        for(int i=1;i<=n;i++) {
            for(int j =1;j<=i;j++) {
                System.out.print(j+" ");
            }
            for(int j=0;j<spaces;j++) {
                System.out.print(" ");
            }
            for(int j=i;j>=1;j--) {
                System.out.print(j+" ");
            }
            System.out.println();
            spaces-=2;
        }

    }

    public static void main(String[] args) {
        numberCrown(5);
    }
}
