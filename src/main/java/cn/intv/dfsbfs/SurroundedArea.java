package cn.intv.dfsbfs;

/**
 * Leetcode 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由 X 和 O 构成，找到所有被 X 围绕的区域，并将该区域中所有的 O 替换成 X
 */
public class SurroundedArea {
    public static void main(String[] args) {
        SurroundedArea tool = new SurroundedArea();
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        tool.solve(board);
        System.out.println();
    }

    /**
     * 1. 搜索四边上的"O"，并与之相连的"O"，将它们变为 "M"；
     * 2. 遍历二维数组，将 O -> X, M -> O
     */
    public void solve(char[][] board) {
        int row = board.length, col = board[0].length;

        // 搜索四边上的"O"，并与之相连的"O"，将它们变为 "M"；
        for(int i=0; i< col; i++){
            dfs(board, 0, i);  // 第 0 行
            dfs(board, row-1, i);  // 最后 1 行
        }
        for(int i=1, idx = row-1; i< idx; i++){
            dfs(board, i, 0);  // 第 0 列
            dfs(board, i, col-1); // 最后 1 列
        }

        // 遍历二维数组，将 O -> X, M -> O
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'M') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != 'O') return;

        board[x][y] = 'M';//把所有的 O -> M

        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }
}
