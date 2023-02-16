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
 * 107. 二叉树的层序遍历 II
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历;
 * 即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历
 */
public class LevelOrderBottom_107 extends TreeInfoPrint {

    public static void main(String[] args) {
        LevelOrderBottom_107 tool = new LevelOrderBottom_107();
        Integer[] arr = {1, 2, 3, 4, null, 6, 7, 8, 9, null, null, 10};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        List<List<Integer>> res = tool.levelOrderBottom(tool.root);
        for (List<Integer> nodes : res) {
            for (Integer item : nodes) {
                System.out.print(item.intValue() + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

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
            result.addFirst(levelNodes);
        }

        return result;
    }
}
