package cn.intv.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 *
 * 输入: s = ""
 * 输出: 0
 * */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring solution = new LengthOfLongestSubstring();
        System.out.println(solution.find("abcadebcabc"));
        System.out.println(solution.find("bbbbb"));
        System.out.println(solution.find("pwwkew"));
        System.out.println("<-------------------->");
        System.out.println(solution.find2("abcadebcabc"));
        System.out.println(solution.find2("bbbbb"));
        System.out.println(solution.find2("pwwkew"));
    }

    /**
     *
     *
     * */
    public int find(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) {
            return 0;
        }

        int[] position = new int[128];
        Arrays.fill(position, -1);

        int max = 0;
        int start = -1; // 窗口开始位置
        for (int index, i = 0; i < len; i++) {
            index = s.charAt(i);
            start = Math.max(start, position[index]);
            max = Math.max(max, i - start);
            position[index] = i;
        }
        return max;
    }

    public int find2(String s) {
        int n = s.length(), res = 0;
        Map<Character, Integer> indexMap = new HashMap<>();
        char c;
        for (int start = 0, end = 0; end < n; end++) {
            c = s.charAt(end);
            if (indexMap.containsKey(c)) {
                // +1 表示跳过原先重复的字符，滑动窗口内不能包含重复的字符
                start = Math.max(start, indexMap.get(c) + 1);
            }
            // end- start +1 表示窗口的长度
            res = Math.max(res, end - start + 1);
            indexMap.put(c, end);
        }

        return res;
    }

}