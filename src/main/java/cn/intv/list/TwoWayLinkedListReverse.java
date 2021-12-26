package cn.intv.list;

import java.util.Stack;

public class TwoWayLinkedListReverse {

	public static void main(String[] args) {
		Node<Integer> node1 = new Node<Integer>(1);
		Node<Integer> node2 = new Node<Integer>(2);
		Node<Integer> node3 = new Node<Integer>(3);
		Node<Integer> node4 = new Node<Integer>(4);
		Node<Integer> node5 = new Node<Integer>(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		node5.prev = node4;
		node4.prev = node3;
		node3.prev = node2;
		node2.prev = node1;

		Node node = node1;
		do {
			System.out.print(node.val + " ");
		} while (null != (node = node.next));

		System.out.println();

		reverse(node1);
		node = node1;
		do {
			System.out.print(node.val + " ");
		} while (null != (node = node.prev));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	// 双向链表反转
	public static void reverse(Node root) {
		Stack<Node> stack = new Stack<Node>();
		//处理将原next链转成prev
		Node tmp1 = root.next;
		while (null != root.next) {
			root.prev = tmp1;
			stack.push(root);
			root = tmp1;
			tmp1 = tmp1.next;
		}
		root.prev = null;
		//
		while (!stack.isEmpty()) {
			tmp1 = stack.pop();
			root.next = tmp1;
			root = tmp1;
		}
		root.next = null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void reverse1(Node root) {
		Stack<Node> stack = new Stack<Node>();
		final int CAPACITY = 1000;
		stack.ensureCapacity(CAPACITY);

		int i = 1;
		Node tmp1, tmp2;
		while (i <= CAPACITY) {
			//
			tmp1 = root.next;
			while (null != root.next && i <= CAPACITY) {
				root.prev = tmp1;
				stack.push(root);
				root = tmp1;
				tmp1 = tmp1.next;
				i++;
			}
			root.prev = null;
			tmp2 = root;
			//
			while (!stack.isEmpty()) {
				tmp1 = stack.pop();
				root.next = tmp1;
				root = tmp1;
			}
			root.next = null;
			
			//
			
		}
	}

	static class Node<T> {
		Node<T> prev = null;
		Node<T> next = null;
		T val;

		public Node(T value) {
			this.val = value;
		}
	}
}
