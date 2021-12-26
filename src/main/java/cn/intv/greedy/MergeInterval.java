package cn.intv.greedy;

import util.PrintUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * intervals 表示若干个区间的集合，其中 intervals[i] = [starti, endi];
 * 合并所有重叠的区间，返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * */
public class MergeInterval {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        PrintUtil.twoDimensionalArray(merge(intervals));
    }

    private static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        PrintUtil.twoDimensionalArray(intervals);

        List<int[]> res = new LinkedList<>();
        int start = intervals[0][0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > intervals[i - 1][1]) {
                res.add(new int[]{start, intervals[i - 1][1]});
                start = intervals[i][0];
            } else {
                intervals[i][1] = Math.max(intervals[i][1], intervals[i - 1][1]);
            }
        }
        //
        res.add(new int[]{start, intervals[intervals.length - 1][1]});

        return res.toArray(new int[res.size()][]);
    }
}
