package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

public class ConvertBST_538 extends TreeInfoPrint {

    int sum = 0;

    public static void main(String[] args) {
        ConvertBST_538 tool = new ConvertBST_538();
        Integer[] arr = {4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        tool.convertBST(tool.root);
        BinaryTrees.println(tool);
    }

    private TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode<Integer> node) {
        if (node == null) {
            return;
        }

        traverse(node.right);
        sum += node.val;
        // 将 BST 转化成累加树
        node.val = sum;
        traverse(node.left);
    }
}
