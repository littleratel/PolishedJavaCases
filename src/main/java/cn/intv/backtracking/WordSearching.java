package cn.intv.backtracking;

/**
 * Leetcode 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word;
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class WordSearching {
    private boolean found = false;

    public static void main(String[] args) {
        WordSearching tool = new WordSearching();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(tool.exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        int row = board.length, col = board[0].length;
        boolean[][] visted = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (found) {
                    return true;
                }
                backtracking(board, i, j, word, 0, visted);
            }
        }

        return found;
    }

    private void backtracking(char[][] board, int i, int j, String word, int pos, boolean[][] visted) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visted[i][j] || found) {
            return;
        }

        if (board[i][j] != word.charAt(pos)) {
            return;
        }

        if (pos == word.length() - 1) {
            found = true;
            return;
        }

        visted[i][j] = true;
        pos++;
        backtracking(board, i, j - 1, word, pos, visted);
        backtracking(board, i, j + 1, word, pos, visted);
        backtracking(board, i - 1, j, word, pos, visted);
        backtracking(board, i + 1, j, word, pos, visted);
        visted[i][j] = false;
    }
}
