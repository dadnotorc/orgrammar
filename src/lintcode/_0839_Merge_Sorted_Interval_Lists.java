package lintcode;

import org.junit.Test;
import util.Interval;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 839. Merge Two Sorted Interval Lists
 * Easy
 * #Array, #Sorted Array, #Interval, #Two Pointers
 * Uber
 *
 * Merge two sorted (ascending) lists of interval and return it as a new sorted list.
 * The new sorted list should be made by splicing together the intervals of the two lists and sorted in ascending order.
 *
 * - The intervals in the given list do not overlap.
 * - The intervals in different lists may overlap.
 *
 * Example1
 * Input: list1 = [(1,2),(3,4)] and list2 = [(2,3),(5,6)]
 * Output: [(1,4),(5,6)]
 * Explanation:
 * (1,2),(2,3),(3,4) --> (1,4)
 * (5,6) --> (5,6)
 *
 * Example2
 * Input: list1 = [(1,2),(3,4)] and list2 = [(4,5),(6,7)]
 * Output: [(1,2),(3,5),(6,7)]
 * Explanation:
 * (1,2) --> (1,2)
 * (3,4),(4,5) --> (3,5)
 * (6,7) --> (6,7)
 */
public class _0839_Merge_Sorted_Interval_Lists {

    /**
     * 双指针, 分别指向 list1 和 list2 的当前下标
     * 设置两个 interval - prev, cur
     * 当两个指针均未遍历完时, 记录 start 较小者为 cur, 将其与 prev 合并
     * 将未完成遍历的指针继续
     * 最后别忘了将 prev 也要加入 ans
     *
     * 易错点:
     * - 在 combine 时, end 的值要取 Math.max(cur.end, prev.end))
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        if (list1 == null || list1.size() == 0) {
            return list2;
        }
        if (list2 == null || list2.size() == 0) {
            return list1;
        }

        List<Interval> ans = new ArrayList<>();
        int i = 0, j = 0;
        Interval prev = null, cur = null;

        while (i < list1.size() && j < list2.size()) {
            // examine the interval with the earlier start time, one at a time
            if (list1.get(i).start < list2.get(j).start) {
                cur = list1.get(i);
                i++;
            } else {
                cur = list2.get(j);
                j++;
            }

            prev = combine(ans, prev, cur);
        }

        while (i < list1.size()) {
            prev = combine(ans, prev, list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            prev = combine(ans, prev, list2.get(j));
            j++;
        }

        if (prev != null) {
            ans.add(prev);
        }

        return ans;
    }

    // 不能用 void, 好像无法传递 prev 的指针
    public Interval combine(List<Interval> list, Interval prev, Interval cur) {
        if (prev == null) {
            return cur;
        }

        if (prev.end >= cur.start) { // overlap
            return new Interval(prev.start, Math.max(cur.end, prev.end)); // 这里要小心, end 要取较大值
        } else {
            list.add(prev);
            return cur;
        }
    }



    @Test
    public void test1() {
        // test list input
        List<Interval> list1 = new ArrayList<>();
        list1.add(new Interval(1, 2));
        list1.add(new Interval(3, 4));
        List<Interval> list2 = new ArrayList<>();
        list2.add(new Interval(2, 3));
        list2.add(new Interval(5, 6));
        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 4));
        expected.add(new Interval(5, 6));
        List<Interval> actual = mergeTwoInterval(list1, list2);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).start, actual.get(0).start);
        assertEquals(expected.get(0).end, actual.get(0).end);
        assertEquals(expected.get(1).start, actual.get(1).start);
        assertEquals(expected.get(1).end, actual.get(1).end);
    }

    @Test
    public void test2() {
        // test list input
        List<Interval> list1 = new ArrayList<>();
        list1.add(new Interval(1, 2));
        list1.add(new Interval(3, 4));
        List<Interval> list2 = new ArrayList<>();
        list2.add(new Interval(4, 5));
        list2.add(new Interval(6, 7));
        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 2));
        expected.add(new Interval(3, 5));
        expected.add(new Interval(6, 7));
        List<Interval> actual = mergeTwoInterval(list1, list2);

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
        // test list input
        List<Interval> list1 = new ArrayList<>();
        list1.add(new Interval(1, 3));
        list1.add(new Interval(6, 8));
        list1.add(new Interval(10, 15));
        List<Interval> list2 = new ArrayList<>();
        list2.add(new Interval(2, 5));
        list2.add(new Interval(6, 7));
        list2.add(new Interval(12, 15));
        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 5));
        expected.add(new Interval(6, 8));
        expected.add(new Interval(10, 15));
        List<Interval> actual = mergeTwoInterval(list1, list2);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).start, actual.get(0).start);
        assertEquals(expected.get(0).end, actual.get(0).end);
        assertEquals(expected.get(1).start, actual.get(1).start);
        assertEquals(expected.get(1).end, actual.get(1).end);
        assertEquals(expected.get(2).start, actual.get(2).start);
        assertEquals(expected.get(2).end, actual.get(2).end);
    }
}
