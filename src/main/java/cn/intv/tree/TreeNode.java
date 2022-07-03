package cn.intv.tree;

public class TreeNode<E> {
    public E value;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode(E value) {
        this.value = value;
    }

    public TreeNode(E e, TreeNode<E> lt, TreeNode<E> rt) {
        value = e;
        left = lt;
        right = rt;
    }
}
