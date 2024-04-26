package extrapractice.minesweeper;

public class Board {
    int[][] board;

    public Board(int n) {
        board = new int[n][n];
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void setMines(int noOfMines) {
        int n = board.length;
        int mines = 0;

        while(mines < noOfMines) {
            int x = (int) (Math.random() * n);
            int y = (int) (Math.random() * n);

            if (board[x][y] != -1) {
                board[x][y] = -1;
                mines++;
            }
        }
    }

    // show the number of mines around some cells in the board
    public void setNumbers() {
        
    }

}
