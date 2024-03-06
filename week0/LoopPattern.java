package week0;

public class LoopPattern {

    /*print the following pattern like a loop
     * 
     * Sample input: 3
     * Output:
     * 1 6 5
     *   2 4
     *     3
     * 
     * Sample Input: 5
     * Output:-
     * 1 12 11 10 9
     *    2 13 15 8
     *       3 14 7
     *          4 6
     *            5
     * 
     * matrix form :-
     * 
     * [1,12,11,10,9],
     * [0,2,13,15,8],
     * [0,0,3,14,7],
     * [0,0,0,4,6],
     * [0,0,0,0,5]
     * 
     * 1,2,3,4,5 along the diagonal, then 6,7,8,9,10,11,12,13,14,15 in the upper triangle
     * 
     * 
     */

    public static int calcNoOfElementsInLoop(int n) {
        if(n==0) return n;
        return n+calcNoOfElementsInLoop(n-1);
    }

    public static void printPattern(int n) {
        int[][] pattern = new int[n][n];
        int start = 1;
        int row = n-1;
        int col = n-1;
        int rowShift = -1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) 
            {
                if(i==j) {
                    pattern[i][j] = start++;
                }
                if(i==n-1 && j==n-1) {
                    row = i-1;
                    col = j;

                    while(pattern[row][col]==0) {
                        while(row > rowShift) {
                            pattern[row][col] = start++;
                            row--;
                        }
                        row++;
                        rowShift++;
                        col--;
                        while(pattern[row][col]==0) {
                            pattern[row][col] = start++;
                            col--;
                        }
                        row++;
                        col+=2;

                        while(pattern[row][col]==0) {
                            pattern[row][col] =start++;
                            row++;
                            col++;
                        }
                        row-=2;
                        col--;
                    }         

                }
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(pattern[i][j]!=0)
                    System.out.print(pattern[i][j]+" ");
                else 
                    System.out.print("   ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        printPattern(5);   
    }
}
