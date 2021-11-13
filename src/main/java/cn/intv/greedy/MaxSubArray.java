package cn.intv.greedy;

/**
 * 给定一个整数数组 nums，找到一个具有最大和的连续子数组（最少包含一个元素）
 *
 * */
public class MaxSubArray {

    public static void main(String[] args) {
        MaxSubArray tool = new MaxSubArray();
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; // 6
        System.out.println(tool.doSvc(nums1));

        int[] nums2 = {-1, 1, 2, 1}; // 4
        System.out.println(tool.doSvc(nums2));

        int[] nums4 = {-2, -8, -1, -5, -9};
        System.out.println(tool.doSvc(nums4)); // -1
    }

    private int doSvc(int[] nums) {
        int len;
        if (nums == null || (len = nums.length) == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        }

        //
        int max = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            sum = Math.max(sum, nums[i]);
            max = Math.max(max, sum);
        }

        return max;
    }
}