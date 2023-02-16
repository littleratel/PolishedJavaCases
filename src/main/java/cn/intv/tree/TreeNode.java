package cn.intv.tree;

public class TreeNode<E> {
    public E val;
    public TreeNode<E> left;
    public TreeNode<E> right;
    public TreeNode<E> next;

    public TreeNode(E value) {
        this.val = value;
    }

    public TreeNode(E e, TreeNode<E> lt, TreeNode<E> rt) {
        val = e;
        left = lt;
        right = rt;
    }
}
