package cn.intv.linkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    public static void main(String[] args) {
        final int CACHE_SIZE = 100;
        Map<String, String> cache = getLRUCache(CACHE_SIZE);
    }

    private static Map<String, String> getLRUCache(final int cacheSize) {
        int initialCapacity = (int) Math.ceil(cacheSize / 0.75f) + 1;
        float loadFactor = 0.75f;
        boolean accessOrder = true;

        /**
         * accessOrder = true 表示按照访问顺序来进行排序，最近访问的放在头部，最老访问的的放在尾部
         * accessOrder = false 所有的Entry按照插入的顺序排列
         */
        Map<String, String> map = new LinkedHashMap<String, String>(initialCapacity, loadFactor, accessOrder) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > cacheSize;
            }
        };

        return map;
    }

}
