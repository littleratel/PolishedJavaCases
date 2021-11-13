package cn.intv.search;

/**
 * 实现有重复数字的升序数组的二分查找
 *
 * 给定一个有重复数字的升序数组 nums 和一个目标值 target.
 * 写一个函数搜索 nums 中的第一个出现的target，如果目标值存在返回下标，否则返回 -1
 *
 * 输入： [1,2,4,4,5],4
 * 返回值： 2
 *
 * 输入： [1,1,1,1,1],1
 * 返回值： 0
 * */
public class HalfSearch {
    public static void main(String[] args) {
        HalfSearch tool = new HalfSearch();
        int[] nums = {1, 2, 4, 4, 5};
        System.out.println(tool.search(nums, 4));
        System.out.println(tool.search(nums, 3));
        int[] nums1 = {1, 1, 1, 1, 1};
        System.out.println(tool.search(nums1, 1));
    }

    private int search(int[] nums, int target) {
        return doHalfSearch(nums, target, 0, nums.length - 1);
    }

    private int doHalfSearch(int[] nums, int target, int right, int left) {
        if (right > left) {
            return -1;
        }

        int mid = (right + left) / 2;
        if (target == nums[mid]) {
            return findFirst(nums, target, right, mid);
        } else if (target < nums[mid]) {
            return doHalfSearch(nums, target, right, mid-1);
        } else {
            return doHalfSearch(nums, target, mid + 1, left);
        }
    }

    private int findFirst(int[] nums, int target, int right, int left) {
        int mid = (right + left) / 2;
        if (left == 0) {
            return 0;
        } else if (nums[left - 1] < target) {
            return left;
        } else if (nums[right] == target) {
            return right;
        } else if (nums[mid] == target) {
            return findFirst(nums, target, right, mid);
        } else if (nums[mid] < target) {
            return findFirst(nums, target, mid, right);
        }

        return -1;
    }
}