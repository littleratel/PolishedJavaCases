package cn.intv.tree.tmp;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

public class InvertTree_226 extends TreeInfoPrint {
    public static void main(String[] args) {
        InvertTree_226 tool = new InvertTree_226();
        Integer[] arr = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        tool.root = tool.invertTree(tool.root);
        BinaryTrees.println(tool);
    }

    // 将整棵树的节点翻转
    // 前序遍历、后序遍历都是可以，但中序遍历不行
    private TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }

        /**** 前序遍历位置 ****/
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 让左右子节点继续翻转 它们的子节点
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
