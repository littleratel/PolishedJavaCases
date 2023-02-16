package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换;
 * 请在不改变其结构的情况下，恢复这棵树
 */
public class RecoverTree_99 extends TreeInfoPrint {
    public static void main(String[] args) {
        RecoverTree_99 tool = new RecoverTree_99();
        Integer[] arr = {3, 1, 4, null, null, 2};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        System.out.println();

        tool.recoverTree(tool.root);
        BinaryTrees.println(tool);
    }

    private TreeNode<Integer> first = null, second = null, prev = null;
    private int times = 0; // 记录不满足 left < root < right 的次数

    /**
     * 递归调用方式
     */
    public void recoverTree(TreeNode root) {
        inorderTraverse(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorderTraverse(TreeNode<Integer> root) {
        if (root == null || times > 1) {
            return;
        }

        inorderTraverse(root.left);

        if (prev != null && root.val < prev.val) {
            times++;
            second = root;  // 第二个错位节点是 root
            if (first == null) {
                // 第一个错位节点是 prev
                first = prev;
            }
        }
        prev = root;

        inorderTraverse(root.right);
    }


    /**
     * 中序遍历 非递归
     */
    public void midOrderNonRecursive(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
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

    /**
     * 非递归方式
     */
    public void recoverTreeNonRecursive(TreeNode<Integer> root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode<Integer> first = null, second = null, pred = null;

        while (root != null || !stack.isEmpty()) {
            // 左孩子依次加入stack
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (pred != null && root.val < pred.val) {
                second = root;
                if (first == null) {
                    first = pred;
                } else {
                    break;
                }
            }
            pred = root;

            //
            root = root.right;
        }

        swap(first, second);
    }

    public void swap(TreeNode<Integer> x, TreeNode<Integer> y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }
}
