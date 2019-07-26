package lintcode;

import util.Interval;

import java.util.*;

/**
 * 919. Meeting Rooms II
 * Medium
 * Facebook, Snapchat, Uber, Google
 *
 * Given an array of meeting time intervals consisting of start and end times
 *  [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference
 *  rooms required.
 *
 * Example1
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: 2
 * Explanation:
 * We need two meeting rooms
 * room1: (0,30)
 * room2: (5,10),(15,20)
 *
 * Example2
 * Input: intervals = [(2,7)]
 * Output: 1
 * Explanation:
 * Only need one meeting room
 */
public class _0919 {

    /**
     * sort first by starting time
     * store meetings in hash table, its value is the end time
     * for new meeting, if its start time > H.T entry's end time -> use the room
     *  otherwise, create a new room
     *
     * time complexity:  O(nlogn) sorting
     * space complextiy: O(n)
     */
    public int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval t1, Interval t2) {
                return t1.start - t2.start;
            }
        });

        Hashtable<Integer, Integer> table = new Hashtable<>();
        int roomNum = 1;
        for (Interval t : intervals) {
            if (table.size() == 0) {
                table.put(roomNum, t.end);
            } else {
                int availRoom = findAvailableRoom(table, t);
                if (!table.containsKey(availRoom)) {
                    // add new room
                    roomNum++;
                    table.put(roomNum, t.end);
                } else {
                    table.put(availRoom, t.end);
                }
            }
        }

        return roomNum;
    }

    public int findAvailableRoom(Hashtable<Integer, Integer> table, Interval t) {
        Enumeration e = table.keys();

        while (e.hasMoreElements()) {
            Integer curRoom = (Integer) e.nextElement();
            int curRoomEndTime = table.get(curRoom);
            if (curRoomEndTime <= t.start) {
                return curRoom;
            }
        }

        return -1;
    }

    /* 使用PriorityQueue保存会议室信息 */

    /**
     * sort first by starting time
     * store meetings in priority queue, the value is the end time
     * for new meetings, if previous meeting has end (start of the queue)
     *  remove it and add new meeting
     *
     * time complexity:  O(nlogn) sorting
     * space complextiy: O(n)
     */
    public int minMeetingRooms1(List<Interval> intervals) {
        // Write your code here
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval t1, Interval t2) {
                return t1.start - t2.start;
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int meetings = intervals.size();
        pq.offer(intervals.get(0).end);

        for (int i = 1; i < meetings; i++) {
            if (pq.peek() <= intervals.get(i).start) {
                // the earliest meeting has ended, so remove it
                pq.poll();
            }
            pq.offer(intervals.get(i).end);
        }

        return pq.size();
    }
}
