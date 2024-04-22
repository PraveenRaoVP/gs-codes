package extrapractice.sudoku_solver;

// given any dimension sudoku, solve it recursively using backtracking

public class Main {
    public static void main(String[] args) {
        // int[][] sudoku = {
        //     {1, 2, 0, 3, 0, 4, 0, 5, 6},
        //     {7, 0, 0, 0, 0, 6, 0, 0, 1},
        //     {0, 0, 0, 0, 0, 0, 0, 0, 0},
        //     {0, 8, 0, 4, 0, 9, 0, 2, 0},
        //     {0, 0, 0, 0, 6, 0, 0, 0, 0},
        //     {0, 3, 0, 5, 0, 1, 0, 8, 0},
        //     {0, 0, 0, 0, 0, 0, 0, 0, 0},
        //     {9, 0, 0, 2, 0, 0, 0, 0, 8},
        //     {8, 4, 0, 6, 0, 7, 0, 1, 9}
        // };

        int[][] sudoku = {
            {0,0,3,0},
            {0,0,1,0},
            {0,0,0,1},
            {3,0,2,0}
        };

        if (solveSudoku(sudoku)) {
            printSudoku(sudoku);
        } else {
            System.out.println("No solution exists");
        }
    }

    public static void printSudoku(int[][] sudoku) {
        for(int i=0;i<sudoku.length;i++) {
            for(int j=0;j<sudoku[0].length;j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean solveSudoku(int[][] sudoku) {
        int row = -1;
        int col = -1;

        boolean isEmpty = true;

        int n = sudoku.length;
        int m = sudoku[0].length;

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(sudoku[i][j]==0) {
                    row = i;
                    col = j;

                    isEmpty = false;
                    break;
                }
            }
            if(!isEmpty) {
                break;
            }
        }

        if(isEmpty && row==-1 && col==-1) {
            return true;
        }

        for(int num=1;num<=n;num++) {
            if(isSafe(sudoku, row, col, num)) {
                sudoku[row][col] = num;

                if(solveSudoku(sudoku)) {
                    return true;
                } else {
                    sudoku[row][col] = 0;
                }
            }
        }

        return false;
    }

    public static boolean isSafe(int[][] sudoku, int row, int col, int num) {
        int n = sudoku.length;
        int sqrt = (int)Math.sqrt(n);
        int r = row - row % sqrt;
        int c = col - col % sqrt;

        for(int i=0;i<n;i++) {
            if(sudoku[row][i]==num || sudoku[i][col]==num) {
                return false;
            }
        }

        for(int i=r; i<r+sqrt;i++) {
            for(int j=c;j<c+sqrt;j++) {
                if(sudoku[i][j]==num) {
                    return false;
                }
            }
        }

        return true;
    }
}
