package cn.intv.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 78. 子集
 * <p>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class Subsets {
    public static void main(String[] args) {
        Subsets tool = new Subsets();
        System.out.println(tool.subsets(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayDeque<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int idx, Deque<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));

        for (int i = idx; i < nums.length; i++) {
            path.addLast(nums[i]);
            backtrack(nums, i + 1, path, res);
            path.removeLast();
        }
    }
}
