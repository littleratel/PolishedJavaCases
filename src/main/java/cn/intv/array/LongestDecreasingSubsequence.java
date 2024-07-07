package cn.intv.array;

import java.util.Arrays;

/**
 * 最长递减子序列（LDS）
 * 给定一个整数数组，找到该数组中最长的递减子序列的长度
 * <p>
 * 输入：nums = [10, 9, 2, 5, 3, 7, 101, 18]
 * 输出：4
 */
public class LongestDecreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(lengthOfLDS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    private static int lengthOfLDS(int[] nums) {
        if (nums.length < 2) return nums.length;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
