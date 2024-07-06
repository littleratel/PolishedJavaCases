package cn.intv.window;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串 s ，找出不含重复字符的最长子串, 返回其长度.
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * */
public class LongestNonRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(doSvc("abcpabcbb"));
        System.out.println(doSvc("pwwkew"));
    }

    private static int doSvc(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) {
            return 0;
        }

        //
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }

        int max = 0;
        int start = 0;

        for (int index, i = 0; i < len; i++) {
            index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            max = Math.max(max, i - start + 1);
            last[index] = i;
        }

        return max;
    }

    // 滑动窗口, 其实就是一个队列
    private static int doSvc3(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, res = 0;
        while (right < s.length()) {
            while (set.contains(s.charAt(right)))
                set.remove(s.charAt(left++));

            set.add(s.charAt(right++));
            res = Math.max(res, right - left);
        }

        return res;
    }
}