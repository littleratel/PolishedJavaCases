package bf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
	private static Map<Integer, Integer> conMap = new ConcurrentHashMap<>();

	public static void main(String[] args) {
		System.out.println("Fibonacci result for 20 is " + fibonacci(20));
	}

	private static int fibonacci(int i) {
		if (i == 0 || i == 1) {
			return i;
		}

		return conMap.computeIfAbsent(i, (key) -> {
			System.out.println("Value is " + key);
			return fibonacci(i - 1) + fibonacci(i - 2);
		});
	}
}
