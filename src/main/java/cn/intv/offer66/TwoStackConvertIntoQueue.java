package cn.intv.offer66;

import java.util.Stack;

/**
 * 用两个栈实现队列的Push和Pop操作 
 */
public class TwoStackConvertIntoQueue<E> {

	private Stack<E> stack1 = new Stack<E>();
	private Stack<E> stack2 = new Stack<E>();

	private E push(E e) {
		return stack1.push(e);
	}

	private E pop() {
		if (!stack2.isEmpty()) {
			return stack2.pop();
		}

		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}

		return stack2.pop();
	}

	public static void main(String[] args) {
		TwoStackConvertIntoQueue<Integer> que = new TwoStackConvertIntoQueue<Integer>();
		que.push(1);
		que.push(2);
		System.out.println(que.pop());
		que.push(3);
		que.push(4);
		System.out.println(que.pop());
		System.out.println(que.pop());
		que.push(5);
		que.push(6);
		System.out.println(que.pop());
	}
}
