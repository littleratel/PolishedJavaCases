package cn.intv.dp;

/**
 *
 *
 *
 * */
public class HouseRobber {
    public static void main(String[] args) {
        System.out.println(robDp(new int[]{1, 2, 3, 1})); // 4
        System.out.println(robDp(new int[]{2, 7, 9, 3, 1})); // 12
    }

    private static int robDp(int[] nums) {
        int len = nums.length;
        if (len <= 1) return len == 0 ? 0 : nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < len; i++) {
            // 状态转移方程:
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        return dp[len - 1];
    }
}
