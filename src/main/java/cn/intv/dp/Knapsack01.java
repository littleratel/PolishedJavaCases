package cn.intv.dp;

/**
 * 0-1背包问题
 * 每件物品重量weight, 价值price, pkgTotalWeight包能放下物品总重量；
 * 求能放如的物品的总价值最大。
 * <p>
 * w[i]: 第i个物体的重量；
 * p[i]: 第i个物体的价值；
 * dp[i][m]: 前i个物体放入容量为m的背包的最大价值；
 * dp[i-1][m]: 前i-1个物体放入容量为m的背包的最大价值；
 * dp[i-1][m-w[i]]: 前i-1个物体放入容量为m-w[i]的背包的最大价值；
 * <p>
 * 状态转移方程:
 * dp[i][m]=max{dp[i-1][m], dp[i-1][m-w[i]]+p[i]}
 * <p>
 * 1)不放第i件物品，所以dp[i][j] = dp[i-1][j]的值
 * 2)放第i件物品，所以dp[i][j] = dp[i-1][j-w[i-1]] + p[i-1]
 */
public class Knapsack01 {

    public static void main(String[] args) {
//        int[] weight = {0, 6, 1, 5, 2, 1};
//        int[] price = {0, 48, 7, 40, 12, 8};
//        int pkgTotalWeight = 11;

        int[] weight = {1, 3, 4};
        int[] price = {15, 20, 30};
        int pkgTotalWeight = 4;

        Knapsack01 tool = new Knapsack01();
        tool.twoDpArray(weight, price, pkgTotalWeight);
        tool.oneDpArray(weight, price, pkgTotalWeight);
    }

    private void twoDpArray(int[] weight, int[] price, int pkgTotalWeight) {
        int dp[][] = new int[weight.length][pkgTotalWeight + 1];

        // 初始化
        for (int j = weight[0]; j <= pkgTotalWeight; j++) {
            dp[0][j] = price[0];
        }

        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j <= pkgTotalWeight; j++) {
                if (j < weight[i])  // 无法放下当前物品weight[i]
                    dp[i][j] = dp[i - 1][j];
                else // 可以放下，但是需要比较<放或者不放时>的最大价值
                    dp[i][j] = Math.max(dp[i - 1][j], price[i] + dp[i - 1][j - weight[i]]);

                System.out.print(String.format("%d ", dp[i][j]));
            }
            System.out.println();
        }

        System.out.println("Result: " + dp[weight.length - 1][pkgTotalWeight]);
    }

    private void oneDpArray(int[] weight, int[] price, int pkgTotalWeight) {
        int dp[] = new int[pkgTotalWeight + 1];

        for (int i = 0; i < weight.length; i++) { // 遍历物品
            for (int j = pkgTotalWeight; j >= weight[i]; j--) { // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + price[i]);
                System.out.print(String.format("%d ", dp[j]));
            }
            System.out.println();
        }

        System.out.println("Result: " + dp[pkgTotalWeight]);
    }
}



