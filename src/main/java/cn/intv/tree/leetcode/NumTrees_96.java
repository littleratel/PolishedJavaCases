package cn.intv.tree.leetcode;

/***
 * 不同的二叉搜索树
 * 给你输入一个正整数n，请你计算，存储{1,2,3...,n}这些值共有有多少种不同的 BST 结构
 */
public class NumTrees_96 {
    public static void main(String[] args) {
        NumTrees_96 tool = new NumTrees_96();
        System.out.println(tool.numTrees(3));
        System.out.println(tool.numTrees2(3));
    }

    /**
     * 思路 1:
     */
    int[] memo1;

    private int numTrees(int n) {
        memo1 = new int[n + 1];
        return count(n);
    }

    /**
     * 思路：
     * 1为根节点1..n的二叉搜索树数量 = 1 * n - 1个元素的数量
     * 2为根节点1..n的二叉搜索树数量 = 左边1个元素的数量 * 右边n - 2个元素数量
     * 3为根节点1..n数量 = 左边2元素数量 * 右边 n - 3数量
     * 4为根 = 左3数量 * 右n-4数量
     */
    private int count(int n) {
        if (n < 2) return 1;
        if (memo1[n] != 0) {
            return memo1[n];
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            // i 的值作为根节点 root
            int left = count(i - 1);
            int right = count(n - i);
            // 左右子树的组合数乘积是 BST 的总数
            res += left * right;
        }

        return res;
    }

    /**
     * 思路 2:
     * 处理重叠子问题
     * 消除重叠子问题的方法，无非就是加一个备忘录
     */
    private int[][] memo; // 备忘录

    private int numTrees2(int n) {
        memo = new int[n + 1][n + 1];
        return count2(1, n);
    }

    private int count2(int lo, int hi) {
        // 显然当lo > hi闭区间[lo, hi]肯定是个空区间，也就对应着空节点 null，
        // 虽然是空节点，但是也是一种情况，所以要返回 1 而不能返回 0。
        if (lo > hi) {// 当前的root节点的左或右子树为NULL
            return 1;
        }

        // 查备忘录
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        for (int i = lo; i <= hi; i++) {
            int left = count2(lo, i - 1);
            int right = count2(i + 1, hi);
            res += left * right;
        }

        // 将结果存入备忘录
        memo[lo][hi] = res;

        return res;
    }
}
