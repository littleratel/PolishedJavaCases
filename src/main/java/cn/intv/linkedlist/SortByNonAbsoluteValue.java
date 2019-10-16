package cn.intv.linkedlist;

import cn.intv.linkedlist.InitList.Node;

/**
 * 一个本身按数字绝对值大小排序的链表，输出按实际值大小排序的链表， 要求: T:O(n)，S:O(1)
 */
public class SortByNonAbsoluteValue {

	public static void main(String[] args) {
		int[] arr = { -1, -2, -3, 4, 5, 6, 7, -8, -9, -10, -11, -12 };
		Node head = InitList.init(arr);

		Node tmp = doSerch(head);
		do {
			System.out.print(tmp.val + " ");
			tmp = tmp.next;
		} while (tmp != null);
	}

	public static Node doSerch(Node node) {
		Node head = node;
		Node next = null;
		while (node != null) {
			next = node.next;
			if (next != null && next.val < node.val) {
				node.next = next.next;
				next.next = head;
				head = next;
				continue;
			}
			node = node.next;
		}

		return head;
	}
}
