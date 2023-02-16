package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.LinkedList;

public class IsEvenOddTree_1609 extends TreeInfoPrint {

    public static void main(String[] args) {
        IsEvenOddTree_1609 tool = new IsEvenOddTree_1609();
        Integer[] arr = {1, 10, 4, 3, null, 7, 9, 12, 8, 6, null, null, 2};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        boolean rootStr = tool.isEvenOddTree(tool.root);
        System.out.println(rootStr);
    }

    private LinkedList<TreeNode<Integer>> q = new LinkedList<>();

    public boolean isEvenOddTree(TreeNode root) {
        q.add(root);

        int lev = 0, size, pre, cur;
        TreeNode<Integer> node;
        boolean even;
        while (!q.isEmpty()) {
            size = q.size();
            node = q.poll();
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);

            pre = node.val;
            even = (lev % 2 == 0);
            if (even && pre % 2 == 0 || !even && pre % 2 == 1) return false;

            for (int i = 1; i < size; i++) {
                node = q.poll();
                cur = node.val;
                if (even) {
                    if (cur % 2 == 0 || cur <= pre) {
                        return false;
                    }
                } else {
                    if (cur % 2 != 0 || cur >= pre) {
                        return false;
                    }
                }
                pre = cur;

                // 子节点加入队列
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }

            lev++;
        }

        return true;
    }
}
