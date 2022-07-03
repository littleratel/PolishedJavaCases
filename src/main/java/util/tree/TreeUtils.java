package util.tree;

import cn.intv.tree.TreeNode;

public class TreeUtils {
    public static TreeNode<Integer> initTree(Integer[] arr, int parent) {
        TreeNode<Integer> root = new TreeNode<>(arr[parent]);
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;

        if (left <= arr.length - 1 && arr[left] != null) {
            root.left = initTree(arr, left);
        }
        if (right <= arr.length - 1 && arr[right] != null) {
            root.right = initTree(arr, right);
        }

        return root;
    }
}
