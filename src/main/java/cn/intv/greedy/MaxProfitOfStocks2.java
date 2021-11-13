package cn.intv.greedy;

/**
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格; 整数 fee 代表了交易股票的手续费用。
 * 可以无限次地完成交易，但是每笔交易都需要付手续费.
 * 如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 * 注意: 这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * Examples:
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 *
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 * */
public class MaxProfitOfStocks2 {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3));

//        System.out.println(maxProfit_Greedy(new int[]{1, 3, 2, 8, 4, 9}, 2)); //
//        System.out.println(maxProfit_Greedy(new int[]{1, 3, 7, 5, 10, 3}, 3)); //
    }

    private static int maxProfit(int[] prices, int fee) {
        int result = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
                continue;
            }

            // 卖则铁亏
            if (prices[i] <= minPrice + fee) {
                continue;
            }

            result += (prices[i] - fee) - minPrice; //

            // minPrice = prices[i + 1];
            // 收获利润的这一天并不是收获利润区间里的最后一天
            // 不是真正的卖出，后面要继续收获利润
            minPrice = prices[i] - fee; // 等于上一步 获得的利润
        }

        return result;
    }

    //
    public static int maxProfit_Greedy(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int sum = 0;
        for (int p : prices) {
            if (p + fee < buy) {
                buy = p + fee;
            } else if (p > buy) {
                sum += p - buy;
                buy = p;
            }
        }

        return sum;
    }

    // 动态规划
    public static int maxProfit_Dp(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        // bad case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }
}