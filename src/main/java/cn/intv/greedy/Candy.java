package cn.intv.greedy;

import java.util.Arrays;

/**
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 1. 每个孩子至少分配到 1 个糖果。
 * 2. 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
 *
 * 输入：[1,0,2]
 * 输出：5
 * 解释：你可以分别给这三个孩子分发 2、1、2 颗糖果
 *
 * 输入：[1,2,2]
 * 输出：4
 * 解释：你可以分别给这三个孩子分发 1、2、1 颗糖果
 * */
public class Candy {

    public static void main(String[] args) {
        System.out.println(candy(new int[]{1, 2, 2, 5, 4, 3, 2}));
        System.out.println(candy(new int[]{1, 2, 2}));
    }

    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];

        // 从前向后
        candies[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }

        // 从后向前
        int sum = candies[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }

            sum += candies[i];
        }

        return sum;
    }
}
