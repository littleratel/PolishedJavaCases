package cn.intv.linkedlist;

public class InitList {

	public static Node init(int[] arr) {
		int len = arr.length;
		Node rootBak = new Node(arr[0]);
		Node root = rootBak;
		for (int i = 1; i < len; i++) {
			Node tmp = new Node(arr[i]);
			rootBak.next = tmp;
			rootBak = rootBak.next;
		}

		return root;
	}

	public static class Node {
		Node next;
		final int val;

		Node(int x) {
			this.val = x;
			next = null;
		}
	}
}