package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import javafx.util.Pair;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.LinkedList;

public class LongestUnivaluePath_687 extends TreeInfoPrint {

    public static void main(String[] args) {
        LongestUnivaluePath_687 tool = new LongestUnivaluePath_687();
        Integer[] arr = {5, 4, 5, 4, 4, 5, 3, 4, 4, null, null, null, 4, null, null, 4, null, null, 4, null, 4, 4, null, null, 4, 4};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        int res = tool.longestUnivaluePath(tool.root);
        System.out.println(res);
    }

    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        int lb = 0, rb = 0;
        if (root.left != null && root.left.val == root.val) {
            lb = left;
        }
        if (root.right != null && root.right.val == root.val) {
            rb = right;
        }

        max = Math.max(max, lb + rb);

        return Math.max(lb, rb) + 1;
    }
}
