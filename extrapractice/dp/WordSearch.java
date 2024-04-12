package extrapractice.dp;

/*
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
using dynamic programming
 */

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]==word.charAt(0) && dfs(board,word,i,j,0,visited)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        if(index == word.length()) return true;

        if(i<0 || i>=board.length || j<0 || j>=board[0].length || visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }

        visited[i][j] = true;

        if(dfs(board, word, i+1, j, index+1, visited) || dfs(board, word, i-1,j,index+1, visited) || dfs(board, word, i, j+1, index+1, visited) || dfs(board, word, i, j-1, index+1, visited)) {
            return true;
        }

        visited[i][j] = false;

        return false;

    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println(ws.exist(board, word));
    }
}
