package cn.bin.collection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListAndMapTest {

	public static void main(String[] args) {
//		testArrayList();
//		testVector();
//		testLinkedList();
//		testArrayDeque();
//		testSynchronizedList();
//		testCopyOnWriteArrayList();
//		testConcurrentHashMap();
//		testConcurrentSkipListMap();
//		testHashMap();
		testLinkedHashMap();
//		testWeakHashMap();
//		testTreeMap();
//		testHashtable();
//		testHashSet();
//		testTreeSet();
	}

	private static void testArrayList() {
		ArrayList<String> list = new ArrayList<String>(7);
		list.add("value");
		list.add(1, "value1");
		list.add(2, "value2");
		list.add("value3");
		System.out.println(list.size());
		list.ensureCapacity(2);
		System.out.println(list.size());
		list.set(0, "value0");
		list.forEach(item -> {
			System.out.println(item);
		});
	}

	private static void testVector() {
		Vector<String> vector = new Vector<>();
		vector.add("value0");
		vector.get(0);
		vector.trimToSize();
	}

	private static void testLinkedList() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(8);
		list.add(5);
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(3);
		System.out.println("size is " + list.size());
		// 两种方式实现比较
		Collections.sort(list);
		list.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				// x.compareTo(y) 是从小往大排序
				return y.compareTo(x);
			}
		});

		for (int a : list) {
			System.out.print(a + " ");
		}
	}

	private static void testArrayDeque() {
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
		deque.addFirst(11);
		deque.addLast(12);
	}

	// synchronized (mutex) { return lst.xxx() }
	private static void testSynchronizedList() {
		List<String> synchList = Collections.synchronizedList(new ArrayList<>());
		synchList.add("hello");
		System.out.println(synchList.get(0));
	}

	private static void testCopyOnWriteArrayList() {
		List<Integer> tempList = Arrays.asList(new Integer[] { 1, 2 });
		CopyOnWriteArrayList<Integer> cpyList = new CopyOnWriteArrayList<>(tempList);
		cpyList.add(12);
	}

	private static void testConcurrentHashMap() {
		ConcurrentHashMap<String, String> concurrentMap = new ConcurrentHashMap<String, String>();
		for (int i = 1; i < 33; i++) {
			concurrentMap.put(("key" + i), ("value" + i));
		}
		concurrentMap.put("key32", "value3");
		concurrentMap.get("key32");
		concurrentMap.putIfAbsent("key32", "value3");// key相同时，不允许覆盖旧值
		System.out.println(concurrentMap.get("key32"));
		System.out.println(concurrentMap.size() == concurrentMap.mappingCount());
	}

	// 跳表
	private static void testConcurrentSkipListMap() {


	}

	private static void testHashMap() {
		HashMap<String, String> hashMap = new HashMap<String, String>(16);
		for (int i = 1; i < 33; i++) {
			hashMap.put(("key" + i), ("value" + i));
		}
		System.out.println(hashMap.size());
	}

	//  extends HashMap implements Map
	private static void testLinkedHashMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(16, 0.75f,true);
		for (int i = 1; i < 3; i++) {
			map.put(("key" + i), ("value" + i));
		}
		map.get("key1");
		System.out.println(map.size());
	}

	private static void testWeakHashMap() {
		Map<String, String> map = new WeakHashMap<>();

		for (int i = 1; i < 33; i++) {
			map.put(("key" + i), ("value" + i));
		}
		System.out.println(map.size());
	}

	private static void testTreeMap() {
		// 默认按key的升序排序
		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
		treeMap.put(("key" + 7), 7);
		treeMap.put(("key" + 1), 1);
		treeMap.put(("key" + 5), 5);
		treeMap.put(("key" + 3), 3);
		treeMap.put(("key" + 1), 11);// 不能添加重复key
		Iterator<String> it = treeMap.keySet().iterator();
		while (it.hasNext()) {
			System.out.print(treeMap.get(it.next()) + " ");
		}
	}

	private static void testHashtable() {
		Hashtable<String, String> table = new Hashtable<String, String>();
		table.put("key1", "value");
		table.get("key");
	}

	/**
	 * HashSet是通过HashMap实现的; TreeSet是通过TreeMap实现的; set.add("key")底层只是往Map中插入key.
	 * hashSet.add("key"){ hashMap.put("key", new Object())； }
	 * treeSet.add("key1"){ treeMap.put("key1", new Object()); }
	 */
	private static void testHashSet() {
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("key");
		for (String k : hashSet) {
			System.out.println(k);
		}
	}

	private static void testTreeSet() {
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.add("key5");
		treeSet.add("key1");
		treeSet.add("key3");
		for (String k : treeSet) {
			System.out.println(k);
		}
	}
}