package bf;

import java.util.concurrent.ConcurrentHashMap;

public class Test {

	public static void main(String[] args) {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		map.computeIfAbsent("AaAa", key -> map.computeIfAbsent("BbBb", key2 -> "value"));
	}// end main

}
