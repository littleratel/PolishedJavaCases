package bf;

import java.util.Arrays;
import java.util.List;

public class TestDeadCycle {
	private static final List<String> AAS_PREFIXES = Arrays.asList("AIR 32", "AIR 64", "MIB");

	public static void main(String[] args) {
		System.out.println(productMatch("AIR6472"));
		System.out.println(productMatch("AIR 3239"));
		System.out.println(productMatch("MIB312"));
	}

	private static boolean productMatch(String productName) {
		if (AAS_PREFIXES.stream().anyMatch(productName::contains)) {
			return true;
		}
		return false;
	}
}
