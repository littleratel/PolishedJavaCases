package cn.intv.stack;

/**
 * LeetCode 155. 最小栈
 * 设计一个支持push,pop,top操作的实现类MinStack，并能在常数时间内检索到最小元素的栈
 */
public class MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   // 返回 -3.
        minStack.pop();
        minStack.top();      // 返回 0.
        System.out.println(minStack.getMin());  // 返回 -2.
    }

    // 栈顶
    Node top = null;

    // 将元素val推入堆栈
    public void push(int val) {
        Node tmp = new Node(val);
        if (top == null) {
            tmp.min = val;
            top = tmp;
            return;
        }

        tmp.min = Math.min(val, top.min);
        tmp.next = top;
        top = tmp;
    }

    // 删除堆栈顶部的元素
    public void pop() {
        assertStackEmpty();
        top = top.next;
    }

    // 获取堆栈顶部的元素
    public int top() {
        assertStackEmpty();
        return top.value;
    }

    // 获取堆栈中的最小元素
    public int getMin() {
        assertStackEmpty();
        return top.min;
    }

    private void assertStackEmpty() {
        if (top == null) {
            throw new RuntimeException("Stack empty error!");
        }
    }

    private static class Node {
        private int value;
        private Node next;
        private int min;

        public Node(int value) {
            this.value = value;
        }
    }
}
