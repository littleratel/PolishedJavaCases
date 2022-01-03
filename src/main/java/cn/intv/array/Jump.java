package cn.intv.array;

import org.junit.Test;

/**
 * LeetCode 55. 跳跃游戏
 * <p>
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 */
public class Jump {

    @Test
    public void main() {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));

        int[] arr2 = {3, 2, 1, 0, 4};
        System.out.println(canJump(arr2));
    }

    /**
     * 贪心算法
     */
    public boolean canJump(int[] nums) {
        for (int i = 0, cover = 0; i < nums.length; i++) {
            if (cover < i) return false;  // cover = 之前的某个i: (i + nums[i])

            cover = Math.max(cover, i + nums[i]);
        }

        return true;
    }
}
