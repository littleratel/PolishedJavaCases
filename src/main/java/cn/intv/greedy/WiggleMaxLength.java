package cn.intv.greedy;

/**
 * [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的</br>
 * [1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 子序列: 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序</br>
 *
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度
 * */
public class WiggleMaxLength {

    public static void main(String[] args) {
        WiggleMaxLength tool = new WiggleMaxLength();
        int[] nums1 = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8}; //7
        System.out.println(tool.doSvc(nums1));
        int[] nums2 = {1, 7, 4, 9, 2, 5};
        System.out.println(tool.doSvc(nums2)); // 6
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(tool.doSvc(nums3)); //2
        int[] nums4 = {0, 0, 0, 0};
        System.out.println(tool.doSvc(nums4)); // 1
        int[] nums5 = {3, 3, 3, 2, 5};
        System.out.println(tool.doSvc(nums5)); // 3
    }

    private int doSvc2(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int curDiff = 0; // 当前一对差值
        int preDiff = 0; // 前一对差值
        int result = 1;  // 记录峰值个数，序列默认序列最右边有一个峰值
        int len = nums.length - 1;
        for (int i = 0; i < len; i++) {
            curDiff = nums[i + 1] - nums[i];
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                result++;
                preDiff = curDiff;
            }
        }

        return result;
    }

    private int doSvc(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int up = 1;
        int down = 1;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            }else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }

        return Math.max(up, down);
    }
}