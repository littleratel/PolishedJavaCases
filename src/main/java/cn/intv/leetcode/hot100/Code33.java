package cn.intv.leetcode.hot100;

/**
 * 整数数组 nums 按升序排列，数组中的值互不相同，数组按照某一下标旋转；
 * 例如，[0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2]
 * 给定一旋转后的数组nums和一个整数target，如果nums中存在这个目标值target，则返回它的下标，否则返回-1 。
 * <p>
 * 要求时间复杂度为 O(logn)
 */
public class Code33 {

    public static void main(String[] args) {
        Code33 tool = new Code33();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int res = tool.search(nums, 0);
        System.out.println(res);
    }

    public int search(int[] nums, int target) {
        int len = nums.length - 1;
        if (len == 0) {
            return nums[0] == target ? 0 : -1;
        }

        int l = 0, r = len, mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // 0～mid 有序
            if (nums[0] < nums[mid]) {
                if (target >= nums[0] && target < nums[mid]) {
                    r = --mid; // 在有序区间内
                } else {
                    l = ++mid;
                }

                continue;
            }

            // mid~len 有序
            if (target > nums[mid] && target <= nums[len]) {
                l = ++mid; // 在有序区间内
            } else {
                r = --mid;
            }
        }

        return -1;
    }
}
