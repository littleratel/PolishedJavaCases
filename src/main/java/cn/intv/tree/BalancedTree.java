package cn.intv.tree;

import util.tree.TreeUtils;

/**
 * 平衡二叉树
 * <p>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1
 */
public class BalancedTree {

    public static void main(String[] args) {
        Integer[] arr = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9};
        TreeNode<Integer> root = TreeUtils.initTree(arr, 0);
        BalancedTree tool = new BalancedTree();
        System.out.println(tool.isBalanced(root));
    }

    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;

        int lh = height(root.left);
        int rh = height(root.right);

        if (lh >= 0 && rh >= 0 && Math.abs(lh - rh) <= 1) {
            return Math.max(lh, rh) + 1;
        } else {
            return -1;
        }
    }
}
