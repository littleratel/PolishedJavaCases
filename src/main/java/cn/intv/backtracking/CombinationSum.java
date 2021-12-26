package cn.intv.backtracking;

import java.util.*;

/**
 * LeetCode 第 39 题:
 * 给定一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的所有不同组合，并以列表形式返回。
 *
 * 且:
 * 1. candidates 中的同一个数字可以无限制重复使用。
 * 2. 相同数字列表的不同排列视为一个结果
 */
public class CombinationSum {
    public static void main(String[] args) {
        CombinationSum tool = new CombinationSum();
//        System.out.println(tool.combinationSum(new int[]{2, 3, 6, 7}, 7));
//        System.out.println(tool.combinationSum(new int[]{2, 3, 3}, 8));
//        System.out.println(tool.combinationSumOpt(new int[]{2, 3, 6, 7}, 7));
//        System.out.println(tool.combinationSumOpt(new int[]{2, 3, 3}, 8));

        int[] candidates = {33, 22, 11, 6, 7, 8, 5, 20, 23, 24, 12, 13, 4, 9, 15, 16, 17, 18, 34, 55, 50, 51, 41, 45, 44};
        int target = 100;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            tool.combinationSum(candidates, target);
        }
        System.out.println("Used: " + (System.currentTimeMillis() - start));

        System.out.println("<========== Opt ==========>");
        start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            tool.combinationSumOpt(candidates, target);
        }
        System.out.println("Used: " + (System.currentTimeMillis() - start));
    }

    /**
     * 方式一：
     *
     * */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, target, path, res);
        return res;
    }

    /**
     * 时间复杂度与空间复杂度不确定, 与 candidate 数组的值有关：
     * 1. 如果 candidate 数组的值都很大，target 的值很小，那么树上的结点就比较少；
     * 2. 如果 candidate 数组的值都很小，target 的值很大，那么树上的结点就比较多。
     */
    private void dfs(int[] candidates, int idx, int target, Deque<Integer> path, List<List<Integer>> res) {
        // 剪枝
        if (target < 0) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx, len = candidates.length; i < len; i++) {
            path.addLast(candidates[i]);

            // 新的分支，它的起始位置不再考虑前一个分支的起始值
            dfs(candidates, i, target - candidates[i], path, res);

            path.removeLast();
        }
    }


    /**
     * 方式二：
     *
     * 优化剪枝
     * */
    public List<List<Integer>> combinationSumOpt(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates);
        dfsOpt(candidates, 0, target, path, res);
        return res;
    }

    // 排序是剪枝的前提
    private void dfsOpt(int[] candidates, int idx, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx, len = candidates.length; i < len; i++) {
            // 剪枝, 减掉 i 之后所有的元素
            if (target - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            dfsOpt(candidates, i, target - candidates[i], path, res);
            path.removeLast();
        }
    }

}
