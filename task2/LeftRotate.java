package task2;

// program to left rotate multidiemensional array

public class LeftRotate {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotated = new int[n][m];

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                rotated[i][j] = arr[j][n-i-1];
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(rotated[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
