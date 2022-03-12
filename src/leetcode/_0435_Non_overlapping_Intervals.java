package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. Non-overlapping Intervals
 * Medium
 * #Array, #DP, #Greedy, #Sorting
 * 
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * 
 * Example 1:
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * 
 * Example 2:
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * 
 * Example 3:
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * 
 * Constraints:
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 */
public class _0435_Non_overlapping_Intervals {

    // [1,5], [2,3], [3,4], [4,5] -> [2,3], [3,4], [1,5], [4,5] (把 [1,5] 放到倒数第二)

    /**
     * 1. 排序
     * - 如果 end 不同, 按照 end 从小到大排列
     * - 如果 end 相同, 按照 start 从小到大排列
     * 
     * 2. 遍历
     * - 如果后者 start >= 前者 end, 说明无 overlap, 前指针指向后者, 后者++
     *    - 这里要注意, 前指针不能简单地 ++, 而应直接诺到后指针 (因为两者之间可能有需要删除的 interval)
     * - 否则说明有 overlap, ans++, 后者++
     * 
     * O(nlogn) + O(n)
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length < 2) { return 0; }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                // if (a[1] == b[1]) {
                //     return a[0] - b[0]; // 可以省略
                // }
                return a[1] - b[1];
            }
        });

        int ans = 0;
        int slow = 0, fast = 1;

        while (fast < intervals.length) {
            if (intervals[fast][0] >= intervals[slow][1]) {
                slow = fast;
            } else {
                ans++;
            }
            fast++;
        }

        return ans;
    }



    /**
     * 改进
     */
    public int eraseOverlapIntervals_2(int[][] intervals) {
        if (intervals == null || intervals.length < 2) { return 0; }

        Arrays.sort(intervals, (a, b) -> (a[1] - b[1])); // 改进在这里

        int ans = 0;
        int pre = 0;

        for (int i = 1; i < intervals.length; i++) { // 这部分没变, 只是改成用 for
            if (intervals[i][0] >= intervals[pre][1]) {
                pre = i;
            } else {
                ans++;
            }
        }


        return ans;
    }
    
}
