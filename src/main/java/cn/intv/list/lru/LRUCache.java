package cn.intv.list.lru;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        System.out.println(lRUCache.get(2)); // -1

        lRUCache.put(1, 111);
        lRUCache.put(2, 222);
        lRUCache.put(2, 22);
        System.out.println(lRUCache.get(2)); // 22
        lRUCache.put(3, 333);
        System.out.println(lRUCache.get(1)); // -1
        System.out.println(lRUCache.get(2)); // 22
        System.out.println(lRUCache.toString());
    }

    private Node head = null;
    private Node tail = null;
    private final int cap;
    private final Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.map = new HashMap(cap, 1);
    }

    public int get(int k) {
        Node n = map.get(k);
        if(n == null){
            return -1;
        }

        afterNodeAccess(n);

        return (int)n.value;
    }

    public void put(int k, int v) {
        Node n = map.get(k);
        if(n != null){
            n.value = v;
            afterNodeAccess(n);
            return;
        }

        if(this.cap == map.size()){
            // 缓存已满，删除head节点
            remove((int)head.key);
        }

        // 添加新节点
        n = new Node(k, v);
        map.put(k, n);

        if(this.head == null){
            this.head = n;
            this.tail = n;
            return;
        }

        n.pre = this.tail;
        this.tail.next = n;
        this.tail = n;
    }

    private void remove(int k) {
        Node n = map.remove(k);
        if(n == null){
            return;
        }

        Node pre = n.pre, next = n.next;
        if(pre == null){
            this.head = next;
        } else {
            pre.next = next;
        }

        if(next == null){
            this.tail = pre;
        } else {
            next.pre = pre;
        }
    }

    private void afterNodeAccess(Node p) {
        if(this.tail == p){
            return;
        }

        // p.next != null
        Node pre = p.pre, next = p.next;
        if(pre != null){
            pre.next = next;
        } else {
            this.head = next;
        }

        if(next != null){
            next.pre = pre;
        }else {
            this.tail = pre;
        }

        // 将 p 设置成 tail
        p.pre = this.tail;
        this.tail.next = p;
        p.next = null;

        this.tail = p;
    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> pre, next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public String toString() {
        Node n = this.head;
        StringBuilder sb = new StringBuilder();
        while (n != null) {
            sb.append(n.key).append(":").append(n.value).append(", ");
            n = n.next;
        }

        return sb.toString();
    }
}
