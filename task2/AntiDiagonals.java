package task2;

/*
  Given a N*N square matrix, return an array of its anti-diagonals. Look at the example for more
details.
Example
Input:
1 2 3
4 5 6
7 8 9
Output : [
[1],
[2, 4],
[3, 5, 7],
[6, 8],
[9]
]
 * 
 */

public class AntiDiagonals {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        int n = arr.length;
        int m = arr[0].length;
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

        int[][] antiDiagonals = new int[2*n-1][];
        int k = 0;
        for(int i=0;i<n;i++) {
            int x = i;
            int y = 0;
            int z = 0;
            int[] temp = new int[i+1];
            while(x>=0) {
                temp[z++] = arr[x][y];
                x--;
                y++;
            }
            antiDiagonals[k++] = temp;
        }
        
        for(int i=1;i<n;i++) {
            int x = n-1;
            int y = i;
            int z = 0;
            int[] temp = new int[n-i];
            while(y<n) {
                temp[z++] = arr[x][y];
                x--;
                y++;
            }
            antiDiagonals[k++] = temp;
        }

        for(int i=0;i<2*n-1;i++) {
            for(int j = antiDiagonals[i].length-1;j>=0;j--) {
                System.out.print(antiDiagonals[i][j]+" ");
            }
            System.out.println();
        }
        
    }
}
