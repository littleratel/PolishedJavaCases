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

        int len = 10;
        Random seed = new Random();
        for (int i = 0; i < len; i++) {
            int val = seed.nextInt(100);
            minHeap.add(val);
            maxHeap.add(val);
        }

        System.out.println(minHeap);
        for (int i = 0; i < len; i++) {
            System.out.print(minHeap.poll() + " ");
        }

        System.out.println();
        System.out.println(maxHeap);
        for (int i = 0; i < len; i++) {
            System.out.print(maxHeap.poll() + " ");
        }
    }
}

