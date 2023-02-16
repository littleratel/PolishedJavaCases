package cn.intv.tree;

import util.tree.TreeUtils;

import java.util.HashMap;
import java.util.Map;

public class BinaryTree {
    public static void main(String[] args) {
        Integer[] arrTrue = {5, 1, 7, null, 3, 6, 9};
        Integer[] arrFalse = {10, 6, 15, 7, 8, 13, 17, 4, null, null, null};
        TreeNode<Integer> treeTrue = TreeUtils.initTree(arrTrue, 0);
        TreeNode<Integer> treeFalse = TreeUtils.initTree(arrFalse, 0);
        System.out.println(isSameTree(treeTrue, treeFalse));

        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode<Integer> root = sortedArrayToBST(sortedArr);
        System.out.println(root);
    }

    /**
     * 相同的树
     */
    public static boolean isSameTree(TreeNode<Integer> p, TreeNode<Integer> q) {
        if (p == null && q == null)
            return true;

        if (p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 对称二叉树
     */
    public boolean isSymmetric(TreeNode<Integer> root) {
        if (root == null)
            return true;
        return check(root.left, root.right);
    }

    public boolean check(TreeNode<Integer> node1, TreeNode<Integer> node2) {
        if (node1 == null && node2 == null)
            return true;

        if (node1.val != node2.val)
            return false;

        return check(node1.left, node2.right) && check(node1.right, node2.left);
    }

    /**
     * 从前序与中序遍历序列构造二叉树
     */
    private int[] arr;
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode<Integer> buildTreeUsePreorderAndInorder(int[] preorder, int[] inorder) {
        arr = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return doBuild(0, 0, preorder.length - 1);
    }

    private TreeNode<Integer> doBuild(int rootIndex, int left, int right) {
        if (left > right) return null;
        TreeNode<Integer> root = new TreeNode<Integer>(arr[rootIndex]);
        int index = map.get(arr[rootIndex]);
        root.left = doBuild(rootIndex + 1, left, index - 1);
        root.right = doBuild(rootIndex + index - left + 1, index + 1, right);

        return root;
    }

    /**
     * 从后序与中序遍历序列构造二叉树
     */
    public TreeNode<Integer> buildTreeUseInorderAndPostorder(int[] inorder, int[] postorder) {
        arr = postorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return doBuild2(postorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode<Integer> doBuild2(int rootIndex, int left, int right) {
        if (left > right) return null;
        TreeNode<Integer> root = new TreeNode<Integer>(arr[rootIndex]);
        int index = map.get(arr[rootIndex]);
        root.left = doBuild2(rootIndex - (right - index) - 1, left, index - 1);
        root.right = doBuild2(rootIndex - 1, index + 1, right);

        return root;
    }

    /**
     * 将有序数组转化为二叉搜索树BST
     */
    public static TreeNode<Integer> sortedArrayToBST(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        return build(nums, left, right);
    }

    private static TreeNode<Integer> build(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) >> 1;
        TreeNode<Integer> root = new TreeNode<>(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }

}
