package cn.intv.greedy;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示, people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面有 ki 个身高 >= hi 的人.
 * 请输出格式化的数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人.
 *
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * */
public class ReconstructQueue {
    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        print(reconstructQueue(people));
    }

    private static int[][] reconstructQueue(int[][] people) {
        // 身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        print((people));

        /**
         * 当index相同时，后插入的元素靠前排，即插入顺序
         * que.add(0,2)
         * que.add(0,0)
         * que.add(0,1)
         *
         * 结果为:
         * (0,1) (1,0) (2,2)
         * */
        LinkedList<int[]> que = new LinkedList<>();
        for (int[] p : people) {
            que.add(p[1], p);
        }

        return que.toArray(new int[people.length][]);
    }

    private static void print(int[][] people) {
        int len = people.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append("{").append(people[i][0]).append(", ").append(people[i][1]).append("}, ");
        }
        System.out.println(sb.toString());
    }
}
