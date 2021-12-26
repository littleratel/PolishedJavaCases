package cn.intv.list;

import cn.intv.list.utils.ListUtil;
import cn.intv.list.utils.ListUtil.Node;

public class FindKthNodeFromTail {

	public static void main(String[] args) {
		int[] arr = { 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		Node head = ListUtil.init(arr);
		Node re = Find1(head, 12);
		if (re == null) {
			System.out.println("null");
		} else {
			System.out.println(re.val);
		}
	}

	/**
	 * 创建2两个临时node，让first node先走k-1步(让两个节点相距k-1)，再让两个节点同时走往后走。
	 */
	public static Node Find1(Node head, int k) {
		if (head == null || k < 1)
			return null;
		Node first = head;
		Node second = head;
		for (int num = k; num > 0; num--) {
			if (first == null) {
				System.out.println("k exceeds the length of the list.");
				return null;
			}
			first = first.next;
		}
		while (first != null) {
			first = first.next;
			second = second.next;
		}

		return second;
	}

	public static Node Find2(Node head, int k) {
		if (head == null || k < 1)
			return null;
		Node cur = head;
		Node backKth = null;
		int number = 0;
		while (cur != null) {
			number++;
			if (number == k)
				backKth = head;
			if (number > k)
				backKth = backKth.next;
			cur = cur.next;
		}
		return backKth;
	}

}
