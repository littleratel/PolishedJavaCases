package cn.intv.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

// 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
// 示如果要学习课程 ai 则 必须 先学习课程 bi 。
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false
public class Code207 {

    public static void main(String[] args) {
        Code207 tool = new Code207();
        int numCourses = 5;
        int[][] prerequisites = {{1, 4}, {2, 4}, {3, 1}, {3, 2}};
        System.out.println(tool.canFinish(numCourses, prerequisites));
    }

    /**
     * 邻接矩阵
     * <p>
     * 本题可约化为：是否是有向、无环图(DAG)。
     * 原理是通过 DFS 判断图中是否有环;
     * 即课程间规定了前置条件，但不能构成任何环路，否则课程前置条件将不成立。
     * <p>
     * 时间复杂度 O(N + M)： 遍历一个图需要访问所有节点和所有临边，N和 M分别为节点数量和临边数量；
     * 空间复杂度 O(N + M)： 为建立邻接表所需额外空间，adjacency 长度为 NN ，并存储 MM 条临边的数据。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        int[] visited = new int[numCourses];
        for (int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency, visited, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(List<List<Integer>> adjacency, int[] visited, int i) {
        // 未被访问: i == 0
        // 由0未被访问 -> 变为1正在被访问
        // 由1正在被访问 -> -1 已被访问过

        // flag[i] == 1，说明在本轮搜索中,节点i被第2次访问，即课程安排图有环，直接返回False。
        if (visited[i] == 1) return false;
        if (visited[i] == -1) return true;

        visited[i] = 1;
        for (Integer j : adjacency.get(i)) {
            if (!dfs(adjacency, visited, j)) {
                return false;
            }
        }
        visited[i] = -1;

        return true;
    }
}
