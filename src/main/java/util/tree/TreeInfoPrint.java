package util.tree;

import cn.intv.tree.TreeNode;

public abstract class TreeInfoPrint implements BinaryTreeInfo {
    protected TreeNode<Integer> root;

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((TreeNode) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((TreeNode) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((TreeNode) node).val;
    }

    public void printTree() {
        BinaryTrees.println(this);
    }
}
