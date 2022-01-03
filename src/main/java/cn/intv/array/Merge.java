package cn.intv.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 56 合并区间
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class Merge {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (arr1, arr2) -> {
            return arr1[0] - arr2[0];
        });

        List<int[]> res = new ArrayList<>();
        int[] tmp = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (tmp[1] >= intervals[i][1]) {

            } else if (tmp[1] >= intervals[i][0]) {
                tmp[1] = intervals[i][1];
            } else {
                res.add(tmp);
                tmp = new int[]{intervals[i][0], intervals[i][1]};
            }
        }

        res.add(tmp);

        return res.toArray(new int[res.size()][]);
    }
}
