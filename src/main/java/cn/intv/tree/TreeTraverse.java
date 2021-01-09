package cn.intv.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import cn.intv.tree.InitTree.TreeNode;

public class TreeTraverse<E extends Comparable<? super E>> {

	public static void main(String[] args) {
		Integer[] arrFalse = { 5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9 };
		TreeNode<Integer> root = InitTree.initTree(arrFalse, 0);
		TreeTraverse<Integer> tree = new TreeTraverse<>();

		System.out.print("递归前序遍历 ：");
		tree.preOrder(root);
		System.out.print("\n非递归前序遍历：");
		tree.preOrder1(root);
		System.out.print("\n递归中序遍历 ：");
		tree.midOrder(root);
		System.out.print("\n非递归中序遍历 ：");
		tree.midOrder1(root);
		System.out.print("\n递归后序遍历 ：");
		tree.posOrder(root);
		System.out.print("\n非递归后序遍历 ：");
		tree.posOrder1(root);
		System.out.print("\n递归层序遍历：");
		tree.levelOrder(root);
		System.out.print("\n非递归层序遍历 ：");
		tree.levelOrder1(root);
	}

	/**
	 * 前序遍历 递归
	 */
	public void preOrder(TreeNode<E> Node) {
		if (Node != null) {
			System.out.print(Node.value + " ");
			preOrder(Node.left);
			preOrder(Node.right);
		}
	}

	/**
	 * 中序遍历 递归
	 */
	public void midOrder(TreeNode<E> Node) {
		if (Node != null) {
			midOrder(Node.left);
			System.out.print(Node.value + " ");
			midOrder(Node.right);
		}
	}

	/**
	 * 后序遍历 递归
	 */
	public void posOrder(TreeNode<E> Node) {
		if (Node != null) {
			posOrder(Node.left);
			posOrder(Node.right);
			System.out.print(Node.value + " ");
		}
	}

	/*
	 * 层序遍历 递归
	 */
	public void levelOrder(TreeNode<E> Node) {
		if (Node == null) {
			return;
		}
		int depth = depth(Node);
		for (int i = 1; i <= depth; i++) {
			levelOrder(Node, i);
		}
	}

	private void levelOrder(TreeNode<E> Node, int level) {
		if (Node == null || level < 1) {
			return;
		}
		if (level == 1) {
			System.out.print(Node.value + " ");
			return;
		}
		// 左子树
		levelOrder(Node.left, level - 1);
		// 右子树
		levelOrder(Node.right, level - 1);
	}

	public int depth(TreeNode<E> Node) {
		if (Node == null) {
			return 0;
		}

		int l = depth(Node.left);
		int r = depth(Node.right);

		return (l > r) ? l + 1 : r + 1;
	}

	// 前序遍历 非递归
	public void preOrder1(TreeNode<E> Node) {
		Stack<TreeNode<E>> stack = new Stack<>();
		while (Node != null || !stack.empty()) {
			while (Node != null) {
				System.out.print(Node.value + " ");
				stack.push(Node);
				Node = Node.left;
			}
			if (!stack.empty()) {
				Node = stack.pop();
				Node = Node.right;
			}
		}
	}

	// 中序遍历 非递归
	public void midOrder1(TreeNode<E> Node) {
		Stack<TreeNode<E>> stack = new Stack<>();
		while (Node != null || !stack.empty()) {
			while (Node != null) {
				stack.push(Node);
				Node = Node.left;
			}
			if (!stack.empty()) {
				Node = stack.pop();
				System.out.print(Node.value + " ");
				Node = Node.right;
			}
		}
	}

	// 后序遍历 非递归
	public void posOrder1(TreeNode<E> Node) {
		Stack<TreeNode<E>> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		int i = 1;
		while (Node != null || !stack1.empty()) {
			while (Node != null) {
				stack1.push(Node);
				stack2.push(0);
				Node = Node.left;
			}

			while (!stack1.empty() && stack2.peek() == i) {
				stack2.pop();
				System.out.print(stack1.pop().value + " ");
			}

			if (!stack1.empty()) {
				stack2.pop();
				stack2.push(1);
				Node = stack1.peek();
				Node = Node.right;
			}
		}
	}

	/*
	 * 层序遍历 非递归
	 */
	public void levelOrder1(TreeNode<E> Node) {
		if (Node == null) {
			return;
		}

		TreeNode<E> binaryNode;
		Queue<TreeNode<E>> queue = new LinkedList<>();
		queue.add(Node);

		while (queue.size() != 0) {
			binaryNode = queue.poll();

			System.out.print(binaryNode.value + " ");

			if (binaryNode.left != null) {
				queue.offer(binaryNode.left);
			}
			if (binaryNode.right != null) {
				queue.offer(binaryNode.right);
			}
		}
	}
}
