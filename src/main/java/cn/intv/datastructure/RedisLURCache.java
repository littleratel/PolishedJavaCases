package cn.intv.datastructure;

import java.util.HashMap;
import java.util.Map;

public class RedisLURCache<k, v> {
    private int capacity;  //容量
    private int count;    //当前有多少节点
    private Map<k, Node<k, v>> nodeMap;  //缓存节点
    private Node<k, v> head;
    private Node<k, v> tail;

    public RedisLURCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.valueOf(capacity));
        }

        this.capacity = capacity;
        this.nodeMap = new HashMap<>();

        //初始化头节点和尾节点，利用哨兵模式减少判断头结点和尾节点为空的代码
        Node headNode = new Node(null, null);
        Node tailNode = new Node(null, null);

        headNode.next = tailNode;
        tailNode.pre = headNode;
        this.head = headNode;
        this.tail = tailNode;
    }

    public void put(k key, v value) {
        Node<k, v> node = nodeMap.get(key);
        if (node == null) {
            if (count >= capacity) {
                removeNode();  //先移除一个节点
            }
            node = new Node<>(key, value);
            addNode(node);
        } else {
            moveNodeToHead(node);  //移动节点到头节点
        }
    }

    public Node<k, v> get(k key) {
        Node<k, v> node = nodeMap.get(key);
        if (node != null) {
            moveNodeToHead(node);
        }
        return node;
    }

    //从链表里面移除, 添加节点到头部
    public void moveNodeToHead(Node<k, v> node) {
        removeFromList(node);
        addToHead(node);
    }

    private void removeNode() {
        Node node = tail.pre;
        removeFromList(node);
        nodeMap.remove(node.key);
        count--;
    }

    private void removeFromList(Node<k, v> node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
        node.next = null;
        node.pre = null;
    }

    private void addNode(Node<k, v> node) {
        addToHead(node);
        nodeMap.put(node.key, node);
        count++;
    }

    private void addToHead(Node<k, v> node) {
        Node next = head.next;
        next.pre = node;
        node.next = next;
        node.pre = head;
        head.next = node;
    }

    class Node<k, v> {
        k key;
        v value;
        Node pre;
        Node next;

        public Node(k key, v value) {
            this.key = key;
            this.value = value;
        }
    }
}
