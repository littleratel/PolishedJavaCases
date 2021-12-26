package cn.intv.greedy;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升;
 * 有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * Output: 3
 *
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * Output: -1
 * */
public class CanCompleteCircuit {

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 1, 5, 4, 2};
        System.out.println(canCompleteCircuit(gas, cost));

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println(canCompleteCircuit(gas2, cost2));

        System.out.println(canCompleteCircuit(new int[]{1}, new int[]{2}));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0, totalSum = 0, index = 0;
        for (int i = 0; i < gas.length; i++) {
            totalSum += gas[i] - cost[i];
            curSum += gas[i] - cost[i];
            if (curSum < 0) {
                curSum = 0;
                index = i + 1;
            }
        }

        if (totalSum < 0)  return -1;

        return index;
    }
}
