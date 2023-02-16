package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 通过前序和中序遍历结果构造二叉树
 */
public class Connect_117 extends TreeInfoPrint {

    public static void main(String[] args) {
        Connect_117 tool = new Connect_117();
        Integer[] arr = {1, 2, 3, 4, 5, null, 7};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        tool.root = tool.connect(tool.root);
        BinaryTrees.println(tool);
    }

    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return root;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            int size = stack.size();
            while (size > 0) {
                TreeNode node = stack.poll();
                node.next = size == 1 ? null : stack.peek();
                size--;

                if (node.left != null) {
                    stack.add(node.left);
                }
                if (node.right != null) {
                    stack.add(node.right);
                }
            }
        }

        return root;
    }

}
