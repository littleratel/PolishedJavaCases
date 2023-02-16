package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）
 */
public class TreeTraverse_102 extends TreeInfoPrint {

    public static void main(String[] args) {
        TreeTraverse_102 tool = new TreeTraverse_102();
        Integer[] arr = {1, 2, 3, 4, null, 6, null, 8};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        List<List<Integer>> res = tool.levelOrder(tool.root);
        for (List<Integer> nodes : res) {
            for (Integer item : nodes) {
                System.out.print(item.intValue() + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<List<Integer>> result = new LinkedList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            int size = stack.size();
            List<Integer> levelNodes = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode curNode = stack.poll();
                levelNodes.add((Integer) curNode.val);

                if (curNode.left != null) {
                    stack.add(curNode.left);
                }
                if (curNode.right != null) {
                    stack.add(curNode.right);
                }
            }
            result.add(levelNodes);
        }

        return result;
    }
}
