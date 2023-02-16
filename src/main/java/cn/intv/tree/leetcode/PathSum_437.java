package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.LinkedList;

/**
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class PathSum_437 extends TreeInfoPrint {

    private int res;

    public static void main(String[] args) {
        PathSum_437 tool = new PathSum_437();
        Integer[] arr = {10_0000_0000, 10_0000_0000, null, 294967296, null, 10_0000_0000, null, 10_0000_0000, null, 10_0000_0000};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        int times = tool.pathSum(tool.root, 0);
        System.out.println(times);
    }

    public int pathSum(TreeNode<Integer> root, int targetSum) {
        if (root == null) {
            return 0;
        }

        dfs(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);

        return res;
    }

    private void dfs(TreeNode<Integer> root, int targetSum) {
        if (root == null) {
            return;
        }
        if (root.val == targetSum) {
            res++;
        }
        dfs(root.left, targetSum - root.val);
        dfs(root.right, targetSum - root.val);
    }
}
