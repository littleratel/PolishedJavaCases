package cn.intv.greedy;

/**
 * 给定一个非负整数数组，最初位于数组的第一个位置。
 * 数组中的每个元素代表在该位置可以跳跃的最大长度。
 *
 * 使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 输入: [2,3,1,1,4] 输出: 2
 * 输入: [2,3,0,1,4] 输出: 2
 * */
public class JumpingGame2 {
    public static void main(String[] args) {
//        int[] steps1 = {2, 3, 1, 1, 4};
//        System.out.println(canJump(steps1));
//        int[] steps2 = {2, 1};
//        System.out.println(canJump(steps2));
//        int[] steps3 = {3, 2, 1, 0, 4};
//        System.out.println(canJump(steps3));
        int[] steps4 = {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0};
        System.out.println(canJump(steps4));
    }

    private static int canJump(int[] nums) {
        if (nums.length == 1) return 0;

        int len = nums.length - 1;
        int ans = 0;
        int curMaxCover = nums[0], nextMaxcover = nums[0];
        for (int i = 1; i <= len; i++) {
            nextMaxcover = Math.max(nextMaxcover, i + nums[i]);
            if (curMaxCover > len) return ans + 1;
            if (i == curMaxCover) {
                if (nextMaxcover == i) {
                    return -1;
                }

                curMaxCover = nextMaxcover;
                ans++;
            }
        }

        return ans;
    }
}
