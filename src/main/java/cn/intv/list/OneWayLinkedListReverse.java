package cn.intv.list;

import cn.intv.list.utils.ListUtil;
import cn.intv.list.utils.ListUtil.Node;

public class OneWayLinkedListReverse {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        Node node = ListUtil.init(arr);
        ListUtil.print(node);

        Node tmp = reverse(node);
        ListUtil.print(tmp);
    }

    public static Node reverse(Node node) {
        Node tmp1, tmp2;
        tmp1 = node.next;
        node.next = null;

        while (null != tmp1.next) {
            tmp2 = tmp1.next;
            tmp1.next = node;
            node = tmp1;
            tmp1 = tmp2;
        }

        tmp1.next = node;
        node = tmp1;
        return node;
    }
}
