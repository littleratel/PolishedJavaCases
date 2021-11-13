package cn.intv.greedy;

import java.util.Arrays;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠.
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * */
public class EraseOverlapIntervals {
    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
    }

    public static int eraseOverlapIntervals(final int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int remove = 0;
        int pre = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (pre > intervals[i][0]) {
                remove++;
                pre = Math.min(pre, intervals[i][1]);
            } else {
                pre = intervals[i][1];
            }
        }

        return remove;
    }
}
