package cn.intv.tree;

import org.junit.Test;
import util.tree.TreeUtils;

/**
 * 111. 二叉树的深度
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * Notes:
 * 左右孩子都为null的 Node 才是叶子结点，
 */
public class TreeDepth {

    @Test
    public void main() {
        Integer[] arrFalse = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9, 0};
        TreeNode<Integer> root = TreeUtils.initTree(arrFalse, 0);
        TreeDepth tool = new TreeDepth();
        System.out.println(tool.maxDepth(root));
//        System.out.println(tool.minDepth(root));
    }

    // 二叉树的最大深度
    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    // 二叉树的最小深度
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        if (root.left == null && root.right != null) {
            return 1 + minDepth(root.right);
        }

        if (root.right == null && root.left != null) {
            return 1 + minDepth(root.left);
        }

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}

