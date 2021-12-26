package cn.intv.list;

import cn.intv.list.utils.ListUtil;
import cn.intv.list.utils.ListUtil.Node;
import org.junit.Test;

import java.util.HashSet;

/**
 * 判断一链表是否有环; 有则找到交叉点.
 */
public class CircularList {

    @Test
    public void main() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Node node = initCircularList(arr);
        findCrossedNode(node);
    }

    private Node initCircularList(int[] arr) {
        Node node = ListUtil.init(arr);

        Node tmp = node;
        for (; ; ) {
            if (tmp.next != null) {
                tmp = tmp.next;
            } else {
                // Set Node(10).next =  Node(3)
                tmp.next = node.next.next;
                break;
            }
        }
        return node;
    }

    /**
     * 使用 Set， 判断 Node节点的唯一性
     * */
    private Node hasCycle(Node root) {
        HashSet<Node> set = new HashSet<>();
        Node tmp = root;
        while (tmp != null) {
            if (!set.add(tmp)) {
                System.out.println(tmp.val);
                return tmp;
            }

            tmp = tmp.next;
        }

        System.out.println("NOT FOUND CYCLE!");
        return null;
    }

    private void findCrossedNode(final Node node) {
        Node oneStep = node;
        Node twoStep = node;
        int stepCount = 0;
        for (; ; ) {
            if (twoStep.next == null || twoStep.next.next == null) {
                System.out.println("This is not a Circular List!");
                break;
            }

            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
            stepCount++;
            if (oneStep == twoStep) {
                // oneStep = twoStep = Node(9), not Node(3)
                // twoStep 比 oneStep 多走了一个环的node的个数(8), 但不代表只比 oneStep 多转一圈
                System.out.println("This is a Circular List!");
                System.out.println("Find the same steps is " + oneStep.val);
                System.out.println("Count of node on the circular list is " + stepCount);
                break;
            }
        }

        // get the final cross node
        oneStep = node;
        while (oneStep != twoStep) {
            oneStep = oneStep.next;
            twoStep = twoStep.next;
        }
        System.out.println("The 1st cross node is " + oneStep.val);
    }

}
