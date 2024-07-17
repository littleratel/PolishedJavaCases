package cn.intv.tree;

import java.util.ArrayList;
import java.util.List;

public class MaxDepthOfNaryTree {

    /**
     * 如果一棵树如下所示：
     *    1
     *   /|\
     *  2 3 4
     *  /|  |\
     * 5 6  7 8
     * 这棵树的最大深度是3, 从根节点1到叶子节点5、6、7或8的路径）
     */
    public static void main(String[] args) {
        Node root = new Node(1);
        Node child2 = new Node(2);
        Node child3 = new Node(3);
        Node child4 = new Node(4);
        Node child5 = new Node(5);
        Node child6 = new Node(6);
        Node child7 = new Node(7);
        Node child8 = new Node(8);
        Node child9 = new Node(9);

        // lever 1
        root.children.add(child2);
        root.children.add(child3);
        root.children.add(child4);

        // lever 2
        child2.children.add(child5);
        child2.children.add(child6);

        // lever 3
        child4.children.add(child7);
        child4.children.add(child8);

        // lever 4
        child6.children.add(child9);

        System.out.println(maxDepth(root));
    }

    private static int maxDepth(Node root) {
        if (root == null) return 0;

        int maxDepth = 0;
        for (Node child : root.children) {
            maxDepth = Math.max(maxDepth, maxDepth(child));
        }

        return maxDepth + 1; // 加1以包括当前节点
    }

    private static class Node {
        int value;
        List<Node> children;

        public Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }
}
