package cn.intv.greedy;

/**
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 *
 * 一开始你手头没有任何零钱。
 *
 * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 *
 *
 * */
public class LemonadeChange {

    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[]{5, 5, 5, 10, 20})); // true
        System.out.println(lemonadeChange(new int[]{5, 5, 10})); // true
        System.out.println(lemonadeChange(new int[]{5, 5, 10, 10, 20})); // false
        int[] bills = {5, 10, 5, 20, 5, 10, 5, 20, 5, 10, 5, 20, 5,
                10, 5, 20, 5, 10, 5, 20, 5, 10, 5, 20, 5, 10, 5, 20,
                5, 10, 5, 20, 5, 10, 5, 20, 5, 10, 5, 20};
        System.out.println(lemonadeChange(bills));
    }

    private static boolean lemonadeChange(int[] bills) {
        int cash5 = 0, cash10 = 0;

        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                cash5++;
            } else if (bills[i] == 10) {
                cash5--;
                cash10++;
            } else if (bills[i] == 20) {
                if (cash10 > 0) {
                    cash10--;
                    cash5--;
                } else {
                    cash5 -= 3;
                }
            }

            if (cash5 < 0 || cash10 < 0) return false;
        }

        return true;
    }
}