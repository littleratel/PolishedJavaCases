package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

/**
 * 删除BST的节点，使删除仍是一颗BST
 */
public class DeleteNode_450 extends TreeInfoPrint {

    public static void main(String[] args) {
        DeleteNode_450 tool = new DeleteNode_450();
        Integer[] arr = {5, 3, 6, 2, 4, null, 7};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        tool.root = tool.deleteNode(tool.root, 3);
        BinaryTrees.println(tool);
    }

    public TreeNode deleteNode(TreeNode<Integer> root, int key) {
        if (root == null) {
            return root;
        }

        if (root.val == key) {
            root = doDelete(root);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    private TreeNode doDelete(TreeNode<Integer> root) {
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }

        TreeNode<Integer> minNode = getMin(root.right);
        root.right = deleteNode(root.right, minNode.val);

        root.val = minNode.val;
        return root;
    }

    TreeNode getMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

}
