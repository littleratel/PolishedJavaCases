package cn.intv.string;

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

    // 循环次数为字符串长度的一半, 每次都将{}删除
    private static Boolean isMatch3(String str) {
        int len = str.length();
        if (len % 2 == 1) {
            return false;
        }

        len /= 2;
        for (int i = 0; i < len; i++) {
            str = str.replace("{}", "");
            if (str.length() == 0) return true;
        }

        return str.length() == 0;
    }

    //
    private static Boolean isMatch2(String str) {
        System.out.println("str is :" + str);
        int len = str.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            stack.push(str.charAt(i));
        }

        int count = 0;
        for (; stack.size() > 0; ) {
            count = (stack.pop().charValue() == '}') ? ++count : --count;
            if (count < 0) {
                return false;
            }
        }

        return count == 0;
    }

    //
    private static Boolean isMatch(String str) {
        int len = str.length();
        Stack<Character> stack = new Stack<>();
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
