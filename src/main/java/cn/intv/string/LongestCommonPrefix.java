package cn.intv.string;

import org.junit.Test;

public class LongestCommonPrefix {

    @Test
    public void main() {
        String[] strs1 = {""};
        String[] strs2 = {"a"};
        String[] strs3 = {"flower", "flower", "flower"};
        String[] strs4 = {"flower", "flow", "flight"};
        String[] strs5 = {"dog", "racecar", "car"};
        String[] strs6 = {};
        System.out.println(longestCommonPrefix(strs1)); // ""
        System.out.println(longestCommonPrefix(strs2)); // a
        System.out.println(longestCommonPrefix(strs3)); //
        System.out.println(longestCommonPrefix(strs4));
        System.out.println(longestCommonPrefix(strs5));
        System.out.println(longestCommonPrefix(strs6));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }

        String str = strs[0];
        int len = str.length();
        char c;
        int i = 0;

        ok:
        for (; i < len; i++) {
            c = str.charAt(i);
            for (String s : strs) {
                if (s == "" || i >= s.length() || c != s.charAt(i)) {

                    break ok;
                }
            }
        }

        i--;

        if (i == -1) {
            return "";
        } else if (i == 0) {
            return "" + str.charAt(0);
        } else if (i == len - 1) {
            return str;
        } else {
            return str.substring(0, i + 1);
        }
    }
}