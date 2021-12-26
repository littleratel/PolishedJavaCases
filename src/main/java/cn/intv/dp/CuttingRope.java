package cn.intv.dp;

/**
 * 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段(m、n都是整数，n>1并且m>1),
 * 每段绳子的长度记为 k[0],k[1]...k[m-1].
 * 求 k[0]*k[1]*...*k[m-1] 的最大积？
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * */
public class CuttingRope {
    public static void main(String[] args) {
//        System.out.println(cuttingRope_DP(10)); // 36
//        System.out.println(cuttingRope_DP(12)); // 81
        System.out.println(cuttingRope_DP(15)); // 243
        System.out.println(cuttingRope_DP(22)); // 2916

//        System.out.println(cuttingRope(10)); // 36
//        System.out.println(cuttingRope(12)); // 81
//        System.out.println(cuttingRope(15)); // 243
//        System.out.println(cuttingRope(22)); // 2916
    }

    private static int cuttingRope_DP(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; ++i) {
            dp[i] = Math.max(dp[i - 2] * 2, dp[i - 3] * 3);
        }

        return dp[n];
    }


    private static int cuttingRope(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        if (n == 5) return 6;
        if (n == 6) return 9;

        return 3 * cuttingRope(n - 3);
    }
}
