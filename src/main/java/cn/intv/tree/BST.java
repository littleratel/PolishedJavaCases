package cn.intv.tree;

import java.util.Stack;

import cn.intv.tree.InitTree.TreeNode;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 */
public class BST {

	public static void main(String[] args) {
		Integer[] arrTrue = { 5, 1, 7, null, 3, 6, 9 };
		Integer[] arrFalse = { 10, 7, 15, 5, 8, 13, 17, 4, 9, null, null };
		TreeNode<Integer> tree = InitTree.initTree(arrFalse, 0);
		System.out.println(isBST(tree));
		System.out.println(isValidBST(tree));
	}
	
	public static boolean isValidBST(TreeNode<Integer> root) {
		Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>>();
		TreeNode<Integer> pre = null;
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (pre != null && pre.value >= root.value)
				return false;
			pre = root;
			root = root.right;
		}
		return true;
	}

	/**
	 * 递归实现
	 * */
	public static boolean isBST(TreeNode<Integer> root) {
		if (root == null)
			return true;
		return (IsSubtreeLessThan(root.left, root.value) && IsSubtreeMoreThan(root.right, root.value)
				&& isBST(root.left) && isBST(root.right));
	}
	public static boolean IsSubtreeLessThan(TreeNode<Integer> t, int val) {
		if (t == null)
			return true;
		return (t.value < val && IsSubtreeLessThan(t.left, val) && IsSubtreeLessThan(t.right, val));
	}
	public static boolean IsSubtreeMoreThan(TreeNode<Integer> t, int val) {
		if (t == null)
			return true;
		return (t.value > val && IsSubtreeMoreThan(t.left, val) && IsSubtreeMoreThan(t.right, val));
	}
}
