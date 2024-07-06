package cn.intv.dp;

/**
 * n个物品每个物品都有一定的价值，把它们分给2个人，怎么分2个人的价值差最小
 * <p>
 * 这是一个经典的分配问题，可以使用动态规划来解决。
 * 首先，计算出所有物品的总价值sum，然后使用动态规划算法，
 * 在不超过sum/2的条件下，找到一个子集，使得其总价值尽可能接近sum/2。
 */
public class FindArrayMinDiff {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 2, 1};
        System.out.println("最小差距为:" + findMinDiff(arr));
    }

    static int findMinDiff(int[] arr) {
        int sum = 0;
        for (int value : arr) {
            sum += value;
        }

        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= sum; i++) {
            dp[0][i] = false;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] > j) {
                    // 如果当前物品的价值大于j，则该物品不能被包含在内
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 否则，考虑包含或不包含当前物品
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        int diff = Integer.MAX_VALUE;

        // 找到最接近sum/2的子集
        for (int j = sum / 2; j >= 0; j--) {
            if (dp[n][j]) {
                diff = sum - 2 * j;
                break;
            }
        }

        return diff;
    }
}
