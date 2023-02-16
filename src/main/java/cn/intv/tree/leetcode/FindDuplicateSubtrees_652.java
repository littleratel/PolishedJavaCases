package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// 寻找重复子树
public class FindDuplicateSubtrees_652 extends TreeInfoPrint {

    private String SEP = ",";
    private String NULL = "#";

    // 记录重复的子树根节点
    List<TreeNode> res = new LinkedList<>();
    // 记录所有子树
    HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        FindDuplicateSubtrees_652 tool = new FindDuplicateSubtrees_652();
        Integer[] arr = {1, 2, 3, 4, null, 2, 4, null, null, null, null, 4};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        List<TreeNode> result = tool.findDuplicateSubtrees(tool.root);
        for (TreeNode node : result) {
            tool.root = node;
            BinaryTrees.println(tool);
        }
    }

    // 使用后续遍历，
    // 某一节点node，必须知道它的左右子树之后，才能判断整棵树是不是重复的
    private List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    // 序列化
    private String traverse(TreeNode root) {
        if (root == null) {
            return NULL;
        }
        // 将左右子树序列化成字符串
        String left = traverse(root.left);
        String right = traverse(root.right);

        /* 后序遍历代码位置 */
        String subTree = left + SEP + right + SEP + root.val;

        Integer times = map.getOrDefault(subTree, 0);
        // times=1, 只被加入1次
        if (times == 1) {
            res.add(root);
        }

        map.put(subTree, times + 1);

        return subTree;
    }
}
