package cn.intv.dfsbfs;

/**
 * LeetCode 200,岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围
 */
public class NumIslands {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        System.out.println(numIslands(grid));
    }

    /**
     * 思路：
     *   1.从头开始寻找1，如果存在，就分别向它的前/后/左/右遍历寻找和它挨着的1，并把1改成X；
     *   2.以上步骤返回时，表示找到一座岛屿，岛屿计数加一；
     *   3.重复上述流程
     * */
    public static int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, m, n);
                    ans++;
                }
            }
        }

        return ans;
    }

    public static void dfs(char[][] grid, int i, int j, int m, int n) {
        if (i >= m || i < 0 || j >= n || j < 0) {
            return;
        }
        if (grid[i][j] == '0' || grid[i][j] == 'X') {
            return;
        }
        if (grid[i][j] == '1') {
            grid[i][j] = 'X';
        }

        dfs(grid, i + 1, j, m, n);
        dfs(grid, i - 1, j, m, n);
        dfs(grid, i, j + 1, m, n);
        dfs(grid, i, j - 1, m, n);
    }
}
