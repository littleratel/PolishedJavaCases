package cn.intv.backtracking;

import java.util.*;

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
        System.out.println(tool.subsetsWithDup(new int[]{1, 2, 2}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayDeque<>(), res);
        return res;
    }

    // 1 2 2 3
    private void backtrack(int[] nums, int lev, Deque<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));

        for (int i = lev; i < nums.length; i++) {
            // 从每一个子树root开始，在它的处于同一层孩子中，如果值有相等的，则它们是重复，需要剪枝
            if (i != lev && nums[i] == nums[i - 1]) {
                // i != idx 非root的节点
                continue;
            }

            path.addLast(nums[i]);
            backtrack(nums, i + 1, path, res);
            path.removeLast();
        }
    }
}
