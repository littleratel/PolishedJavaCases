package cn.intv.list.utils;

import java.util.HashSet;
import java.util.Set;

public class ListUtil {

    private ListUtil() {
    }

    public static Node init(int[] arr) {
        int len = arr.length;
        Node rootBak = new Node(arr[0]);
        Node root = rootBak;
        for (int i = 1; i < len; i++) {
            Node tmp = new Node(arr[i]);
            rootBak.next = tmp;
            rootBak = rootBak.next;
        }

        return root;
    }

    public static Node initCycle(int[] arr, int crossNodeIdx) {
        int len = arr.length;
        if (crossNodeIdx >= len) {
            return null;
        }

        Node root = new Node(arr[0]);
        Node tail = root;
        Node crossNode = null;
        for (int i = 1; i < len; i++) {
            Node tmp = new Node(arr[i]);
            if (i == crossNodeIdx) {
                crossNode = tmp;
            }

            tail.next = tmp;
            tail = tail.next;
        }

        tail.next = crossNode;

        return root;
    }

    public static class Node {
        public Node next;
        public final int val;

        public Node(int x) {
            this.val = x;
            next = null;
        }
    }

    public static void print(Node node) {
        Node tmp = node;

        Set<Node> set = new HashSet<>();
        StringBuilder sb1 = new StringBuilder("val: ");
        for (; ; ) {
            if (tmp == null) {
                break;
            }

            sb1.append(tmp.val).append(" ");

            if (!set.add(tmp)) {
                break;
            }

            tmp = tmp.next;
        }

        System.out.println(sb1);
        System.out.println();
    }
}
