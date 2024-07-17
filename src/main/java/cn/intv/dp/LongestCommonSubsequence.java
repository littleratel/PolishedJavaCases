package cn.intv.dp;

import org.junit.Test;

/**
 * 字符串的最长公共子序列
 * <p>
 * 示例：
 * text1 = "ABCD";
 * text2 = "ACDF";
 * 最长公共子序列是 “ACD”
 * <p>
 * dp[i][j]表示s1的前i个字符与s2的前j个字符的最长公共子序列的长度.
 */
public class LongestCommonSubsequence {

    @Test
    public void main() {
        String text1 = "ABCD";
        String text2 = "ACDF";
        System.out.println(longestCommonSubsequence(text1, text2));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) return 0;

        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
