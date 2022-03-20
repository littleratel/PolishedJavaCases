package cn.intv.array;

/**
 * LeetCode 152. 乘积最大子数组
 * 给你一个整数数组 nums ，找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 子数组 是数组的连续子序列。
 * <p>
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class MaxProduct {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4})); // 6
        System.out.println(maxProduct(new int[]{-2, 0, -1})); // 0
        System.out.println(maxProduct(new int[]{-1, -2, -3, 0})); // 6
    }

    /**
     * 正反方向判断，计算最大值
     *
     */
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int max1 = nums[0], max2 = nums[len - 1];
        int res = Math.max(max1, max2);

        for (int i = 1; i < nums.length; ++i) {
            max1 = max1 == 0 ? 1 : max1;
            max1 = max1 * nums[i];
            res = Math.max(max1, res);

            max2 = max2 == 0 ? 1 : max2;
            max2 = max2 * nums[len - i - 1];
            res = Math.max(max2, res);
        }

        return res;
    }
}
