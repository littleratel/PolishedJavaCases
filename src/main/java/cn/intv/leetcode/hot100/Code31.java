package cn.intv.leetcode.hot100;

import util.ArrayUtil;

public class Code31 {

    public static void main(String[] args) {
        Code31 tool = new Code31();
        int[] nums = {1, 1, 5};
        tool.nextPermutation(nums); // 1,5,1
        ArrayUtil.print(nums);
    }

    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }

        // 找到nums[minIdx] < nums[minIdx+1]的索引
        int minIdx = nums.length - 2;
        while (minIdx >= 0 && nums[minIdx] >= nums[minIdx + 1]) {
            minIdx--;
        }

        // 如果nums从左到右是依次递减的，从小到大排序
        if (minIdx < 0) {
            sort(nums, 0, nums.length - 1);
            return;
        }

        // 从minIdx+1开始向右遍历，找出 nums[k] > nums[minIdx] > nums[k+1] 的索引k
        int key = nums[minIdx];
        int idx = minIdx + 1;
        // 找到key >= nums[idx]时的idx
        while (idx < nums.length && key < nums[idx]) {
            idx++;
        }
        idx--; // 此刻的nums[idx] > nums[minIdx]

        // 将 nums[minIdx] 与 nums[idx] 互换
        nums[minIdx] = nums[idx];
        nums[idx] = key;

        // 从minIdx + 1开始，从小到大排列
        sort(nums, minIdx + 1, nums.length - 1);
    }

    private void sort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }

        int key;
        while (start < end) {
            key = nums[start];
            nums[start++] = nums[end];
            nums[end--] = key;
        }
    }
}
