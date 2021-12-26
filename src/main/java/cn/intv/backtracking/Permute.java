package cn.intv.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 46 全排列:
 * 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列;
 * <p>
 * <p>
 * For Example:
 * Input：nums = [1,2,3]
 * Output：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Permute {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] visited = new boolean[nums.length];

        dfs(nums, path, visited, res);
        return res;
    }

    // backtrack

    /**
     * 时间复杂度：O(n×n!)
     * 空间复杂度：O(n): path & visited
     */
    private static void dfs(int[] nums, Deque<Integer> path, boolean[] visited, List<List<Integer>> res) {
        int depth = path.size();
        if (nums.length == depth) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            path.addLast(nums[i]);
            visited[i] = true;
            dfs(nums, path, visited, res);
            path.removeLast();
            visited[i] = false;
        }
    }

}
