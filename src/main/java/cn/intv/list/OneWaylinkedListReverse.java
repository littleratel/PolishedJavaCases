package cn.intv.list;

import cn.intv.list.utils.ListUtil.Node;

public class OneWaylinkedListReverse {

	public static void main(String[] args) {
		Node B1 = new Node(1);
		B1.next = new Node(2);
		B1.next.next = new Node(3);
		B1.next.next.next = new Node(4);
		B1.next.next.next.next = new Node(5);
		Node tmp = reverse(B1);
		for (int i = 1; i < 6; i++) {
			System.out.println(tmp.val);
			tmp = tmp.next;
		}
	}

	public static Node reverse(Node node) {
		Node tmp1 = null, tmp2 = null;
		tmp1 = node.next;
		node.next = null;
		while (null != tmp1.next) {
			tmp2 = tmp1.next;
			tmp1.next = node;
			node = tmp1;
			tmp1 = tmp2;
		}
		tmp1.next = node;
		node = tmp1;
		return node;
	}
}
