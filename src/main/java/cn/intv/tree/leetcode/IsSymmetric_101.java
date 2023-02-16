package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否 轴对称
 */
public class IsSymmetric_101 extends TreeInfoPrint {
    public static void main(String[] args) {
        IsSymmetric_101 tool = new IsSymmetric_101();
        Integer[] arr = {1, 2, 2, null, 3, null, 3};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        System.out.println(tool.isSymmetric(tool.root));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return doCheck(root.left, root.right);
    }

    private boolean doCheck(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null) {
            return n1 == n2;
        }

        if (n1.val != n2.val) {
            return false;
        }

        return doCheck(n1.left, n2.right) && doCheck(n1.right, n2.left);
    }
}
