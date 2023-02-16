package cn.intv.tree.leetcode;

import cn.intv.tree.TreeNode;
import util.tree.BinaryTrees;
import util.tree.TreeInfoPrint;
import util.tree.TreeUtils;

import java.util.LinkedList;

/**
 * 通过前序和中序遍历结果构造二叉树
 */
public class Codec_449 extends TreeInfoPrint {

    public static void main(String[] args) {
        Codec_449 tool = new Codec_449();
        Integer[] arr = {2,1,3};
        tool.root = TreeUtils.initTree(arr, 0);
        BinaryTrees.println(tool);

        String rootStr = tool.serialize(tool.root);
        System.out.println(rootStr);

        tool.root = tool.deserialize(rootStr);
        BinaryTrees.println(tool);
    }

    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        doSerialize(root, sb);
        return sb.toString();
    }

    private void doSerialize(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append( "#").append(",");
            return;
        }

        sb.append(root.val).append(",");
        doSerialize(root.left, sb);
        doSerialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || "".equals(data)){
            return null;
        }

        LinkedList<String> nodes = new LinkedList<>();
        for(String node : data.split(",")){
            nodes.add(node);
        }

        return doDeserialize(nodes);
    }

    private TreeNode doDeserialize(LinkedList<String> nodes){
        if(nodes.isEmpty()){
            return null;
        }

        String rootStr = nodes.poll();
        if(rootStr.equals("#")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(rootStr));
        root.left = doDeserialize(nodes);
        root.right = doDeserialize(nodes);

        return root;
    }
}
