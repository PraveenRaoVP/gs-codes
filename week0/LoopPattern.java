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
        int bx1 = 0, bx2 = n-1, by1 = 0, by2 = n-1;
        int noOfElements = calcNoOfElementsInLoop(n);
        int turns = 1;
        int i=0,j=0;
        while(noOfElements>0) {
            
        }
       
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(pattern[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        printPattern(5);   
    }
    
}
