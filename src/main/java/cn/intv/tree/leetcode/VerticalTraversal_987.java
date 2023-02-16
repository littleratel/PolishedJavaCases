package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class VerticalTraversal_987 extends TreeInfoPrint {
    private PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> { // col, row, val
        if (a[0] != b[0]) return a[0] - b[0];
        if (a[1] != b[1]) return a[1] - b[1];
        return a[2] - b[2];
    });

    public static void main(String[] args) {
        VerticalTraversal_987 tool = new VerticalTraversal_987();
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        List<List<Integer>> res = tool.verticalTraversal(tool.root);
        System.out.println(res);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);
        List<List<Integer>> res = new LinkedList<>();
        while (!q.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int[] poll = q.peek();
            while (!q.isEmpty() && q.peek()[0] == poll[0]) tmp.add(q.poll()[2]);
            res.add(tmp);
        }

        return res;
    }

    private void dfs(TreeNode<Integer> root, int col, int row) {
        if (root == null) return;

        int[] e = new int[]{col, row, root.val};
        q.add(e);

        dfs(root.left, col - 1, row + 1);
        dfs(root.right, col + 1, row + 1);
    }
}
