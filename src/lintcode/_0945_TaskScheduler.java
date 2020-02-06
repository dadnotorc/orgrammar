/*
Medium
Queue, Greedy, Array
Facebook
 */
package lintcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 945. Task Scheduler
 *
 * Given a char array representing tasks CPU need to do. It contains capital
 * letters A to Z where different letters represent different tasks. Tasks
 * could be done without original order. Each task could be done in one
 * interval. For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between
 * two same tasks, there must be at least n intervals that CPU are doing
 * different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to
 * finish all the given tasks.
 *
 * Notice
 * - The number of tasks is in the range [1, 10000].
 * - The integer n is in the range [0, 100].
 *
 * Example1
 * Input: tasks = ['A','A','A','B','B','B'], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 * Example2
 * Input: tasks = ['A','A','A','B','B','B'], n = 1
 * Output: 6
 * Explanation:
 * A -> B -> A -> B -> A -> B.
 */
public class _0945_TaskScheduler {

    /* 解法2 - 九章参考  */
    /**
     * 1. 获得tasks出现次数
     * 2. 按出现数由少到多排序
     * 3. best case:  无需加入任何idle -> interval = tasks.length
     *    worst case: 所有tasks出现次数一样多 ->　(occurrences[max]-1) * (n+1) + 所有tasks个数 (或拥有max occurrences的个数)
     *    (occurrences[max]-1) -> max task最后一次被执行之前, 已被执行的次数
     *    (n+1) -> 每次max task repeat之前, 占用的intervals
     *    两者取多者
     *
     * 例1, ['A','A','A','B','B','B'], n = 2
     * A _ _ A _ _ A
     * (3 - 1) * (2 + 1) 意思是, 在最后的任务之前, 有(3-1)段intervals, 每段interval长度为(2+1)
     */
    public int leastInterval_2(char[] tasks, int n) {

        if (tasks == null || tasks.length <= 1)
            return 1;

        // 先统计各个task出现次数
        int[] counts = new int[26];
        for (char c : tasks)
            counts[c - 'A']++;

        Arrays.sort(counts); // 从少到多排列, 最后一项拥有 max count

        // 从后往前, 找到数组中有多少项也具有 max count
        // 这些项在填满间隔后(最后的任务时), 每项需要 +1
        int tasksWithMaxCount = 1; // 从1开始的原因是要包含最后一项
        int index = 24;

        while (index >= 0 && counts[index] == counts[25]) {
            index--;
            tasksWithMaxCount++;
        }

        return Math.max(tasks.length, (counts[25] - 1) * (n + 1) + tasksWithMaxCount);
    }


    /**
     * 假设 tasks 中之含有大写 A - Z
     *
     * 例1:
     * 3 + (3 - 1) * 2 + 1 (3个A + 每个A之间2个间隔 * 2 + (3个B - 之前的2段间隔))
     * 例2:
     * 3 + (3 - 1) * 1 + 1
     *
     * time:  O(nlogn) 因为用到sorting
     * space: O(1)
     */
    public int leastInterval(char[] tasks, int n) {

        if (tasks.length <= 1)
            return 1;

        // 先统计各个task出现次数
        int[] counts = new int[26];
        for (char c : tasks)
            counts[c - 'A']++;

        Arrays.sort(counts); // 从少到多排列

        int intervals = counts[25] - 1;
        int spaceToFill = intervals * n;

        int ans = counts[25] + spaceToFill;

        for (int i = 24; i >= 0; i--) {
            if (counts[i] == 0)
                break;

            if (spaceToFill > 0) {
                // 其他项数量 = 倒数第一项时, 填上非空的间隔, 最后 + 1
                // 其他项数量 = intervals,  填上非空的间隔
                ans += counts[i] > intervals ? 1 : 0;

                spaceToFill -= counts[i] > intervals ? intervals * 1 : counts[i]; // intervals * 1 表示每段间隔填入一个

            } else {
                ans += counts[i];
            }
        }

        return ans;
    }



    /* 解法3 ? */
    //todo 考虑使用 PriorityQueue

    @Test
    public void test0() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 0;
        org.junit.Assert.assertEquals(6, leastInterval(tasks, n));
        org.junit.Assert.assertEquals(6, leastInterval_2(tasks, n));
    }

    @Test
    public void test1() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        org.junit.Assert.assertEquals(8, leastInterval(tasks, n));
        org.junit.Assert.assertEquals(8, leastInterval_2(tasks, n));
    }

    @Test
    public void test2() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 1;
        org.junit.Assert.assertEquals(6, leastInterval(tasks, n));
        org.junit.Assert.assertEquals(6, leastInterval_2(tasks, n));
    }

    // A B C _ A B C _ A B C
    // 3 + 2 * 3 + (3 - 2) + (3 - 2)
    @Test
    public void test3() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C'};
        int n = 3;
        org.junit.Assert.assertEquals(11, leastInterval(tasks, n));
        org.junit.Assert.assertEquals(11, leastInterval_2(tasks, n));
    }

    // A B C A D E A F G Q W R T
    // 3 + 2 * 2 +
    @Test
    public void test4() {
        char[] tasks = {'A','A','A','B','C','D','E','F','G','Q','W','R','T'};
        int n = 2;
        org.junit.Assert.assertEquals(13, leastInterval(tasks, n));
        org.junit.Assert.assertEquals(13, leastInterval_2(tasks, n));
    }

}
