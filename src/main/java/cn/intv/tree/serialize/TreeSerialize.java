package cn.intv.tree.serialize;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 要想实现反序列方法，首先要构造 root 节点。
 * 前序遍历得到的 nodes 列表中，第一个元素是 root 节点的值；
 * 后序遍历得到的 nodes 列表中，最后一个元素是 root 节点的值
 * <p>
 * 2. 中序遍历的方式行不通，因为无法实现反序列化方法。
 * 因为 root 的值被夹在两棵子树的中间，也就是在 nodes 列表的中间，我们不知道确切的索引位置，所以无法找到 root 节点；
 * <p>
 * 所以，树的序列化和反序列化，稚嫩用于前序遍历和后续遍历
 */
public class TreeSerialize extends TreeInfoPrint {

    private String SEP = ",";
    private String NULL = "#";

    public static void main(String[] args) {
        TreeSerialize tool = new TreeSerialize();

        Integer[] arr = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        // 序列化
        String treeStr;
        treeStr = tool.serialize(tool.root);
        System.out.println(treeStr);

        // 逆序列化：前序、后续
//        tool.root = tool.deserialize(treeStr);

        // 反序列化：中序遍历
        tool.root = tool.deserializeLevelTraversal(treeStr);
        BinaryTrees.println(tool);
    }

    /**
     * 序列化
     */
    private String serialize(TreeNode<Integer> root) {
        StringBuilder sb = new StringBuilder();

//        doSerializePre(root, sb);
//        doSerializePost(root, sb);
        doSerializeLevelTraversal(root, sb);

        return sb.toString();
    }

    // 前序遍历 和 后续遍历
    private void doSerializePre(TreeNode<Integer> root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        /****** 前序遍历位置 ******/
        sb.append(root.value).append(SEP);
        /***********************/

        doSerializePre(root.left, sb);
        doSerializePre(root.right, sb);
    }

    private void doSerializePost(TreeNode<Integer> root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        doSerializePost(root.left, sb);
        doSerializePost(root.right, sb);

        /****** 后序遍历位置 ******/
        sb.append(root.value).append(SEP);
        /***********************/
    }

    private void doSerializeLevelTraversal(TreeNode<Integer> root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<Integer>> queue = new LinkedList();
        queue.offer(root);

        TreeNode<Integer> cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            if (cur == null) {
                sb.append(NULL).append(SEP);
                continue;
            }
            sb.append(cur.value).append(SEP);

            queue.offer(cur.left);
            queue.offer(cur.right);
        }
    }


    private TreeNode<Integer> deserializeLevelTraversal(String rootSre) {
        String[] nodes = rootSre.split(SEP);

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        TreeNode<Integer> root = new TreeNode<>(Integer.valueOf(nodes[0]));
        queue.offer(root);

        TreeNode<Integer> cur;
        String leftStr, rightStr;
        for (int i = 1; i < nodes.length; i++) {
            cur = queue.poll();

            leftStr = nodes[i++];
            if (leftStr.equals(NULL)) {
                cur.left = null;
            } else {
                cur.left = new TreeNode<>(Integer.valueOf(leftStr));
                queue.offer(cur.left);
            }

            rightStr = nodes[i];
            if (rightStr.equals(NULL)) {
                cur.right = null;
            } else {
                cur.right = new TreeNode<>(Integer.valueOf(rightStr));
                queue.offer(cur.right);
            }
        }

        return root;
    }

    /**
     * 反序列化
     * <p>
     * 通过二叉树的前序遍历结果，还原一棵二叉树
     */
    private TreeNode<Integer> deserialize(String rootSre) {
        String[] nodes = rootSre.split(SEP);
        TreeNode<Integer> result;

        // 前序遍历
//        idx = 0;
//        result = doDeserializePre(nodes);

        // 后续遍历
        idx = nodes.length - 1;
        result = doDeserializePost(nodes);

        return result;
    }

    private static int idx = 0;

    private TreeNode<Integer> doDeserializePre(String[] nodes) {
        if (idx == nodes.length) {
            return null;
        }

        /****** 前序遍历位置 ******/
        String val = nodes[idx];
        if (val.equals(NULL)) {
            return null;
        }
        TreeNode<Integer> node = new TreeNode<>(Integer.valueOf(val));
        /***********************/

        idx++;
        node.left = doDeserializePre(nodes);
        idx++;
        node.right = doDeserializePre(nodes);

        return node;
    }

    private TreeNode<Integer> doDeserializePost(String[] nodes) {
        if (idx < 0) {
            return null;
        }

        String val = nodes[idx];
        if (val.equals(NULL)) {
            return null;
        }
        TreeNode<Integer> node = new TreeNode<>(Integer.valueOf(val));

        idx--;
        node.right = doDeserializePost(nodes);
        idx--;
        node.left = doDeserializePost(nodes);

        return node;
    }
}
