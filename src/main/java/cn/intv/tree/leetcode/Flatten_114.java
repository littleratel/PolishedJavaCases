package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

/**
 * 将二叉树展开为链表
 */
public class Flatten_114 extends TreeInfoPrint {
    public static void main(String[] args) {
        Flatten_114 tool = new Flatten_114();
        Integer[] arr = {1, 2, 5, 3, 4, null, 6};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        tool.flatten(tool.root);
        BinaryTrees.println(tool);
    }

    /**
     * 1、将root的左子树和右子树拉平。
     * 2、将root的右子树接到左子树下方，然后将整个左子树作为右子树。
     */
    private void flatten(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }

        flatten(root.right);
        flatten(root.left);

        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;

        // 找到现在的右子树的最后一个节点
        TreeNode tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }

        // 将原右子树挂在目前的右子树的最末端
        tmp.right = right;
    }
}
