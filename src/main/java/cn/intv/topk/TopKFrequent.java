package cn.intv.topk;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。
 * 你可以按 任意顺序 返回答案。
 */
public class TopKFrequent {

    public static void main(String[] args) {
        TopKFrequent tool = new TopKFrequent();
        int[] nums = new int[]{1, 1, 1, 1, 2, 2, 2, 5};
        int[] res = tool.topKFrequent(nums, 2);
        for (int item : res) {
            System.out.print(item + " ");
        }
    }

    private int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int item : nums) {
            map.merge(item, 1, Integer::sum);
        }

        PriorityQueue<Pair<Integer, Integer>> pairs = new PriorityQueue<>(Comparator.comparingInt(Pair::getValue));
        map.forEach((key, v) -> {
            pairs.add(new Pair<>(key, v));
            if (pairs.size() > k) {
                pairs.poll();
            }
        });


        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pairs.poll().getKey();
        }

        return res;
    }
}
