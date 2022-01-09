package cn.intv.greedy;

import org.junit.Test;

/**
 * LeetCode 45. 跳跃游戏 II
 * <p>
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 *
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
     */
    //
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int curMaxCover = 0, nextMaxcover = 0;
        int res = 0;
        for (int i = 0, len = nums.length - 1; i < len; i++) {
            nextMaxcover = Math.max(nextMaxcover, i + nums[i]);
            if (nextMaxcover >= len) {
                return ++res;
            }

            if (i == curMaxCover) {
                curMaxCover = nextMaxcover;
                res++;
            }
        }

        return res;
    }
}
