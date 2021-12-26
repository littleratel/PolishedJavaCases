package cn.intv.list.utils;

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

    public static class Node {
        public Node next;
        public final int val;

        public Node(int x) {
            this.val = x;
            next = null;
        }
    }

    public static void print(Node lst) {
        while (lst != null) {
            System.out.print(lst.val + " ");
            lst = lst.next;
        }

        System.out.println();
    }
}