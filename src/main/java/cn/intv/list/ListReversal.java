package cn.intv.list;

/**
 * 链表反转
 * 
 * */
public class ListReversal {

	public static void main(String[] args) {
		
		Node node1= new Node(1);
		Node node2= new Node(2);
		Node node3= new Node(3);
		Node node4= new Node(4);
		Node node5= new Node(5);
		
		node4.next = node5;
		node3.next = node4;
		node2.next = node3;
		node1.next = node2;
		
		oneWayListRreversal(node1);
	}

	private static void oneWayListRreversal(Node node){
		
		
		
	}

	static class Node {
		private int value;// 数据域
		private Node next = null;// 指针域

		public Node(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
		public void setValue(int value) {
			this.value = value;
		}

		public Node getNext() {
			return this.next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
	}
}
