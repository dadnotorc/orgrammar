package lintcode;

import util.Interval;

import java.util.*;

/**
 * 920. Meeting Rooms
 * Easy
 * Uber
 *
 * Given an array of meeting time intervals consisting of start and end times
 *  [[s1,e1],[s2,e2],...] (si < ei),
 *  determine if a person could attend all meetings.
 *
 * Example1
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: false
 * Explanation: (0,30), (5,10) and (0,30),(15,20) will conflict
 *
 * Example2
 * Input: intervals = [(5,8),(9,15)]
 * Output: true
 * Explanation: Two times will not conflict
 */
public class _0920 {

    /**
     * @param intervals: a list of meeting time intervals
     * @return: if a person could attend all meetings
     *
     * time complexity:  O(nlogn) 排序 + O(n) 循环 = O(nlogn)
     * space complexity: O(1)
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Write your code here
        if (intervals == null || intervals.size() == 0) {
            return true;
        }

        // first, sort the input list by their start times
        // so meeting i always starts later than meeting i - 1
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        Iterator it = intervals.iterator();
        int latestEnd = ((Interval) it.next()).end;
        while (it.hasNext()) {
            Interval i = (Interval) it.next();
            if (i.start < latestEnd) {
                return false;
            }
            latestEnd = i.end;
        }

        return true;
    }

    // note the difference here is that the input is an array instead of a list
    public boolean canAttendMeetings(Interval[] intervals) {
        // Write your code here
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        // first, sort the input list by their start times
        // so meeting i always starts later than meeting i - 1
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        int latestEnd = intervals[0].end;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < latestEnd) {
                return false;
            }
            latestEnd = intervals[i].end;
        }

        return true;
    }


    // this will fail if there are lots of data to be put in the hashset
    public static boolean canAttendMeetings2(List<Interval> intervals) {
        // Write your code here
        HashSet<Integer> set = new HashSet<>();

        Iterator it = intervals.iterator();
        while (it.hasNext()) {
            Interval i = (Interval) it.next();
            int start = i.start;
            int end = i.end;

            if (set.contains(start) || set.contains(end)) {
                return false;
            } else {
                for (int j = start; j <= end; j++) {
                    set.add(j);
                }
            }
        }

        return true;
    }
}
