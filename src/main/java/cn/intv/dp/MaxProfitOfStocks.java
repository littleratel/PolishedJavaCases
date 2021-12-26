package cn.intv.dp;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格
 *
 * 只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票
 * 设计一个算法来计算你所能获取的最大利润,
 * 如果不能获取任何利润，返回 0
 * */
public class MaxProfitOfStocks {

    public static void main(String[] args) {
        MaxProfitOfStocks tool = new MaxProfitOfStocks();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(tool.maxProfit(prices));
    }

    private int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        return max;
    }
}