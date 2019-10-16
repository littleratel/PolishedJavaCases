package cn.intv.tree;

import cn.intv.tree.InitTree.TreeNode;

public class FindNearestPublicAncestor {

	public static void main(String[] args) {
		Integer[] arrFalse = { 5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9 };
		TreeNode<Integer> root = InitTree.initTree(arrFalse, 0);
		TreeNode<Integer> node1 = new TreeNode<Integer>(7);
		TreeNode<Integer> node2 = new TreeNode<Integer>(1);
		System.out.println(recursiveFind(root, node1, node2).value);
	}

	// 递归查找
	private static TreeNode<Integer> recursiveFind(TreeNode<Integer> root, TreeNode<Integer> node1,
			TreeNode<Integer> node2) {
		if (root == null || node1 == null || node2 == null)
			return null;

		if (root.value == node1.value || root.value == node2.value)
			return root;

		TreeNode<Integer> left = recursiveFind(root.left, node1, node2);
		TreeNode<Integer> right = recursiveFind(root.right, node1, node2);
		// 如果左右子树都能找到，那么当前节点就是最近的公共祖先节点
		if (left != null && right != null)
			return root;
		// 如果左子树上有，就返回左子树的查找结果
		else if (left != null)
			return left;
		else
			return right;
	}

	// 非递归查找
	private static TreeNode<Integer> NonRecursiveFind(TreeNode<Integer> root, TreeNode<Integer> node1,
			TreeNode<Integer> node2) {
		if (root == null || node1 == null || node2 == null)
			return null;

		return null;
	}
}
