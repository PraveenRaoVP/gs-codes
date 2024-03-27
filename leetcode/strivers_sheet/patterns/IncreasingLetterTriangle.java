package leetcode.strivers_sheet.patterns;

public class IncreasingLetterTriangle {
    public static void nLetterTriangle(int n) {
        for(int i=65;i<65+n;i++) {
            for(int j=65;j<=i;j++) {
                System.out.print((char)j+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        nLetterTriangle(5);
    }
}
