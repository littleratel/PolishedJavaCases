package cn.intv.greedy;

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
        System.out.println(tool.maxProfitMultiple(prices));
    }

    /**
     * 贪心算法
     * 在一次遍历中，只要今天价格大于昨天价格，就在昨天买入今天卖出;
     * 时间复杂度O(n)
     * */
    public int maxProfitMultiple(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            // 收集每天利润为正的股票
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }

        return res;
    }
}