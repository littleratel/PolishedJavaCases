package cn.intv.dp;


/**
 * 91. 解码方法
 * https://leetcode-cn.com/problems/decode-ways/
 *
 * 给你一个只含数字的非空字符串s，计算并返回 解码 方法的 总数:<br/>
 * 解码规则:<br/>
 * 1 -> A
 * 2 -> B
 * 26 -> Z
 * 但是, 消息(1 11 06), "06"不能映射为 "F"
 *
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6)<br/>
 *
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）<br/>
 * */
public class NumDecodings {

    public static void main(String[] args) {
//        System.out.println(funcWithDp("1111"));
//        System.out.println(funcWithDp("1100"));
//        System.out.println(funcWithDp("11101110"));

//        System.out.println(numDecodings("0"));
//        System.out.println(numDecodings("01"));
//        System.out.println(numDecodings("10"));
        System.out.println(numDecodings("110011"));
        System.out.println(numDecodings("1111"));
        System.out.println(numDecodings("11101110"));
    }

    /**
     * s.charAt(i] = 0, f(i) = 0
     * s.charAt(i] != 0, f(i) = f(i+1) + f(i+2)
     * 时间复杂度: O(N)
     * 空间复杂度: O(N)
     * */
    private static int funcWithDp(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];
                if (i + 2 <= len && (s.charAt(i) - '0') * 10 + s.charAt(i + 1) - '0' <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }

        return dp[0];
    }

    private static int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            /**
             * 如果 s.charAt(i-1) != '0', 那么i-1位可以单独解码,
             * 所以dp[i]跟 dp[i-1] 和 dp[i-2] 有关;
             *
             * 如果 s.charAt(i-1) = '0', 那么i-1位只能与i-2位组合解码，i-1位不能单独解码，
             * 所以dp[i]的值只跟 dp[i-2]有关;
             * */
            if (s.charAt(i - 1) != '0') dp[i] = dp[i - 1];

            if (i >= 2) {
                int num = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
                // num在[10.26]之间, 才能与  dp[i - 2] 扯上关系
                if (num >= 10 && num <= 26) dp[i] += dp[i - 2];
            }

            if (dp[i] == 0) {
                return 0;
            }
        }

        return dp[len];
    }

}
