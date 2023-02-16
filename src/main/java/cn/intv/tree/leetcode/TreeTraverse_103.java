package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * 103. 二叉树的层序遍历
 * Z 字形打印二叉树
 */
public class TreeTraverse_103 extends TreeInfoPrint {

    public static void main(String[] args) {
        TreeTraverse_103 tool = new TreeTraverse_103();
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        List<List<Integer>> res = tool.zigzagLevelOrder(tool.root);
        for (List<Integer> nodes : res) {
            for (Integer item : nodes) {
                System.out.print(item.intValue() + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        def(root, res, 0);
        return res;
    }

    private void def(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }

        if (level == res.size()) {
            res.add(new LinkedList<>());
        }

        LinkedList<Integer> curLevelNodes = (LinkedList<Integer>) res.get(level);

        if (level % 2 == 0) {
            curLevelNodes.addLast((Integer) root.val);
        } else {
            curLevelNodes.addFirst((Integer) root.val);
        }

        def(root.left, res, level + 1);
        def(root.right, res, level + 1);
    }
}
