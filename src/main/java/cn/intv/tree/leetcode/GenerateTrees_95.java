package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;

import java.util.LinkedList;
import java.util.List;

/***
 * 不同的二叉搜索树
 * 给你输入一个正整数n，请你计算，存储{1,2,3...,n}这些值共有有多少种不同的 BST 结构
 */
public class GenerateTrees_95 extends TreeInfoPrint {
    public static void main(String[] args) {
        GenerateTrees_95 tool = new GenerateTrees_95();

        List<TreeNode> treeNodes = tool.generateTrees(3);
        for (TreeNode node : treeNodes) {
            tool.root = node;
            BinaryTrees.println(tool);
        }
    }

    private List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        // 构造闭区间 [1, n] 组成的 BST
        return build(1, n);
    }

    // 构造闭区间 [lo, hi] 组成的 BST
    private List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new LinkedList<>();
        if (lo > hi) {
            res.add(null);
            return res;
        }

        // 穷举 Root 节点的所有可能
        for (int i = lo; i <= hi; i++) {
            // 递归构造出左右子树的所有合法 BST
            List<TreeNode> leftTree = build(lo, i - 1);
            List<TreeNode> rightTree = build(i + 1, hi);

            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    // i 作为根结点 root 的值
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }
}
