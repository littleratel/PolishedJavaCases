package cn.intv.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 输入：n = 4, k = 2
 * 输出：[[2,4], [3,4], [2,3], [1,2], [1,3], [1,4]]
 */
public class Combine {
    public static void main(String[] args) {
        Combine tool = new Combine();
        System.out.println(tool.combine(4, 2));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, k, 1, new ArrayDeque<>(), res);
        return res;
    }

    //
    private void backtrack(int n, int k, int idx, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // i <= idx + k - path.size() - 1
        for (int i = idx; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            backtrack(n, k, i + 1, path, res);
            path.removeLast();
        }
    }
}
