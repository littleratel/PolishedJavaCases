package cn.intv.list;

import cn.intv.list.utils.ListUtil;
import cn.intv.list.utils.ListUtil.Node;
import org.junit.Test;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 *  l1 = 2 -> 4 -> 3
 *  l2 = 5 -> 6 -> 4
 * Return :
 *  7 -> 0 -> 8
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
        int tmp, flag = 0;
        while (l1 != null || l2 != null) {
            tmp = flag;
            if (l1 != null) {
                tmp += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                tmp += l2.val;
                l2 = l2.next;
            }

            flag = tmp / 10;
            tmp %= 10;

            cur.next = new Node(tmp);
            cur = cur.next;
        }

        if (flag != 0) {
            cur.next = new Node(flag);
        }

        return root.next;
    }
}