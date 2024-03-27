package leetcode.strivers_sheet.patterns;

public class NinjaNumberPattern {
    public static void getNumberPattern(int n) {
        int[][] matrix = new int[n*2-1][n*2-1];
        int top = 0, bottom = n*2-2;
        int left = 0, right = n*2-2;

        while(top <= bottom && left <= right) {
            for(int i=left;i<right;i++) {
                matrix[top][i] = n;
            }
            top++;
            for(int i=top;i<bottom;i++) {
                matrix[i][right] = n;
            }
            right--;
            for(int i=right;i>=left;i--) {
                matrix[bottom][i] = n;
            } 
            bottom--;
            for(int i=bottom;i>=top;i--) {
                matrix[i][left] = n;
            }
            left++;
            n--;
        }
        for(int i=0;i<n*2-1;i++) {
            for(int j=0;j<n*2-1;j++) {
                System.out.print(matrix[i][j]+"*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        getNumberPattern(3);
    }
}
