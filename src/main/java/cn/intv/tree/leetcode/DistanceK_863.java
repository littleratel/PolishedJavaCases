package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 */
public class DistanceK_863 extends TreeInfoPrint {
    private Map<TreeNode, TreeNode> par = new LinkedHashMap<>();
    private List<Integer> res = new LinkedList<>();
    private Set<TreeNode> visited = new HashSet<>();
    private TreeNode target;

    public static void main(String[] args) {
        DistanceK_863 tool = new DistanceK_863();
        Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        tool.findTarget(tool.root, 5);
        List<Integer> res = tool.distanceK(tool.root, tool.target, 2);
        System.out.println(res);
    }

    private void findTarget(TreeNode<Integer> root, int targetVal) {
        if (root == null) {
            return;
        }

        if (root.val == targetVal) {
            target = root;
        }

        findTarget(root.left, targetVal);
        findTarget(root.right, targetVal);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        addPra(root);
        dfs(target, k);
        return res;
    }

    private void dfs(TreeNode<Integer> target, int k) {
        if (target == null || k < 0 || !visited.add(target)) return;

        if (k == 0) {
            res.add(target.val);
            return;
        }

        dfs(target.left, k - 1);
        dfs(target.right, k - 1);
        dfs(par.get(target), k - 1);
    }

    private void addPra(TreeNode root) {
        if (root == null) return;

        if (root.left != null) {
            par.put(root.left, root);
            addPra(root.left);
        }

        if (root.right != null) {
            par.put(root.right, root);
            addPra(root.right);
        }
    }
}
