package cn.bin.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapTest {

	private static final ConcurrentMap<String, AtomicInteger> CACHE_MAP = new ConcurrentHashMap<>();
	private static final String KEY = "test";

	private static class TestThread implements Runnable {
		@Override
		public void run() {
			getCacheValue(KEY).incrementAndGet();
		}
	}

	public static AtomicInteger getCacheValue(String key) {
		if (CACHE_MAP.get(key) == null) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//put不管什么直接存入，返回旧值，putIfAbsent如果为null才存入，返回旧值。
//			CACHE_MAP.putIfAbsent(key, new AtomicInteger());
			CACHE_MAP.put(KEY, new AtomicInteger());//synchronized
		}
		return CACHE_MAP.get(key);
	}

	public static void main(String[] args) {
		new Thread(new TestThread()).start();
		new Thread(new TestThread()).start();
		new Thread(new TestThread()).start();
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("次数:" + CACHE_MAP.get(KEY).get());
	}
}
