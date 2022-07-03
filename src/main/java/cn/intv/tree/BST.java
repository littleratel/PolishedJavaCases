package cn.intv.tree;

import java.util.Stack;

import util.tree.TreeUtils;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 */
public class BST {

    public static void main(String[] args) {
        Integer[] arrTrue = {5, 1, 7, null, 3, 6, 9};
        Integer[] arrFalse = {10, 6, 15, 7, 8, 13, 17, 4, null, null, null};
        TreeNode<Integer> tree = TreeUtils.initTree(arrFalse, 0);
//        System.out.println(isValidBST2(tree));
//        System.out.println(isValidBST(tree));
//        System.out.println(isSearchTree(tree));
        recoverTree(tree);
        System.out.println();
    }

    /**
     * 非递归实现.
     * 中序遍历，left->root->right
     * 保存一个前驱节点，这样在每检查一个节点的时候，就跟前驱节点对比，如果比前驱节点小（或者等于）就表示不合法.
     */
    private static boolean isValidBST(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && pre.value >= root.value)
                return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    private static boolean isSearchTree(TreeNode<Integer> root) {
        int preVal = Integer.MIN_VALUE;
        Stack<TreeNode<Integer>> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.value <= preVal) {
                return false;
            }
            preVal = root.value;
            root = root.right;
        }

        return true;
    }

    /**
     * 递归实现.
     * 采用自顶向下的遍历，对于每个节点，left < root < right.
     */
    private static boolean isValidBST2(TreeNode<Integer> root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode<Integer> root, int minVal, int maxVal) {
        if (root == null) return true;

        if (root.value >= maxVal || root.value <= minVal)
            return false;

        // left < root  &&  root < right
        return isValidBST(root.left, minVal, root.value) && isValidBST(root.right, root.value, maxVal);
    }

    /**
     * 恢复二叉搜索时
     * <p>
     * 给定一棵二叉搜索树的根节点root，该树中的两个节点(可以是父/子节点)被错误地交换。
     * 请在不改变其结构的情况下，恢复这棵树。
     */
    private static TreeNode<Integer> t1, t2;

    public static void recoverTree(TreeNode<Integer> root) {
        inorder(root);
        int temp = t1.value;
        t1.value = t2.value;
        t2.value = temp;
    }

    private static void inorder(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && pre.value >= root.value) {
                t1 = pre;
                t2 = root;
            }

            pre = root;
            root = root.right;
        }
    }
}
