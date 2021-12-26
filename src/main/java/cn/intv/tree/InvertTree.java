package cn.intv.tree;

import cn.intv.tree.InitTree.TreeNode;

public class InvertTree<E extends Comparable<? super E>> {

    public static void main(String[] args) {
        Integer[] arrFalse = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9};
        TreeNode<Integer> root = InitTree.initTree(arrFalse, 0);
        InvertTree<Integer> tool = new InvertTree<>();
        tool.doInvert(root);
    }

    // 前后序遍历都可以
    public TreeNode doInvert(TreeNode root) {
        if (root == null) {
            return null;
        }

        // swapChildren(root); // 前序遍历
        doInvert(root.left);
        doInvert(root.right);
        swapChildren(root); // 后序遍历
        return root;
    }

    private void swapChildren(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }
}
