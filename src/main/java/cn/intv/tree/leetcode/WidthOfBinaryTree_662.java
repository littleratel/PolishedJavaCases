package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import javafx.util.Pair;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.LinkedList;

public class WidthOfBinaryTree_662 extends TreeInfoPrint {

    public static void main(String[] args) {
        WidthOfBinaryTree_662 tool = new WidthOfBinaryTree_662();
        Integer[] arr = {1,3,2,5};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        int res = tool.widthOfBinaryTree(tool.root);
        System.out.println(res);
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        LinkedList<Pair> lst = new LinkedList<>();
        lst.add(new Pair(root, 1));
        int maxWidth = 0, start, end;
        while (!lst.isEmpty()) {
            int size = lst.size() - 1;
            start = 0;
            end = 0;

            for (int i = 0; i <= size; i++) {
                Pair<TreeNode, Integer> nodePair = lst.poll();

                if (i == 0) {
                    start = nodePair.getValue();
                }

                else if (i == size) {
                    end = nodePair.getValue();
                }

                TreeNode node = nodePair.getKey();
                if (node.left != null) {
                    lst.add(new Pair(node.left, 2 * nodePair.getValue()));
                }
                if (node.right != null) {
                    lst.add(new Pair(node.right, 2 * nodePair.getValue() + 1));
                }
            }

            maxWidth = Math.max(end - start + 1, maxWidth);
        }

        return maxWidth;
    }
}
