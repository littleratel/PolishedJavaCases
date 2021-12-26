package cn.intv.greedy;

/**
 * 给定一个非负整数数组，最初位于数组的第一个位置。
 * 数组中的每个元素代表在该位置可以跳跃的最大长度。
 * 判断是否能够到达最后一个位置。
 *
 * 输入: [2,3,1,1,4] 输出: true
 * 输入: [3,2,1,0,4] 输出: false
 * */
public class JumpingGame {
    public static void main(String[] args) {
        int[] steps1 = {2, 3, 1, 1, 4};
        int[] steps2 = {3, 2, 1, 0, 4};
        System.out.println(canJump(steps1));
        System.out.println(canJump(steps2));
    }

    private static boolean canJump(int[] nums) {
        if (nums.length == 1) return true;

        int len = nums.length - 1;
        int maxCover = nums[0];
        for (int i = 1; i <= maxCover; i++) {
            maxCover = Math.max(maxCover, i + nums[i]);
            if (maxCover >= len) {
                return true;
            }
        }

        return false;
    }
}
