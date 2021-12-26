package cn.intv.tree;

public class InitTree {
    public static TreeNode<Integer> initTree(Integer[] arr, int parent) {
        TreeNode<Integer> root = new TreeNode<>(arr[parent]);
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;

        if(left <= arr.length - 1 && arr[left] != null){
			root.left = initTree(arr, left);
		}
		if(right <= arr.length - 1 && arr[right] != null){
			root.right = initTree(arr, right);
		}

        return root;
    }

    public static class TreeNode<E> {
        final E value;
        TreeNode<E> left;
        TreeNode<E> right;

        TreeNode(E value) {
            this.value = value;
        }

        TreeNode(E e, TreeNode<E> lt, TreeNode<E> rt) {
            value = e;
            left = lt;
            right = rt;
        }
    }
}
