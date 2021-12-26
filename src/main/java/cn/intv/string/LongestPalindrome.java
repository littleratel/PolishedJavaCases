package cn.intv.string;

import org.junit.Test;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串;
 *
 * 输入：s = "babad"
 * 输出："bab
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 输入：s = "a"
 * 输出："a"
 *
 * 输入：s = "ac"
 * 输出："a"
 * */
public class LongestPalindrome {

    @Test
    public void main() {
        System.out.println(doSvc("afabbafb"));
        System.out.println(doSvc("babad"));
        System.out.println(doSvc("a"));
        System.out.println(doSvc("ac"));
    }

    // 中心扩散法
    // 时间复杂度：O(n^2), 空间复杂度：O(1)
    public String doSvc(String s) {
        if (s == null || s.length() < 1) {
            return "";
        } else if (s.length() == 1) {
            return s;
        }

        int maxLen = 1, index = 0, currentLen;
        int len_odd, len_even; // 分别拿到奇数偶数的回文子串长度
        for (int i = 0; i < s.length(); i++) {
            len_odd = expandCenter(s, i, i);
            len_even = expandCenter(s, i, i + 1);
            currentLen = Math.max(len_odd, len_even);

            if (currentLen > maxLen) {
                maxLen = currentLen;
                index = i;
            }
        }

        int start;
        if (maxLen % 2 == 0) { //
            start = index - maxLen / 2 + 1;
        } else {
            start = index - maxLen / 2;
        }

        return s.substring(start, start + maxLen);
    }

    private int expandCenter(String s, int left, int right) {
        // left = right，此时回文中心是1个字符，回文串的长度是奇数
        // right = left + 1，此时回文中心是2个字符，回文串的长度是偶数
        // 跳出循环的时候恰好满足 s.charAt(left) ！= s.charAt(right)
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 回文串的长度是 right-1-(left+1)+1 = right - left - 1
        return right - left - 1;
    }


    // Manacher
    // 时间复杂度：O(n), 空间复杂度：O(n)
    public String longestPalindromeManacher(String s) {


        return null;
    }


    // 动态规划
    public String byDynamicProgramming(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}