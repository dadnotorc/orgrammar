package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Constraints:
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 */
public class _0056_MergeIntervals {

    /*
    检查是否 overlap 时
    情况1 - end > start  |  情况 2 - end = start  |  情况3 - end < start
    [_________]            [_____]                  [_____]
        [_____]                  [_____]                     [_____]

   情况 1 & 2: 更新区间1 的 end, 注意 这里要取两个区间 end 的较大者

   情况 3: 直接加入新的区间
     */

    /**
     * sort + linkedlist (无需 PQ)
     * 简化写法
     */
    public int[][] merge_2(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        LinkedList<int[]> list = new LinkedList<>(); // 也可以使用 ArrayList, 后面读取时用, list.get(list.size() - 1)
        list.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // 如果有 overlap 时, 更新最后一位的 end, 取两个区间 end 较大者
            // 如果无 overlap 时, 直接加入 list
            if (list.getLast()[1] >= intervals[i][0]) {
                list.getLast()[1] = Math.max(list.getLast()[1], intervals[i][1]); // 注意! 这里要取两者 end 的较大值
            } else { // 无 overlap
                list.add(intervals[i]);
            }
        }

        // 注意 写法, 返回 int[][],
        return list.toArray(new int[list.size()][2]);
    }




    /**
     * sort + priority queue
     *
     * sort 时, start 较小者放在前
     * pq 中, end 较大者放在前. 注意是 较大者, 不是较小者 (所以 PQ 中, 靠前的区间 都是数字较大者)
     * - 因为每次插入新的区间时, 都是跟区间靠后者 (比较)
     * - 这里跟 leetcode 1094. Car Pooling 不同的地方是, 因为 car pooling 中, 不是找overlap, 而且确认不overlap时, 会将较小者 poll 出来
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i2[1] - i1[1] ; // 注意 这里是较大者靠前
            }
        });

        for (int[] it : intervals) {
            if (!pq.isEmpty() && pq.peek()[1] >= it[0]) {
                // 注意 这里容易错, 不能简单的设为当前 interval 的 endi
                // 要取较大者
                pq.peek()[1] = Math.max(pq.peek()[1], it[1]);
            } else {
                pq.offer(it);
            }
        }

        // 因为 PQ 里, 数字较大者靠前, 等于时反向放置, 所以要翻着拿出来
        int size = pq.size();
        int[][] ans = new int[pq.size()][2];
        for (int i = size - 1; i >= 0; i--) {
            ans[i] = pq.poll();
        }

        return ans;
    }
}
