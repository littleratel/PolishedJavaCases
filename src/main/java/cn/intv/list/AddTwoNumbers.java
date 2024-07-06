package cn.intv.list;

import cn.intv.list.utils.ListUtil;
import cn.intv.list.utils.ListUtil.Node;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * l1 = 2 -> 4 -> 3
 * l2 = 5 -> 6 -> 4
 * Return :
 * 7 -> 0 -> 8
 */
public class AddTwoNumbers {

    @Test
    public void main() {
        Node l1 = ListUtil.init(new int[]{9, 9, 9, 9, 9, 9, 9});
        Node l2 = ListUtil.init(new int[]{9, 9, 9, 9});
        Node res = addTwoNumbers(l1, l2);
        ListUtil.print(res);
    }

    private Node addTwoNumbers(Node l1, Node l2) {
        Node root = new Node(-1), cur = root;

        int tmp, carry = 0;
        while (l1 != null || l2 != null) {
            tmp = carry;
            if (l1 != null) {
                tmp += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                tmp += l2.val;
                l2 = l2.next;
            }

            carry = tmp / 10;

            cur.next = new Node(tmp % 10);
            cur = cur.next;
        }

        if (carry != 0) {
            cur.next = new Node(carry);
        }

        return root.next;
    }

    private Node addTwoNumbers2(ListUtil.Node l1, ListUtil.Node l2) {
        LinkedList<Integer> stack1 = new LinkedList<>();
        LinkedList<Integer> stack2 = new LinkedList<>();
        LinkedList<Integer> stack3 = new LinkedList<>();

        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int flag = 0;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int v1 = stack1.poll();
            int v2 = stack2.poll();

            int sum = v1 + v2 + flag;
            int curVal = sum % 10;
            flag = sum / 10;

            stack3.push(curVal);
        }

        while (!stack1.isEmpty()) {
            int v = stack1.poll();
            int sum = v + flag;
            int curVal = sum % 10;
            flag = sum / 10;

            stack3.push(curVal);
        }

        while (!stack2.isEmpty()) {
            int v = stack2.poll();
            int sum = v + flag;
            int curVal = sum % 10;
            flag = sum / 10;

            stack3.push(curVal);
        }

        if (flag != 0) {
            stack3.push(flag);
        }

        Node tmp = new Node(stack3.poll());
        Node header = tmp;
        while (!stack3.isEmpty()) {
            tmp.next = new Node(stack3.poll());
            tmp = tmp.next;
        }

        return header;
    }
}
