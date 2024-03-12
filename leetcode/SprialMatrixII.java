package leetcode;

public class SprialMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int count = 1;
        int top = 0, bottom = n-1, left = 0, right=n-1;
        while(top<=bottom && left <= right) {
            for(int i=left;i<=right;i++) {
                matrix[top][i] = count++;
            }
            top++;
            for(int i=top;i<=bottom;i++) {
                matrix[i][right] = count++;
            }
            right--;
            for(int i=right;i>=left;i--) {
                matrix[bottom][i]=count++;
            }
            bottom--;
            for(int i=bottom;i>=top;i--) {
                matrix[i][left]=count++;
            }
            left++;
        }
        return matrix;
    }
}