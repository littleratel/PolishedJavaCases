package cn.intv.list.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedHashMap {
    public static void main(String[] args) {
        final int CACHE_SIZE = 100;
        Map<String, String> lruCache = getLRUCache(CACHE_SIZE);

        lruCache.put("001", "用户1信息");
        lruCache.put("002", "用户2信息");
        lruCache.put("003", "用户3信息");
        lruCache.put("004", "用户4信息");
        lruCache.put("005", "用户5信息");
        System.out.println(lruCache.toString());
        lruCache.get("002");
        System.out.println(lruCache.toString());
        lruCache.put("004", "用户2信息更新");
        lruCache.put("006", "用户6信息");
        System.out.println(lruCache.toString());
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
        System.out.println(lruCache.toString());
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
