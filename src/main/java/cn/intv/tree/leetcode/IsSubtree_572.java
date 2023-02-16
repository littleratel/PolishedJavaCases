package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

/**
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class IsSubtree_572 extends TreeInfoPrint {

    private int res;

    public static void main(String[] args) {
        IsSubtree_572 tool = new IsSubtree_572();
        Integer[] arr = {3, 4, 5, 1, 2, null, null, null, null, 0}; //
        TreeNode root = TreeUtils.initTree(arr, 0);
        tool.root = root;
        BinaryTrees.println(tool);

        Integer[] arr2 = {4,1,2}; //
        TreeNode subRoot = TreeUtils.initTree(arr2, 0);
        tool.root = subRoot;
        BinaryTrees.println(tool);

        boolean res = tool.isSubtree(root, subRoot);
        System.out.println(res);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        StringBuilder rootSb = new StringBuilder(",");
        serialize(root, rootSb);
        System.out.println(rootSb);

        StringBuilder subRootSb = new StringBuilder(",");
        serialize(subRoot, subRootSb);
        System.out.println(subRootSb);

        return rootSb.toString().contains(subRootSb.toString());
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
            return;
        }

        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }
}
