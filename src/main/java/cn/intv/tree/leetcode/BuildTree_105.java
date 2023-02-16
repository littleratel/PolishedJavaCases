package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过前序和中序遍历结果构造二叉树
 */
public class BuildTree_105 extends TreeInfoPrint {

    public static void main(String[] args) {
        BuildTree_105 tool = new BuildTree_105();
//        int[] preOrder = {3, 9, 20, 15, 7};
//        int[] inOrder = {9, 3, 15, 20, 7};

        int[] preOrder = {1, 2, 3};
        int[] inOrder = {3, 2, 1};

        tool.root = tool.buildTree(preOrder, inOrder);
        BinaryTrees.println(tool);
    }

    /**
     * @param preOrder 前序遍历
     * @param inOrder  中序遍历
     */
    private Map<Integer, Integer> map = new HashMap<>();

    private TreeNode<Integer> buildTree(int[] preOrder, int[] inOrder) {
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }

        return build(preOrder, 0, preOrder.length - 1,
                inOrder, 0, inOrder.length - 1);
    }

    private TreeNode<Integer> build(int[] preOrder, int preStart, int preEnd,
                                    int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // 找到 root
        int rootV = preOrder[preStart];
        int inOrderRootIdx = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == rootV) {
                inOrderRootIdx = i;
                break;
            }
        }

        TreeNode root = new TreeNode(rootV);

        // 递归构造左右子树
        int leftSize = inOrderRootIdx - inStart;
        root.left = build(preOrder, preStart + 1, preStart + leftSize,
                inOrder, inStart, inOrderRootIdx - 1);

        root.right = build(preOrder, preStart + leftSize + 1, preEnd,
                inOrder, inOrderRootIdx + 1, inEnd);

        return root;
    }
}
