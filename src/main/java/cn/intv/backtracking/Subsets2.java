package cn.intv.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。
 * <p>
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 */
public class Subsets2 {
    public static void main(String[] args) {
        Subsets2 tool = new Subsets2();
        System.out.println(tool.subsetsWithDup(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        backtrack(nums, 0, path, res);
        return res;
    }

    private void backtrack(int[] nums, int idx, Deque<Integer> path, List<List<Integer>> res) {


    }
}
