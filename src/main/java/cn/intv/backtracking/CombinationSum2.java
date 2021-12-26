package cn.intv.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 40. 组合总和 II
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和 为target的组合
 * candidates 中的每个数字在每个组合中只能使用一次.
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出: [[1,2,2],[5]]
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        CombinationSum2 tool = new CombinationSum2();

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> trace = new ArrayList<>();
        backtrace(candidates, target, 0, trace, 0, res);
        return res;
    }

    public void backtrace(int[] candidates, int target, int start, List<Integer> trace, int sum, List<List<Integer>> res) {
        if (sum == target) {
            res.add(new ArrayList<>(trace));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 避免重复
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] + sum <= target) {
                trace.add(candidates[i]);
                backtrace(candidates, target, i + 1, trace, sum + candidates[i], res);
                trace.remove(trace.size() - 1);
            } else {
                // 后面的 candidates[i] 都比当前大，不用试了
                break;
            }
        }
    }
}
