package cn.intv.tree.tmp;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

/**
 * 查找两个节点的最近的一个共同父节点，可以包括节点自身.
 */
public class FindNearestPublicAncestor_236 extends TreeInfoPrint {

    public static void main(String[] args) {
        FindNearestPublicAncestor_236 tool = new FindNearestPublicAncestor_236();
        Integer[] arr = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9};

        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        TreeNode<Integer> p = new TreeNode<>(6);
        TreeNode<Integer> q = new TreeNode<>(9);

        TreeNode result = tool.lowestCommonAncestor(tool.root, p, q);
        System.out.println(result.value);
    }

    // 递归查找
    // 后序遍历是从下往上，就好比从p和q出发往上走，第一次相交的节点，是最近公共祖先
    private TreeNode lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        if (root == null) {
            return null;
        } else if (root.value == p.value || root.value == q.value) {
            return root;
        }

        TreeNode<Integer> left = lowestCommonAncestor(root.left, p, q);
        TreeNode<Integer> right = lowestCommonAncestor(root.right, p, q);

        // 如果左、右子树都能找到，那么当前节点就是最近的公共祖先节点
        if (left != null && right != null) {
            return root;
        }

        //
        return left != null ? left : right;
    }
}
