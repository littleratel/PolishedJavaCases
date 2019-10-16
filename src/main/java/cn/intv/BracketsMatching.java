package cn.intv;

import java.util.Arrays;
import java.util.Stack;

public class BracketsMatching {

	public static void main(String[] args) {
		String[] brackets = { "{{{}{}}{}}{}", // true
				"{{}}{}}{}", // false
				"{{{}{}}", // false
				"{{}{{}}{{}}}", // true
				"{{" // false
		};

		Arrays.stream(brackets).forEach(str -> System.out.println(isMatch(str)));
	}

	public static Boolean isMatch(String str) {
		int len = str.length();
		Stack<Character> stack = new Stack<Character>();
		char ctmp;
		for (int i = 0; i < len; i++) {
			if ( (ctmp= str.charAt(i)) == '}' && stack.size() == 0) {
				return false;
			} else if (ctmp == '{') {
				stack.push(ctmp);
				continue;
			}

			if (ctmp == stack.peek()) {
				stack.push(ctmp);
			} else {
				stack.pop();
			}
		}

		return stack.size() == 0;
	}

}
