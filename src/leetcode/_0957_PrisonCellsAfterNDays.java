/*
Medium
#Hash Table
 */
package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 957. Prison Cells After N Days
 *
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 * - If a cell has two adjacent neighbors that are both occupied or both vacant,
 *   then the cell becomes occupied.
 * - Otherwise, it becomes vacant.
 *
 * (Note that because the prison is a row, the first and the last cells in the row
 * can't have two adjacent neighbors.)
 *
 * We describe the current state of the prison in the following way:
 * cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 *
 * Given the initial state of the prison, return the state of the prison after N days
 * (and N such changes described above.)
 *
 * Example 1:
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 * Note:
 * 1. cells.length == 8
 * 2. cells[i] is in {0, 1}
 * 3. 1 <= N <= 10^9
 */
public class _0957_PrisonCellsAfterNDays {

    /**
     * 在暴力解法基础上, 寻找数组循环的规律.
     * 因为数组首尾在一次循环后将为0, 剩余6个数字, 可为0或者1, 所以共有 2^6 种组合, 即64种组合.
     * 所以数组必定在某个时间会重复出现. 使用HashSet记录已见过的组合
     *
     * 易错点:
     * 1. HashSet中不用直接使用int[],
     *    因为两个int[]只有当 a==b 的时候才会被HashSet认为是重复的, 所以在这里无法起到保证唯一性的作用
     *    解决办法就是将int[]转成起来类型, 比如String或者ArrayList
     * 2. 循环的次数
     * 3. 找到循环后, break出来, 呼叫原方程, N变为 N % cycles
     */
    public int[] prisonAfterNDays(int[] cells, int N) {
        Set<String> set = new HashSet<>();
        boolean foundCycle = false;

        int[] res = cells;
        int cycles = 0;
        for (int i = 0; i < N; i++) {
            int[] tmp = getNextDay(res);
            String key = Arrays.toString(tmp);
            if (set.contains(key)) { // found the cycle
                foundCycle = true;
                break;
            }

            set.add(key);
            cycles++;
            res = tmp;
        }

        if (foundCycle) {
            return prisonAfterNDays(res, N % cycles);
        }
        return res;
    }

    private int[] getNextDay(int[] cells) {
        int[] tmp = new int[8];
        for (int j = 1; j < 7; j++) {
            tmp[j] = cells[j - 1] == cells[j + 1] ? 1 : 0;
        }
        return tmp;
    }






    /**
     * 还是暴力解法 - O(n)
     * 为了方便之后的improvement, 将之间的解法拆成两部分
     */
    public int[] prisonAfterNDays_TLE2(int[] cells, int N) {
        int[] res = cells;
        for (int i = 1; i <= N; i++) {
            res = helper_TLE2(res);
        }
        return res;
    }

    private int[] helper_TLE2(int[] cells) {
        int[] tmp = new int[8];
        for (int j = 1; j < 7; j++) {
            tmp[j] = cells[j - 1] == cells[j + 1] ? 1 : 0;
        }
        return tmp;
    }

    /**
     * 暴力解法 - O(n)
     * 使用example 2的input, 会TLE
     */
    public int[] prisonAfterNDays_TLE(int[] cells, int N) {
        int[] res = cells;
        for (int i = 1; i <= N; i++) {
            int[] tmp = new int[8];
            for (int j = 1; j < 7; j++) {
                tmp[j] = res[j - 1] == res[j + 1] ? 1 : 0;
            }
            res = tmp;
        }
        return res;
    }
}
