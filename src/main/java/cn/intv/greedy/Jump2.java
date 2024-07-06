package cn.intv.greedy;

import org.junit.Test;

/**
 * LeetCode 45. 跳跃游戏 II
 * <p>
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 */
public class Jump2 {

    @Test
    public void main() {
        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));

        int[] arr2 = {3, 2, 1, 1, 4};
        System.out.println(jump(arr2));
    }

    /**
     * 贪心算法
     * 时间复杂度是O(n)
     */
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int curMaxCover = 0, nextMaxCover = 0;
        int res = 0;
        for (int i = 0, len = nums.length - 1; i < len; i++) {
            nextMaxCover = Math.max(nextMaxCover, i + nums[i]);
            if (nextMaxCover >= len) {
                return ++res;
            }

            // 到达当前能到达的最远位置时，就必须进行下一次跳跃，此时更新当前位置，并将跳跃次数增加1
            if (i == curMaxCover) {
                curMaxCover = nextMaxCover;
                res++;
            }
        }

        return res;
    }

    /**
     * 动态规划
     * 时间复杂度是O(n^2)
     */
    public int jumpDp(int[] nums) {
        int[] dp = new int[nums.length];
        int len = nums.length - 1;

        // 初始化为最大值，表示无法到达
        for (int i = 1; i <= len; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // dp[0] = 0，起点不需要跳跃
        for (int i = 0; i < len; i++) {
            // 更新可达位置的最小跳跃次数
            for (int j = i + 1; j <= i + nums[i] && j <= len; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }

        return dp[len];
    }
}
