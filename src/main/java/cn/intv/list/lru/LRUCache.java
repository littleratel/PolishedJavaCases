package cn.intv.list.lru;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        System.out.println(lRUCache.get(2));

        lRUCache.put(2, 6);
        System.out.println(lRUCache.get(1));

        lRUCache.put(1, 5);
        lRUCache.put(1, 2);
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(2));
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
        if (n == null) {
            return -1;
        }

        afterNodeAccess(n);

        return (int) n.value;
    }

    public void put(int k, int v) {
        Node n = map.get(k);
        // key is already exist?
        if (n != null) {
            n.value = v;
            afterNodeAccess(n);
            return;
        }

        // cach is full?
        if (map.size() == this.cap) {
            this.remove((int) head.key);
        }

        //
        n = new Node(k, v);
        map.put(k, n);
        if (head == null) {
            this.head = n;
            this.tail = n;
        } else {
            // add to tail
            this.tail.after = n;
            n.before = this.tail;
            this.tail = n;
        }
    }

    private void remove(int k) {
        Node p = map.remove(k);
        if (p == null) {
            return;
        }

        if (p == head) {
            if (p == tail) {
                head = null;
                tail = null;
                return;
            }

            head = p.after;
            head.before = null;
            return;
        }

        Node b = p.before, a = p.after;
        b.after = a;
        a.before = b;
    }

    private void afterNodeAccess(Node p) {
        Node last = this.tail;
        if (last == p) {
            return;
        }

        Node b = p.before, a = p.after;
        p.after = null;

        // Point to a
        if (b == null)
            head = a;
        else
            b.after = a;

        // Point to b
        if (a != null)
            a.before = b;
        else
            last = b;

        // Point to p
        if (last == null)
            head = p;
        else {
            p.before = last;
            last.after = p;
        }

        tail = p;
    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> before, after;

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
            n = n.after;
        }

        return sb.toString();
    }
}
