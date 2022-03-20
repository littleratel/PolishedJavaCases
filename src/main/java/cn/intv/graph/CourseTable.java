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
        System.out.println(canFinish(2, new int[][]{{1, 0}}));
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> indegreeMap = new HashMap<>();
        Map<Integer, List<Integer>> postsMap = new HashMap<>();

        // 初始化 入度表, 邻接表
        for (int i = 0; i < numCourses; i++) {
            indegreeMap.put(i, 0);
            postsMap.put(i, new LinkedList<>());
        }

        for (int[] temp : prerequisites) {
            int cur = temp[1];
            int post = temp[0];

            // 统计后继结点入度
            indegreeMap.put(post, indegreeMap.getOrDefault(post, 0) + 1);

            // 建立 邻接表
            postsMap.get(cur).add(post);

        }

        Deque<Integer> queue = new LinkedList<>();
        // 向队列中加入入度为0的结点
        for (Integer key : indegreeMap.keySet()) {
            if (indegreeMap.get(key) == 0) {
                queue.offer(key);
            }
        }

        // 出队
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            List<Integer> posts = postsMap.get(cur);

            for (Integer post : posts) {
                // 将后继结点入度减1
                indegreeMap.put(post, indegreeMap.get(post) - 1);
                // 将减1后入度为0，且存在后继的后继结点加入队列
                if (indegreeMap.get(post) == 0) {
                    queue.offer(post);
                }
            }
        }

        for (Integer key : indegreeMap.keySet()) {
            if (indegreeMap.get(key) > 0) {
                return false;
            }
        }

        return true;
    }
}
