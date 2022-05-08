package cn.intv.graph;

import java.util.*;

/**
 * LeetCode 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1.
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出;
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi.
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1.
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false.
 * <p>
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 */
public class CourseTable {
    public static void main(String[] args) {
        System.out.println(canFinishBfs(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(canFinishBfs(2, new int[][]{{1, 0}}));
    }

    /**
     * 入度表（广度优先遍历）
     */
    public static boolean canFinishBfs(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());

        // Get the indegree and adjacency of every course.
        for (int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }

        // Get all the courses with the indegree of 0.
        for (int i = 0; i < numCourses; i++)
            if (indegrees[i] == 0) queue.add(i);

        // BFS TopSort.
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for (int cur : adjacency.get(pre))
                if (--indegrees[cur] == 0) queue.add(cur);
        }
        return numCourses == 0;
    }

    /**
     * 深度优先遍历
     * 原理是通过 DFS 判断图中是否有环。
     */
    public static boolean canFinishDfs(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        int[] flags = new int[numCourses];
        for (int[] cp : prerequisites)
            adjacency.get(cp[1]).add(cp[0]);
        for (int i = 0; i < numCourses; i++)
            if (!dfs(adjacency, flags, i)) return false;
        return true;
    }

    private static boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if (flags[i] == 1) return false;
        if (flags[i] == -1) return true;
        flags[i] = 1;
        for (Integer j : adjacency.get(i))
            if (!dfs(adjacency, flags, j)) return false;
        flags[i] = -1;
        return true;
    }
}
