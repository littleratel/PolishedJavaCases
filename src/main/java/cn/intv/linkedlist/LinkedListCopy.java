package cn.intv.linkedlist;

/**
 * 实现将一个链表复制：
 * 
 * 原链表, node编号用数字代替：
 * next方向: 1->2->3->4->5->6
 * random方向: 1->5,2->6,3->1,4->null,5->2,6->3 
 * 
 * 复制后:
 * next方向: 10->20->30->40->50->60
 * random方向: 10->50,20->60,40->null...
 * 
 * 此外，链表可能有环
 * 
 * */
public class LinkedListCopy {

	public static void main(String[] args) {
		Node list = initNode();
		print(list);
		Node cpyList = listCopy(list);
		print(cpyList);
	}

	private static Node listCopy(Node node) {
		Node tmp1 = node, tmp2 = tmp1;
		// 复制，实现next指向: 1->10->2->20->3->30...
		while (node != null) {
			tmp1 = node.next;
			node.next = node.cpy();
			node = tmp1;
		}
		// 处理复制的那些node的random, 使得
		node = tmp2;
		while (node != null) {
			node.next.random = node.random == null ? null : node.random.next;
			node = node.next.next;
		}
		// 取出复制的那些node组成的链表
		node = tmp2.next;
		while (node.next != null) {
			node.next = node.next.next;
			node = node.next;
		}
		return tmp2.next;
	}

	private static Node initNode() {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;

		node1.random = node5;
		node2.random = node6;
		node3.random = node1;
		node4.random = null;
		node5.random = node2;
		node6.random = node3;

		return node1;
	}

	private static void print(final Node node) {
		Node tmp = node;

		System.out.print("val: ");
		while (tmp != null) {
			System.out.print(tmp.val + " ");
			tmp = tmp.next;
		}
		System.out.println();

		System.out.print("rand-val: ");
		tmp = node;
		while (tmp != null) {
			System.out.print(tmp.random == null ? "null " : tmp.random.val + " ");
			tmp = tmp.next;
		}
		System.out.println();
	}

	private static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}

		public Node cpy() {
			Node n = new Node(this.val * 10);
			n.next = this.next;
			n.random = this.random;
			return n;
		}
	}
}