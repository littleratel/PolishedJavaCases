package cn.intv.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
// 示如果要学习课程 ai 则 必须 先学习课程 bi 。
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回任意一种就可以;
// 如果不可能完成所有课程，返回 一个空数组。
public class Code210 {

    public static void main(String[] args) {
        Code210 tool = new Code210();
        int numCourses = 5;
        int[][] prerequisites = {{1, 4}, {2, 4}, {3, 1}, {3, 2}};
        int[] result = tool.findOrder(numCourses, prerequisites);
        for (int v : result) {
            System.out.print(v + " ");
        }
    }

    /**
     * 邻接矩阵 + BFS
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 1) return new int[1];

        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        int[] inDegrees = new int[numCourses];
        for (int[] info : prerequisites) { // 对于有先修课的课程，计算有几门先修课
            inDegrees[info[0]]++;
            adjacency.get(info[1]).add(info[0]);//统计邻节点
        }

        // 入度为0的节点队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) queue.add(i);
        }

        int count = 0;  // 记录可以学完的课程数量
        int[] res = new int[numCourses];  // 可以学完的课程

        // 根据提供的先修课列表，删除入度为 0 的节点
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            res[count++] = pre;   // 将可以学完的课程加入结果当中
            for (int cur : adjacency.get(pre)) {
                if (--inDegrees[cur] == 0) {
                    queue.add(cur);
                }
            }
        }

        if (count == numCourses) return res;

        return new int[0];
    }
}
