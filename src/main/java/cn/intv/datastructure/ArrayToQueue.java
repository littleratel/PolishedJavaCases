package cn.intv.datastructure;

@SuppressWarnings("unchecked")
public class ArrayToQueue<E> {
	private final E[] queue;
	private final int CAPACITY;
	private int head = 0;
	private int tail = 0;
	private int count = 0;

	public ArrayToQueue(int size) {
		this.CAPACITY = size;
		queue = (E[]) new Object[CAPACITY];
	}

	public ArrayToQueue() {
		this(16);
	}

	/**
	 * Retrieves and removes the head of this queue, or returns {@code null} if
	 * this queue is empty.
	 * 
	 * poll
	 */
	public E get() {
		if (count == 0)
			return null;
		E e = queue[head++];
		head %= CAPACITY;
		count--;
		return e;
	}

	/**
	 * 从队尾插入新元素，未成功返回false
	 * 
	 * offer
	 */
	public boolean add(E data) {
		if (count == CAPACITY)
			return false;
		queue[tail++] = data;
		tail %= CAPACITY;
		count++;
		return true;
	}

	public E getHead() {
		if (count == 0)
			return null;
		return queue[head];
	}

	public int size() {
		return count;
	}

	public boolean isFull() {
		return count == CAPACITY;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public static void main(String[] args) {
		ArrayToQueue<Integer> que = new ArrayToQueue<Integer>(10);

		for (int j = 0; j < 3; j++) {
			for (int i = 1; i < 11; i++) {
				que.add(i);
			}
			for (int i = 1; i < 6; i++) {
				System.out.print(que.get() + ", ");
			}
			System.out.println();
			for (int i = 20; i < 25; i++) {
				que.add(i);
			}
		}

		int size = que.size();
		for (int i = 1; i <= size; i++) {
			System.out.print(que.get() + ", ");
		}
	}
}
