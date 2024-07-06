package cn.intv.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi]
// 表示如果要学习课程 ai 则 必须 先学习课程 bi。
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false
public class Code207 {

    public static void main(String[] args) {
        Code207 tool = new Code207();
        int numCourses = 5;
        int[][] prerequisites = {{1, 4}, {2, 4}, {3, 1}, {3, 2}};
        System.out.println(tool.canFinishBfs(numCourses, prerequisites));
    }

    /**
     * 深度优先遍历
     * <p>
     * 本题可约化为：是否是有向、无环图(DAG)。
     * 原理是通过 DFS 判断图中是否有环;
     * 即课程间规定了前置条件，但不能构成任何环路，否则课程前置条件将不成立。
     * <p>
     * 时间复杂度 O(N + M)： 遍历一个图需要访问所有节点和所有临边，N和 M分别为节点数量和临边数量；
     * 空间复杂度 O(N + M)： 为建立邻接表所需额外空间，adjacency 长度为 NN ，并存储 MM 条临边的数据。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 2) {
            return true;
        }

        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        for (int[] vertex : prerequisites) {
            adjacency.get(vertex[1]).add(vertex[0]);
        }

        int[] vertexVisited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency, vertexVisited, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(List<List<Integer>> adjacency, int[] vertexVisited, int i) {
        // 未被访问: i == 0
        // 由0未被访问 -> 变为1正在被访问
        // 由1正在被访问 -> -1 已被访问过

        // flag[i] == 1，说明在本轮搜索中,节点i被第2次访问，即课程安排图有环，直接返回False。
        if (vertexVisited[i] == 1) return false;
        if (vertexVisited[i] == -1) return true;

        vertexVisited[i] = 1;
        for (Integer j : adjacency.get(i)) {
            if (!dfs(adjacency, vertexVisited, j)) {
                return false;
            }
        }
        vertexVisited[i] = -1;

        return true;
    }

    /**
     * 入度表（广度优先遍历）
     */
    public boolean canFinishBfs(int numCourses, int[][] prerequisites) {
        //建立邻接表
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        //建立入度表(每门课程的入度)
        int[] inDegree = new int[numCourses];
        //统计有向图中节点的邻节点
        for (int[] info : prerequisites) {
            adjacency.get(info[1]).add(info[0]);//统计邻节点
            inDegree[info[0]]++;//统计入度
        }

        // 从入度为0的节点开始，沿着后继节点向后遍历，所以需要将入度为0的节点入队
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        //BFS
        while (!queue.isEmpty()) {
            //每出队一个课程，课程数目减一
            //如果为有向无环图，则每个节点都会入队并出队，使课程数减为0
            int pre = queue.poll();
            numCourses--;
            for (int cur : adjacency.get(pre)) {
                //如果邻节点(后继节点)入度减一后为0，说明cur所有的前驱节点已经被 “删除”，此时将 cur 入队
                if (--inDegree[cur] == 0) {
                    queue.add(cur);
                }
            }
        }

        return numCourses == 0;
    }

}
