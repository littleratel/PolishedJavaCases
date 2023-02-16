package cn.intv.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import util.tree.TreeUtils;

public class TreeTraverse<E extends Comparable<? super E>> {

    public static void main(String[] args) {
        Integer[] arrFalse = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9};
        TreeNode<Integer> root = TreeUtils.initTree(arrFalse, 0);
        TreeTraverse<Integer> tree = new TreeTraverse<>();

//        System.out.print("递归前序遍历:");
//        tree.preOrder(root);

//        System.out.print("\n非递归前序遍历:");
//        tree.preOrderNonRecursive(root);

		  System.out.print("\n递归 中序遍历:");
		  tree.midOrder(root);

		  System.out.print("\n非递归中序遍历:");
		  tree.midOrderNonRecursive(root);

//        System.out.print("\n递归后序遍历 :");
//        tree.posOrder(root);

//        System.out.print("\n非递归后序遍历:");
//        tree.posOrderNonRecursive(root);

//        System.out.print("\n 递归层序遍历:");
//        tree.levelOrder(root);

//        System.out.print("\n非递归层序遍历:");
//        tree.levelOrderNonRecursive(root);
    }

    /**
     * 前序遍历 递归
     */
    public void preOrder(TreeNode<E> Node) {
        if (Node != null) {
            System.out.print(Node.val + " ");
            preOrder(Node.left);
            preOrder(Node.right);
        }
    }

    /**
     * 中序遍历 递归
     */
    public void midOrder(TreeNode<E> Node) {
        if (Node != null) {
            midOrder(Node.left);
            System.out.print(Node.val + " ");
            midOrder(Node.right);
        }
    }

    /**
     * 后序遍历 递归
     */
    public void posOrder(TreeNode<E> Node) {
        if (Node != null) {
            posOrder(Node.left);
            posOrder(Node.right);
            System.out.print(Node.val + " ");
        }
    }

    /*
     * 层序遍历 递归
     */
    public void levelOrder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        int depth = depth(root);
        for (int i = 1; i <= depth; i++) {
            levelOrder(root, i);
        }
    }

    public int depth(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }

        int l = depth(node.left);
        int r = depth(node.right);

        return Math.max(l, r) + 1;
    }

    private void levelOrder(TreeNode<E> node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.val + " ");
            return;
        }

        levelOrder(node.left, level - 1);
        levelOrder(node.right, level - 1);
    }

    // 前序遍历 非递归
    public void preOrderNonRecursive(TreeNode<E> root) {
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root);
        TreeNode tmp;
        while (!stack.empty()) {
            tmp = stack.pop();
            System.out.print(tmp.val + " "); // root
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
    }

    // 中序遍历 非递归
    public void midOrderNonRecursive(TreeNode<E> root) {
        Stack<TreeNode<E>> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            // 左孩子依次加入stack
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            System.out.print(root.val + " ");
            root = root.right;
        }
    }

    // 后序遍历 非递归 左-右-中
    // 入栈顺序：中-左-右 出栈顺序：中-右-左
    // 最后翻转结果
    public void posOrderNonRecursive(TreeNode<E> root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add((Integer) node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(result);
        result.stream().forEach((v) -> {
            System.out.print(v + " ");
        });

//        Stack<TreeNode<E>> stack1 = new Stack<>();
//        Stack<Integer> stack2 = new Stack<>();
//        int i = 1;
//        while (Node != null || !stack1.empty()) {
//            while (Node != null) {
//                stack1.push(Node);
//                stack2.push(0);
//                Node = Node.left;
//            }
//
//            while (!stack1.empty() && stack2.peek() == i) {
//                stack2.pop();
//                System.out.print(stack1.pop().value + " ");
//            }
//
//            if (!stack1.empty()) {
//                stack2.pop();
//                stack2.push(1);
//                Node = stack1.peek();
//                Node = Node.right;
//            }
//        }
    }

    /*
     * 层序遍历 非递归
     */
    public void levelOrderNonRecursive(TreeNode<E> Node) {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(Node);
        TreeNode<E> tmp;
        while (queue.size() != 0) {
            tmp = queue.poll(); // remove()
            System.out.print(tmp.val + " ");

            if (tmp.left != null) {
                queue.offer(tmp.left); // add()
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
            }
        }
    }

}
