package cn.intv.dp;

/**
 * LeetCode 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 */
public class Rob {
    public static void main(String[] args) {
//        System.out.println(rob(new int[]{1, 2, 3, 1})); // 4
//        System.out.println(rob(new int[]{2, 7, 9, 3, 1})); // 12

        System.out.println(robOpt(new int[]{1, 2, 3, 1})); // 4
        System.out.println(robOpt(new int[]{2, 7, 9, 3, 1})); // 12
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length <= 1) {
            return nums[0];
        } else if (nums.length <= 2) {
            return Math.max(nums[1], nums[0]);
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    public static int robOpt(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length <= 1) {
            return nums[0];
        } else if (nums.length <= 2) {
            return Math.max(nums[1], nums[0]);
        }

        // dp_1 : dp[i - 1]; dp_2 : dp[i - 2]
        int dp_2 = nums[0], dp_1 = Math.max(nums[1], nums[0]);
        for (int i = 2, tmp; i < nums.length; i++) {
            tmp = Math.max(nums[i] + dp_2, dp_1);
            dp_2 = dp_1;
            dp_1 = tmp;
        }

        return dp_1;
    }
}
