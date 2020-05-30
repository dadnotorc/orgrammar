/*
Medium
#DFS, #BFS, #Graph, #Topological Sort
 */
package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 207. Course Schedule
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example:
 * to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs,
 * is it possible for you to finish all courses?
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 * - The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 *   https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs
 * - You may assume that there are no duplicate edges in the input prerequisites.
 * - 1 <= numCourses <= 10^5
 *
 *
 * 此题与lintcode 615. Course Schedule 相同
 */
public class _0207_CourseSchedule {

    /**
     * BFS - 4ms
     * 之前的map中 key = 课程, value = 必修的前置课程的列表
     * 这里 key = 前置课程, value = 前置课程所能解锁的后续课程列表
     * 需要新加入int[]记录indegree
     *
     * time: O(2n + 2m), n = numCourses, m = prerequisites.length
     * space: O(n + m), n = inDegree.length, m = map的空间
     */
    public boolean canFinish_2(int numCourses, int[][] prerequisites) {

        Map<Integer, ArrayList<Integer>> courseMap = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courseMap.put(i, new ArrayList<Integer>());
        }
        for (int[] preq : prerequisites) {
            inDegree[preq[0]]++; // 后续课程的入度
            courseMap.get(preq[1]).add(preq[0]); // key = 前置课程, value = 前置课程所能解锁的后续课程列表
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) { // 将入度为0的课程加入队列
                q.offer(i);
            }
        }

        // 注意, 这里不能用numCourses--, 因为之后的for循环中需要用到原始的numCourses值
        int coursesTaken = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            coursesTaken++;
            if (coursesTaken == numCourses) {
                return true;
            }
            for (int i : courseMap.get(cur)) { // 解锁的后续课程
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    q.offer(i);
                }
            }
        }

        return coursesTaken == numCourses;
    }


    /**
     * DFS - 105ms
     * 判断是否有环, 重复访问已学过的课程
     *
     * 易错点:
     * 1. DFS中别忘了回溯
     */
    public boolean canFinish_3(int numCourses, int[][] prerequisites) {

        Map<Integer, ArrayList<Integer>> courseMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            courseMap.put(i, new ArrayList<Integer>());
        }
        for (int[] preq : prerequisites) {
            courseMap.get(preq[1]).add(preq[0]); // key = 前置课程, value = 前置课程所能解锁的后续课程列表
        }

        boolean[] courseTaken = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!helper(courseMap, courseTaken, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean helper(Map<Integer, ArrayList<Integer>> courseMap, boolean[] courseTaken, int course) {
        if (courseTaken[course]) { // 进入了环, 例如{{0,1},{1,0}}
            return false;
        }

        courseTaken[course] = true;

        for (int i : courseMap.get(course)) { // 解锁的后续课程
            if (!helper(courseMap, courseTaken, i)) {
                return false;
            }
        }

        courseTaken[course] = false; // 别忘了回溯
        return true;
    }





    /**
     * BFS - 218ms 很慢
     * map中, key = 课程, value = 必修的前置课程的列表
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // key = 课程, value = 必修的前置课程的列表
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for (int[] preq : prerequisites) {
            map.get(preq[0]).add(preq[1]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int key : map.keySet()) {
            if (map.get(key).size() == 0) {
                q.offer(key);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            numCourses--;
            if (numCourses == 0) {
                return true;
            }
            for (int key : map.keySet()) {
                if (map.get(key).contains(cur)) {
                    // 注意! 这里不能使用 map.get(key).remove(cur);
                    // 因为如此移除的是cur下标所指的数值, 而不是cur这个值
                    map.get(key).remove(new Integer(cur));
                    if (map.get(key).size() == 0) {
                        q.offer(key);
                    }
                }
            }
        }

        return numCourses == 0;
    }

    @Test
    public void test1() {
        int[][] preq = {{1,0}};
        org.junit.Assert.assertTrue(canFinish_2(2, preq));
    }
}
