package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;

/**
 * 构造最大二叉树
 */
public class ConstructMaximumBinaryTree_654 extends TreeInfoPrint {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 6, 0, 5};
        ConstructMaximumBinaryTree_654 tool = new ConstructMaximumBinaryTree_654();

        tool.root = tool.constructMaximumBinaryTree(arr);
        BinaryTrees.println(tool);
    }

    private TreeNode<Integer> constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode<Integer> build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 找到数组中的最大值和对应的索引
        int max = nums[left], idx = left;
        for (int i = left + 1; i <= right; i++) {
            if (max < nums[i]) {
                max = nums[i];
                idx = i;
            }
        }
        TreeNode<Integer> node = new TreeNode<>(nums[idx]);

        // 递归构建左右子树
        node.left = build(nums, left, idx - 1);
        node.right = build(nums, idx + 1, right);

        return node;
    }
}
