package cn.intv.dp;

/**
 * LeetCode 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * <p>
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 */
public class MinPathSum {

    public static void main(String[] args) {
        MinPathSum tool = new MinPathSum();
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}; // 7
        System.out.println(tool.minPathSum(grid));
    }

    /**
     * 动态规划: DP
     * <p>
     * 状态定义: 设 dp 为大小 m × n 矩阵，其中 dp[i][j] 的值代表直到走到 (i,j) 的最小路径和。
     * 转移方程: grid[i][j] = min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j]
     * <p>
     * 时间复杂度 O(M × N): 遍历整个 grid 矩阵元素。
     * 空间复杂度 O(1): 直接修改原矩阵，不使用额外空间。
     */
    public int minPathSum(int[][] grid) {
        int row = grid.length - 1, col = grid[0].length - 1;

        // 状态转移方程 1
        for (int i = 1; i <= row; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        // 状态转移方程 2
        for (int i = 1; i <= col; i++) {
            grid[0][i] += grid[0][i - 1];
        }

        // 状态转移方程 3
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[row][col];
    }
}



