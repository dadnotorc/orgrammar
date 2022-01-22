package interviews;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Merge Intervals
 *
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
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 * leetcode 56. Merge Intervals
 */
public class Bloomberg_MergeIntervals {

    /**
     * sort + linkedlist (无需 PQ)
     * 简化写法
     */
    public int[][] merge_2(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        LinkedList<int[]> merged = new LinkedList<>();

        for (int[] it : intervals) {
            if (!merged.isEmpty() && merged.getLast()[1] >= it[0]) { // 有 overlap 时, end 取 两个区间的较大者
                merged.getLast()[1] = Math.max(merged.getLast()[1], it[1]);
            } else { // 无 overlap
                merged.add(it);
            }
        }

        // 注意 写法
        return merged.toArray(new int[merged.size()][2]);
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




    @Test
    public void test1() {
        int[][] intervals = {
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        };

        int[][] exp = {
                {1,6},
                {8,10},
                {15,18}
        };

        int[][] output = merge(intervals);

        Assert.assertEquals(exp.length, output.length);
        for (int i = 0; i < exp.length; i++) {
            Assert.assertArrayEquals(exp[i], output[i]);
        }
    }

    @Test
    public void test2() {
        int[][] intervals = {
                {1,7},
                {2,6},
                {8,10},
                {15,18}
        };

        int[][] exp = {
                {1,7},
                {8,10},
                {15,18}
        };

        int[][] output = merge(intervals);

        Assert.assertEquals(exp.length, output.length);
        for (int i = 0; i < exp.length; i++) {
            Assert.assertArrayEquals(exp[i], output[i]);
        }
    }


    @Test
    public void test3() {
        int[][] intervals = {
                {1,7},
                {7,10}
        };

        int[][] exp = {
                {1,10}
        };

        int[][] output = merge(intervals);

        Assert.assertEquals(exp.length, output.length);
        for (int i = 0; i < exp.length; i++) {
            Assert.assertArrayEquals(exp[i], output[i]);
        }
    }

    @Test
    public void test4() {
        int[][] intervals = {
                {2,3},
                {2,2},
                {3,3},
                {1,3},
                {5,7},
                {2,2},
                {4,6}
        };

        int[][] exp = {
                {1,3},
                {4,7}
        };

        int[][] output = merge(intervals);

        Assert.assertEquals(exp.length, output.length);
        for (int i = 0; i < exp.length; i++) {
            Assert.assertArrayEquals(exp[i], output[i]);
        }
    }
}
