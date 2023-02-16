package cn.intv.unionfind;

public class UnionFind {
    private int[] parent;

    public UnionFind(int cap) {
        parent = new int[cap];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public int find(int v) {
        return parent[v];
    }

    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    // 将v1 v2 两个节点合并到同一个集合
    // 将所有的父节点为p1的节点 父节点都换成 p2
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == p1) parent[i] = p2;
        }
    }
}
