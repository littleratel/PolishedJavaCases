package cn.intv.list;

/**
 * 链表反转
 */
public class ListReversal {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

//        Node header = oneWayListReversal(node1);
        Node header = reverseKGroup(node1, 3);
        while (header != null) {
            System.out.print(header.value + " ");
            header = header.next;
        }
        System.out.println();
    }

    /**
     * 单方向链表翻转
     */
    private static Node oneWayListReversal(Node head) {
        Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;  // 保存下一个节点
            current.next = prev;  // 翻转当前节点的指针
            prev = current;       // 前进到下一个节点
            current = next;       // 继续下一轮循环
        }

        return prev;
    }

    /**
     * LeetCode 25 K个一组翻转链表
     * <p>
     * 给你链表的头节点head ，每k个节点一组进行翻转，返回修改后的链表;
     * k是一个正整数，它的值<=链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序;
     * <p>
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     */
    private static Node reverseKGroup(Node head, int k) {
        if (head == null || head.next == null) {
            return head;
        } else if (k == 1) {
            return oneWayListReversal(head);
        }

        Node header = new Node(-1);
        Node tail = header;
        Node curHead = head;
        while (curHead != null) {
            Node start = curHead, end;
            // 用于将tail快速移动到尾节点
            Node first = curHead;

            // 分段截取
            int i = 0;
            while (i < k - 1 && curHead.next != null) {
                curHead = curHead.next;
                i++;
            }

            // 记录分段链表的尾节点
            end = curHead;
            // 记录下个分段链表的首节点
            curHead = curHead.next;
            // 将该段链表的尾节点设置为Null
            end.next = null;

            // 分段翻转
            if (i == k - 1) {
                tail.next = oneWayListReversal(start);
                tail = first;
            } else {
                tail.next = start;
            }
        }

        return header.next;
    }

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
}
