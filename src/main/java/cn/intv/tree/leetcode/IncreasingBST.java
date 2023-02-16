package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 */
public class IncreasingBST extends TreeInfoPrint {
    public static void main(String[] args) {
        IncreasingBST tool = new IncreasingBST();
        Integer[] arr = {2, 1, 4, null, null, 3};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        TreeNode res = tool.increasingBST(tool.root);
        System.out.println(res);
    }

    private LinkedList<TreeNode> lst = new LinkedList<>();

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;

        dfs(root);

        TreeNode head = lst.poll(), pre = head, node;
        pre.left = null;
        while (!lst.isEmpty()) {
            node = lst.poll();
            pre.right = node;
            pre.left = null;
            pre = node;
        }
        pre.left = null;


        return head;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        lst.add(root);
        dfs(root.right);
    }
}
