package cn.intv.greedy;

/**
 * 假设你有一个数组，其中第 i 个元素是股票在第i天的价格;
 * 你可以买入一次股票和卖出一次股票, 且总共只能买入和卖出各一次，求最大的收益.
 * 要求:
 * 空间复杂度O(1) ，时间复杂度O(n)
 *
 * 输入: [1,4,2]
 * 返回值: 3
 *
 * 输入: [2,4,1]
 * 返回值: 2
 * */
public class MaxProfitOfStocks3 {

    public static void main(String[] args) {
        System.out.println(doSvc(new int[]{3, 4, 2, 4, 1}));
        System.out.println(doSvc(new int[]{2, 1}));
    }

    private static int doSvc(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int min = prices[0], max = 0, tmp;
        for (int i = 1; i < prices.length; ++i) {
            if (min > prices[i]) {
                min = prices[i];
            } else if (max < (tmp = prices[i] - min)) {
                max = tmp;
            }
        }

        return max;
    }
}