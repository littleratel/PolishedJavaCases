package cn.intv;

import java.util.Arrays;
import java.util.Stack;

public class BracketsMatching {

    public static void main(String[] args) {
        String[] brackets = {"{{{}{}}{}}{}", // true
                "{{}}{}}{}", // false
                "{{{}{}}", // false
                "{{}{{}}{{}}}", // true
                "{{" // false
        };

        Arrays.stream(brackets).forEach(str -> System.out.println(isMatch2(str)));
    }

    public static Boolean isMatch2(String str) {
        System.out.println("str is :" + str);
        int len = str.length();
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < len; i++) {
            stack.push(str.charAt(i));
        }

        int count = 0;
        for (; stack.size() > 0; ) {
            count = (stack.pop().charValue() == '}') ? count++ : count--;
            if (count < 0) {
                return false;
            }
        }

        return (count == 0) ? true : false;
    }

	public static Boolean isMatch(String str) {
		int len = str.length();
		Stack<Character> stack = new Stack<Character>();
		char ctmp;
		for (int i = 0; i < len; i++) {
			if ((ctmp = str.charAt(i)) == '}' && stack.size() == 0) {
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
