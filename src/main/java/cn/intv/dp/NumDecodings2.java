package cn.intv.dp;


/**
 * 639. 解码方法 II
 * https://leetcode-cn.com/problems/decode-ways-ii/
 *
 * 给定一条包含数字和字符'*'的加密信息，字符'*'可以被当做1到9中任一数字，请确定解码方法的总数.<br/>
 * 解码规则:<br/>
 * 1 -> A
 * 2 -> B
 * 26 -> Z
 * 但是, 消息(1 11 06), "06"不能映射为 "F"
 *
 * 输入: "*"
 * 输出: 9
 * 解释: 加密的信息可以被解密为: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 *
 * 输入: "1*"
 * 输出: 9 + 9 = 18 <br/>
 * */
public class NumDecodings2 {

    public static void main(String[] args) {
        System.out.println(numDecodings2("*"));
        System.out.println(numDecodings2("1*"));
//        System.out.println(numDecodings("1111"));
//        System.out.println(numDecodings("11101110"));
    }

    /**
     * e0表示当前可以获得的解码的次数，当前数字可以为任意数
     * e1表示当前可以获得的解码的次数，当前数字为1
     * e2表示当前可以获得的解码的次数，当前数字为2
     *
     * f0, f1, f2分别为处理完当前字符c的e0, e1, e2的值
     * */
    private static int numDecodings(String s) {
        int e0 = 1, e1 = 0, e2 = 0, f0 = 0, M = (int) (1e9 + 7);
        for (char cnt : s.toCharArray()) {
            if (cnt == '*') {
                f0 = 9 * e0 + 9 * e1 + 6 * e2;
                e1 = e0;
                e2 = e0;
            } else {
                f0 = (cnt > '0' ? 1 : 0) * e0 + e1 + (cnt <= '6' ? 1 : 0) * e2;
                e1 = (cnt == '1' ? 1 : 0) * e0;
                e2 = (cnt == '2' ? 1 : 0) * e0;
            }

            e0 = f0 % M;
        }

        return e0;
    }

    /**
     * 三个变量滚动更新. 分两种情况，当前字符是*和不是*,
     * 从第一个字符到现在具有的解码数量=当前字符独立解码+当前字符与前一个字符一起解码的，
     * 然后再将当前字符与前一个字符一起解码的分为前一个是1或者是2.
     * */
    private static int numDecodings2(String s) {
        if (s.charAt(0) == '0')
            return 0;

        int a = 1, b = 1, cnt;
        if (s.charAt(0) == '*') {
            b = 9;
        }

        for (int i = 1; i < s.length(); i++) {
            cnt = 0;
            if (s.charAt(i) == '*') {
                cnt = b * 9; 
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '*') cnt += a * 9;
                if (s.charAt(i - 1) == '2' || s.charAt(i - 1) == '*') cnt += a * 6;
            } else {
                if (s.charAt(i) != '0') cnt = b;
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '*') cnt += a;
                if (s.charAt(i) >= '0' && s.charAt(i) <= '6' && (s.charAt(i - 1) == '2' || s.charAt(i - 1) == '*'))
                    cnt += a;
            }
            a = b;
            b = cnt;
        }

        return b;
    }
}
