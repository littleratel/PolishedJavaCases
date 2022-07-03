package cn.intv.leetcode.alg200;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 力扣 253， 会议室2
 */
public class LeetCode253 {

    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        Random seed = new Random();
        for (int i = 0; i < 10; i++) {
            int val = seed.nextInt(100);
            minHeap.add(val);
            maxHeap.add(val);
        }

        System.out.println(minHeap);
        System.out.println(maxHeap);
    }
}

