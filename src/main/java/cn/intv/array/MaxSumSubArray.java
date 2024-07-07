package cn.intv.array;

import org.junit.Test;

/**
 * 最大连续子数组之和
 * <p>
 * 输入：[2, -6, 1, -3, 4, -1, -1, 3, -5]
 * 输出：5
 * 其中和最大的连续子数组位：[4, -1, -1, 3]
 */
public class MaxSumSubArray {

    @Test
    public void main() {
        int[] nums = {2, -6, 1, -3, 4, -1, -1, 3, -5};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray(new int[]{1, -3}));
    }

    /**
     * Kadane 算法：
     * 基本思想是遍历数组，同时维护两个变量：当前元素之前的最大子数组和（局部最大值）和全局最大值。
     * 对于数组中的每个元素，算法会更新局部最大值，如果局部最大值为负，则将其重置为当前元素（因为负数会减小总和）
     * 时间复杂度是O(n)，n 是数组的长度；
     */
    public int maxSubArray(int[] nums) {
        int len;
        if (nums == null || (len = nums.length) == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        }

        int maxEndingHere = 0, res = nums[0];
        for (int x : nums) {
            maxEndingHere = Math.max(maxEndingHere + x, x);
            res = Math.max(res, maxEndingHere);
        }

        return res;
    }

    /**
     * 动态规划实现
     * 状态转移方程：
     * dp[i] = max(nums[i], dp[i-1] + nums[i])
     */
    private int maxSubArrayDp(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
