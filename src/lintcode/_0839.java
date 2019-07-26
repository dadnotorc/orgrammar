package lintcode;

import util.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 839. Merge Two Sorted Interval Lists
 * Easy
 * Uber
 *
 * Merge two sorted (ascending) lists of interval and return it as a new sorted
 *  list. The new sorted list should be made by splicing together the intervals
 *  of the two lists and sorted in ascending order.
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
public class _0839 {



    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        // write your code here
        if (list1 == null || list1.size() == 0) {
            return list2;
        }
        if (list2 == null || list2.size() == 0) {
            return list1;
        }

        List<Interval> ans = new ArrayList<>();
        int i = 0, j = 0;
        Interval cur = null, last = null;
        while (i < list1.size() && j < list2.size()) {
            // examine the interval with the earlier start time
            // one at a time
            if (list1.get(i).start < list2.get(j).start) {
                cur = list1.get(i);
                i++;
            } else {
                cur = list2.get(j);
                j++;
            }

            last = combine(ans, last, cur);
        }

        while (i < list1.size()) {
            last = combine(ans, last, list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            last = combine(ans, last, list2.get(j));
            j++;
        }

        if (last != null) {
            ans.add(last);
        }

        return ans;
    }

    public Interval combine(List<Interval> list, Interval last, Interval cur) {
        if (last == null) {
            return cur;
        }

        if (cur.start <= last.end) { // overlap
            return new Interval(last.start, Math.max(cur.end, last.end));
        } else {
            list.add(last);
            return cur;
        }
    }
}
