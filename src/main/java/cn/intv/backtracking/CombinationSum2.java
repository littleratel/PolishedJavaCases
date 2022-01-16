package cn.intv.backtracking;

import java.util.*;

/**
 * LeetCode 40. 组合总和 II
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和 为target的组合。
 * 注意：candidates 中的每个数字在每个组合中只能使用一次.
 *
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出: [[1,2,2],[5]]
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        CombinationSum2 tool = new CombinationSum2();
        // [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
        System.out.println(tool.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        // [[1,2,2], [5]]
//        System.out.println(tool.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, new ArrayDeque<>(), 0, res);
        return res;
    }

    public void backtracking(int[] candidates, int target, int start, Deque<Integer> path, int sum, List<List<Integer>> res) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 去重 剪枝
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // 优化 剪枝
            if (candidates[i] + sum > target) {
                break;
            }

            path.addLast(candidates[i]);
            backtracking(candidates, target, i + 1, path, sum + candidates[i], res);
            path.removeLast();
        }
    }
}
