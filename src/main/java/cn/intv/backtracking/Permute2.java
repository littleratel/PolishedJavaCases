package cn.intv.backtracking;

import java.util.*;

/**
 * LeetCode 47 全排列 II:
 * 给定一个 含重复数字 的数组 nums ，返回其所有可能的全排列;
 * <p>
 * Input：nums = [1,1,2]
 * Output：[[1,1,2],[1,2,1],[2,1,1]]
 */
public class Permute2 {

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1, 1, 2}));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>(nums.length);
        boolean[] used = new boolean[nums.length];

        backtracking(nums, path, used, res);

        return res;
    }

    private static void backtracking(int[] nums, Deque<Integer> path, boolean[] visited, List<List<Integer>> res) {
        if (nums.length == path.size()) { // depth: path.size()
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            // !visited[i - 1] ?
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            visited[i] = true;
            backtracking(nums, path, visited, res);
            path.removeLast();
            visited[i] = false;
        }
    }
}
