package cn.intv.leetcode.hot100;

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
        System.out.println(tool.findOrder(numCourses, prerequisites));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {


        return null;
    }
}
