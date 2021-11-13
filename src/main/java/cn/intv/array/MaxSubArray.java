package cn.intv.array;

import org.junit.Test;

public class MaxSubArray {

    @Test
    public void main() {
        int[] nums = {5, 1, -3, -2, -3, 4, -1, -1, 1, -5, 8, -6};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray(new int[]{1, -3}));
    }

    public int maxSubArray(int[] nums) {
        int len;
        if (nums == null || (len = nums.length) == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        }

        int pre = 0, res = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            res = Math.max(res, pre);
        }

        return res;
    }
}