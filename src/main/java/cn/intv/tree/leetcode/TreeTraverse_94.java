package cn.intv.tree.leetcode;

public class TreeTraverse_94 {

    public static void main(String[] args) {
        TreeTraverse_94 tool = new TreeTraverse_94();
        int res = tool.numTrees(3);
        System.out.println(res);
    }

    int[][] memo;

    public int numTrees(int n) {
        memo = new int[n + 1][n + 1];
        return buildTreeNum(1, n);
    }

    private int buildTreeNum(int lo, int hi) {
        //
        if (lo > hi) {
            return 1;
        }

        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        for (int i = lo; i <= hi; i++) {
            int left = buildTreeNum(lo, i - 1);
            int right = buildTreeNum(i + 1, hi);
            res += left * right;
        }

        memo[lo][hi] = res;

        return res;
    }
}
