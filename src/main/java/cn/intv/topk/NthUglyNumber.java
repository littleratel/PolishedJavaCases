package cn.intv.topk;

/**
 *
 */
public class NthUglyNumber {

    public static void main(String[] args) {
        NthUglyNumber tool = new NthUglyNumber();
        System.out.println(tool.nthUglyNumber(5)); // 9
        System.out.println(tool.nthUglyNumber(7)); // 13
        System.out.println(tool.nthUglyNumber(9)); // 19
        System.out.println(tool.nthUglyNumber(12)); // 27
    }

    private int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        // 最开始三个指针p2,p3,p5都在第一个数(i=1)的位置上
        // 表示数2，3可以与该位置的值dp[i]相乘
        int p2 = 1, p3 = 1;
        for (int i = 2; i <= n; i++) {
            int tmp2 = dp[p2] * 2 + 1, tmp3 = dp[p3] * 3 + 1;
            // dp从1～n 为依次递增的序列
            dp[i] = Math.min(tmp2, tmp3);

            // 表示dp[i]取与2相乘的值
            if (dp[i] == tmp2) {

                p2++;
            }
            // 这里没有用else if，避免dp[]中出现连续相同的丑数
            if (dp[i] == tmp3) {

                p3++;
            }
        }

        return dp[n];
    }
}
