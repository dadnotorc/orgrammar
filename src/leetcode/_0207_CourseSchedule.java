package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 207. Course Schedule
 * Medium
 * #DFS, #BFS, #Graph, #Topological Sort
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 * - For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 *
 * Return true if you can finish all courses. Otherwise, return false.
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
 * Constraints:
 * 1 <= numCourses <= 10^5
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 *
 *
 *  此题与lintcode 615. Course Schedule 相同
 */
public class _0207_CourseSchedule {

    /**
     * 1. get inDegree for all courses - 用 int 数组
     * 2. map preq course with following courses that can be unlocked - 用 hashmap
     * 3. use queue for courses which preq has been made
     *
     * time: O(2n + 2m), n = numCourses, m = prerequisites.length
     * space: O(n + m), n = inDegree.length, m = map的空间
     */
    public boolean canFinish_2(int numCourses, int[][] prerequisites) {

        // get inDegree for all courses
        int[] inDegree = new int[numCourses];

        // map preq course with following courses that can be unlocked
        Map<Integer, ArrayList<Integer>> courseMap = new HashMap<>();

        for (int[] preq : prerequisites) {
            inDegree[preq[0]]++; // 后续课程的入度

            if (!courseMap.containsKey(preq[1])) {
                courseMap.put(preq[1], new ArrayList<Integer>());
            }
            courseMap.get(preq[1]).add(preq[0]); // key = 前置课程, value = 前置课程所能解锁的后续课程列表
        }

        // use queue for courses which preq has been made
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

            // reduce the indegree of the following courses
            // and add the courses that has preq met to queue
            for (int i : courseMap.get(cur)) { // 解锁的后续课程
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    q.offer(i);
                }
            }
        }

        return false;
    }




    /**
     * DFS - 3ms
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

    public boolean canFinish_4(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];

        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (int[] preq : prerequisites) {
            courses[preq[0]].addPreq(courses[preq[1]]); // 这里注意addPreq的参数是course, 所以不能简单的传入preq[1]
        }

        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(courses[i])) {
                return false;
            }
        }

        return true;
    }

    // 这个helper func的作用是如果找到cyclic, 返回false, 否则将要拿的课程加入res, 并返回true
    private boolean isCyclic(Course course) {
        if (course.tested) { // 课程已修完, 无需继续
            return false;
        }
        if (course.visited) { // 找到了cyclic
            return true;
        }

        course.visited = true;
        for (Course c : course.preq) {
            if (isCyclic(c)) {
                return true;
            }
        }
        course.tested = true;
        return false;
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
