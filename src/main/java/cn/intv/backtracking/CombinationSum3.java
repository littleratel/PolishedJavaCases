package cn.intv.backtracking;

import java.util.*;

/**
 * LeetCode 216. 组合总和 III
 * <p>
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 所有数字都是正整数； 解集不能包含重复的组合。 
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {
    public static void main(String[] args) {
        CombinationSum3 tool = new CombinationSum3();
        System.out.println(tool.combinationSum3(3, 7));
        System.out.println(tool.combinationSum3(3, 9));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrace(k, n, 0, new ArrayDeque<Integer>(), res, 1);
        return res;
    }

    public void backtrace(int k, int target, int sum, Deque<Integer> trace, List<List<Integer>> res, int idx) {
        if (sum == target) {
            if (trace.size() == k) {
                res.add(new ArrayList<>(trace));
            }

            return;
        }

        for (int i = idx; i <= 9; i++) {
            if (i + sum > target) {
                return;
            }

            trace.addLast(i);
            backtrace(k, target, sum + i, trace, res, i + 1);
            trace.removeLast();
        }
    }
}
