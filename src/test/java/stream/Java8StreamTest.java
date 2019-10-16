package stream;

import java.util.stream.Stream;

public class Java8StreamTest {

	public static void main(String[] args) {
		Stream.of("d2", "a2", "b1", "a5", "c", "a0").filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).sorted((s1, s2) -> {
			System.out.printf("sort: %s; %s\n", s1, s2);
			return s1.compareTo(s2);
		}).forEach(s -> System.out.println("forEach: " + s));

	}

}
