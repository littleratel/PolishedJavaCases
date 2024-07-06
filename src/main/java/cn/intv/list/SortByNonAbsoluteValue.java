package cn.intv.list;

import cn.intv.list.utils.ListUtil;
import cn.intv.list.utils.ListUtil.Node;

/**
 * 一个本身按数字绝对值大小排序的链表，输出按实际值大小排序的链表， 要求: T:O(n)，S:O(1)
 */
public class SortByNonAbsoluteValue {

    public static void main(String[] args) {
        int[] arr = {-1, -2, -3, 4, 5, 6, 7, -8, -9, -10, -11, -12};
        Node head = ListUtil.init(arr);

        Node node = doSearch(head);
        ListUtil.print(node);
    }

    public static Node doSearch(Node node) {
        Node head = node, next;
        while (node != null) {
            next = node.next;

            // 如果next小于前一个节点，直接放到队头
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
