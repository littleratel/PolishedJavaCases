package cn.intv.leetcode.alg200;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 力扣 253， 会议室2
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时
 * 间 intervals[i] = [starti, endi] ，为避免会议冲突，同时要考虑充分利用会
 * 议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 * <p>
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 */
public class LeetCode253 {

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};

        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];

            if (cur[0] < minHeap.peek()) {
                minHeap.add(cur[1]);
            } else {
                minHeap.poll();
                minHeap.add(cur[1]);
            }
        }

        System.out.println(minHeap.size());
    }
}

