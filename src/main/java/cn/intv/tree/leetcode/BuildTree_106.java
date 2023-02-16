package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过前序和中序遍历结果构造二叉树
 */
public class BuildTree_106 extends TreeInfoPrint {

    public static void main(String[] args) {
        BuildTree_106 tool = new BuildTree_106();
        int[] inOrder = {9, 3, 15, 20, 7};
        int[] postOrder = {9, 15, 7, 20, 3};
        tool.root = tool.buildTree(inOrder, postOrder);
        BinaryTrees.println(tool);
    }

    /**
     * @param inOrder   中序遍历
     * @param postOrder 后续遍历
     */
    private Map<Integer, Integer> map = new HashMap<>();

    private TreeNode<Integer> buildTree(int[] inOrder, int[] postOrder) {
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }

        return build(inOrder, 0, inOrder.length - 1,
                postOrder, 0, postOrder.length - 1);
    }

    private TreeNode<Integer> build(int[] inOrder, int inStart, int inEnd,
                                    int[] postOrder, int postStart, int postEnd) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        // 找到 root index
        int idx = map.get(postOrder[postEnd]);
        TreeNode root = new TreeNode(postOrder[postEnd]);

        // 递归构造左右子树
        int leftSize = idx - inStart;
        root.left = build(inOrder, inStart, idx - 1,
                postOrder, postStart, postStart + leftSize - 1);
        root.right = build(inOrder, idx + 1, inEnd,
                postOrder, postStart + leftSize, postEnd - 1);

        return root;
    }
}
