package extrapractice.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nqueens {
    public static void main(String[] args) {
        List<List<String>> result = solveNQueens(4);
        for (List<String> list : result) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println();
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        // using backtracking
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        
        solveNQueens(result, board, 0);

        return result;
        
    }

    private static void solveNQueens(List<List<String>> result, char[][] board, int row) {
        if (row == board.length) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                list.add(new String(board[i]));
            }
            result.add(list);
            return;
        }
        
        for (int i = 0; i < board.length; i++) {
            if (isValid(board, row, i)) {
                board[row][i] = 'Q';
                solveNQueens(result, board, row + 1);
                board[row][i] = '.';
            }
        }
    }

    public static boolean isValid(char[][] board, int row, int col) {
        for(int i=0; i<row; i++) {
            if(board[i][col] == 'Q') {
                return false;
            }

            int diff = row - i;
            if(col - diff >= 0 && board[i][col - diff] == 'Q') {
                return false;
            }

            if(col + diff < board.length && board[i][col + diff] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
