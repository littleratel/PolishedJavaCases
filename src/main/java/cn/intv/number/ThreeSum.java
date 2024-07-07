package cn.intv.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Leetcode 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeSum {
    public static void main(String[] args) {
//        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
//        System.out.println(threeSum(new int[]{-2, 0, 0, 2, 2}));
        // [[-4,0,4],[-4,1,3],[-3,-1,4],[-3,0,3],[-3,1,2],[-2,-1,3],[-2,0,2],[-1,-1,2],[-1,0,1]]
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4}));
    }

    /**
     * 双指针法
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);

        int sum, l, r;
        for (int i = 0, len = nums.length - 3; i <= len; i++) {
            // 提前返回，nums[]是排序后的，若nums[i]>0, i之后的数据都>0
            if (nums[i] > 0) return res;
            // 过滤重复
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            l = i + 1;
            r = nums.length - 1;

            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l++], nums[r--]));

                    // 过滤重复
                    while (l < r && nums[l] == nums[l - 1]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return res;
    }

    /**
     * 3层循环方案
     */
    private static List<List<Integer>> threeSumLoop(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        int target = 0;

        for (int i = 0, len = nums.length - 3; i <= len; i++) {
            // 剪枝
            if (nums[i] > target) return res;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j <= nums.length - 2; j++) {
                // 剪枝
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int curTarget = target - nums[i] - nums[j];
                if (nums[j] > curTarget) break;

                for (int k = j + 1; k < nums.length; k++) {
                    // 剪枝
                    if (nums[k] > curTarget) break;
                    if (k > j + 1 && nums[k] == nums[k - 1]) continue;

                    if (curTarget == nums[k]) {
                        List<Integer> path = new ArrayList<>();
                        path.add(nums[i]);
                        path.add(nums[j]);
                        path.add(nums[k]);
                        res.add(path);
                    }
                }
            }
        }

        return res;
    }
}
