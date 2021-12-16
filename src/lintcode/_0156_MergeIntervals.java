/*
Easy
#Sort, #Sweep Line
LinkedIn, Google, Facebook, Microsoft, Twitter
 */
package lintcode;

import org.junit.Test;
import util.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 156. Merge Intervals
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 * Input: [(1,3)]
 * Output: [(1,3)]
 *
 * Example 2:
 * Input:  [(1,3),(2,6),(8,10),(15,18)]
 * Output: [(1,6),(8,10),(15,18)]
 *
 * Challenge
 * - O(n log n) time and O(1) extra space.
 *
 * time O(nlogn) -> 先sort, 后遍历并合并
 */
public class _0156_MergeIntervals {

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ans = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return ans;
        }

        // start较早点的排在前面
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval it1, Interval it2) {
                return it1.start - it2.start;
            }
        });

        ans.add(intervals.get(0));
        int ansIndex = 0;

        for (int i = 1; i < intervals.size(); i++) {
            // 看清楚了, new是intervals上的. cur是ans上的
            Interval newUnmerged = intervals.get(i);
            Interval curMerged = ans.get(ansIndex);

            // 如果当前 i 的开头早于 ans end 指针, 表示有overlap -> ans 中 start 不用修改, end 取两者的较大者 (较晚结束者)
            if (newUnmerged.start <= curMerged.end) {
                // 这里要特别小心, 一定要选两者最大值
                curMerged.end = Math.max(newUnmerged.end, curMerged.end);
                // 这里不能递增 ansIndex
            } else {
                // 没有 overlap, 直接插入
                ans.add(intervals.get(i));
                ansIndex++;
            }
        }

        return ans;
    }

    @Test
    public void test1() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 3));
        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 3));
        List<Interval> actual = merge(list);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).start, actual.get(0).start);
        assertEquals(expected.get(0).end, actual.get(0).end);
    }

    @Test
    public void test2() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 3));
        list.add(new Interval(2, 6));
        list.add(new Interval(8, 10));
        list.add(new Interval(15, 18));
        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 6));
        expected.add(new Interval(8, 10));
        expected.add(new Interval(15, 18));
        List<Interval> actual = merge(list);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).start, actual.get(0).start);
        assertEquals(expected.get(0).end, actual.get(0).end);
        assertEquals(expected.get(1).start, actual.get(1).start);
        assertEquals(expected.get(1).end, actual.get(1).end);
        assertEquals(expected.get(2).start, actual.get(2).start);
        assertEquals(expected.get(2).end, actual.get(2).end);
    }

    @Test
    public void test3() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(2, 3));
        list.add(new Interval(4, 5));
        list.add(new Interval(6, 7));
        list.add(new Interval(8, 9));
        list.add(new Interval(1, 10));
        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 10));
        List<Interval> actual = merge(list);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).start, actual.get(0).start);
        assertEquals(expected.get(0).end, actual.get(0).end);
    }
}
