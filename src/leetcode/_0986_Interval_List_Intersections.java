package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. Interval List Intersections
 * Medium
 * #Two Pointers, #Interval
 *
 * You are given two lists of closed intervals, firstList and secondList,
 * where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are
 * either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 *
 * Example 1:
 * Input: secondList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 *
 * Example 2:
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 *
 * Constraints:
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 10^9
 * endi < starti+1
 * 0 <= startj < endj <= 10^9
 * endj < startj+1
 *
 */
public class _0986_Interval_List_Intersections {

    /*
    面试时, 确认 [24, 25] 和 [25, 26], 是否有交集 [25,25]

    双指针 分别指向 两个 array
    每次循环, 比较两个 array element, 获得 startMax 和 endMin

    如果 startMax <= endMin, 说明有交集 -> 加入答案队列
    否则, 说明无交集

    移动指针时, 移动 endMin 对应的那个 element, 因为它靠前
     */

    /**
     * 双指针 - 两组值较大的开始值 如果小于等于 较小的结束值, 则说明两者有交集
     * 比较结束后, 移动结束值较小者
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList == null || firstList.length == 0 || secondList == null || secondList.length == 0)
            return new int[0][0];

        List<int[]> ans = new ArrayList<>();

        int i = 0, j = 0;
        int startMax = 0, endMin = 0;

        while (i < firstList.length && j < secondList.length) {
            startMax = Math.max(firstList[i][0], secondList[j][0]);
            endMin = Math.min(firstList[i][1], secondList[j][1]);

            if (startMax <= endMin) { // 注意 例子中 [24,25] 和 [25,26] 的交集是 [25, 25]
                ans.add(new int[] {startMax, endMin});
            }

            if (firstList[i][1] == endMin) {
                i++;
            } else {
                j++;
            }
        }


        return ans.toArray(new int[0][2]);
        // return ans.toArray(new int[ans.size()][2]);
        // 两者都可以, 但是前者较快 https://shipilev.net/blog/2016/arrays-wisdom-ancients/#_conclusion
    }




    /**
     * 使用 双指针 + Interval类
     */
    public Interval[] intervalIntersection(Interval[] firstList, Interval[] secondList) {
        if (firstList == null || firstList.length == 0 || secondList == null || secondList.length == 0)
            return new Interval[0];

        List<Interval> ans = new ArrayList<>();

        int i = 0, j = 0;
        int startMax, endMin;

        while (i < firstList.length && j < secondList.length) {
            Interval intervalA = firstList[i];
            Interval intervalB = secondList[j];

            startMax = Math.max(intervalA.start, intervalB.start);
            endMin = Math.min(intervalA.end, intervalB.end);

            if (startMax <= endMin) {
                ans.add(new Interval(startMax, endMin));
            }

            if (intervalA.end == endMin) {
                i++;
            } else {
                j++;
            }
        }

        return ans.toArray(new Interval[0]);
        // return ans.toArray(new Interval[ans.size()]);
        // 两者都可以, 但是前者较快 https://shipilev.net/blog/2016/arrays-wisdom-ancients/#_conclusion
    }

    static class Interval {
        public int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }
}
