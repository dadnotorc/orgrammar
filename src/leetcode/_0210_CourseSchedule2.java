/*
Medium
#DFS, #BFS, #Graph, #Topological Sort
 */
package leetcode;

import java.util.*;

/**
 * 210. Course Schedule II
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0
 * you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 *
 * Example 2:
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 *
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class _0210_CourseSchedule2 {

    /**
     * DFS
     * 使用 OOD 记录每个课程的属性
     */
    class Course{
        int num;
        boolean visited; // 课程是否有修过
        boolean tested; // 是否检查过包含当前课程的cyclic (检查过无cyclic->true, 还未检查过->false)
        List<Course> preq = new ArrayList<Course>();

        public Course(int num) {
            this.num = num;
        }

        public void addPreq(Course course) {
            preq.add(course);
        }
    }

    int index = 0; // the index of the courses to be taken

    public int[] findOrder_DFS(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];

        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (int[] preq : prerequisites) {
            courses[preq[0]].addPreq(courses[preq[1]]); // 这里注意addPreq的参数是course, 所以不能简单的传入preq[1]
        }

        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(courses[i], res)) {
                return new int[0];
            }
        }

        return res;
    }

    // 这个helper func的作用是如果找到cyclic, 返回false, 否则将要拿的课程加入res, 并返回true
    private boolean isCyclic(Course course, int[] res) {
        if (course.tested) { // 课程已修完, 无需继续
            return false;
        }
        if (course.visited) { // 找到了cyclic
            return true;
        }

        course.visited = true;
        for (Course c : course.preq) {
            if (isCyclic(c, res)) {
                return true;
            }
        }
        course.tested = true;
        res[index++] = course.num;
        return false;
    }



    

    /**
     * BFS
     */
    public int[] findOrder_BFS(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        if (numCourses == 0) { return res; }
        int index = 0; // // the index of the courses to be taken

        Map<Integer, ArrayList<Integer>> courseMap = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i< numCourses; i++) {
            courseMap.put(i, new ArrayList<>());
        }
        for (int[] preq : prerequisites) {
            inDegree[preq[0]]++; // 后续课程的入度
            courseMap.get(preq[1]).add(preq[0]); // key = 前置课程, value = 前置课程所能解锁的后续课程列表
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i); // 将入度为0的课程加入队列
                res[index++] = i; // 也将其加入res
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (index == numCourses) {
                return res;
            }
            for (int i : courseMap.get(cur)) { // 解锁的后续课程
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    q.offer(i);
                    res[index++] = i;
                }
            }
        }

        return (index == numCourses) ? res : new int[0];
    }
}
