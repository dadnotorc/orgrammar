/*
Medium
#Heap, #Interval, #Sweep line, #Greedy, #Sort
Facebook, Google, Uber
 */
package lintcode;

import org.junit.Test;
import util.Interval;

import java.util.*;

import static org.junit.Assert.assertEquals;

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
public class _0919_MeetingRooms2 {

    /**
     * 1. 按照会议开始时间, 由先到后排序
     * 2. 将会议放去PriorityQueue, value = end time
     * 3. 遇到新会议时, 对比PQ首位 earliest meeting's end time vs current meeting's start time,
     *    决定是否可以使用同一会议室
     * 4. 返回PQ size
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
                return t1.start - t2.start; // 小的/早的start time在前, 大的/晚的在后
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 存meeting end time

        for (Interval t : intervals) {
            if (pq.peek() != null && pq.peek() <= t.start) {
                pq.poll(); // earliest meeting has ended
            }
            pq.offer(t.end);
        }

        return pq.size();
    }


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

    @Test
    public void test1() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0, 30));
        list.add(new Interval(5, 10));
        list.add(new Interval(15, 20));
        assertEquals(2, new _0919_MeetingRooms2().minMeetingRooms(list));
        assertEquals(2, new _0919_MeetingRooms2().minMeetingRooms1(list));
    }

    @Test
    public void test2() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(2, 7));
        assertEquals(1, new _0919_MeetingRooms2().minMeetingRooms(list));
        assertEquals(1, new _0919_MeetingRooms2().minMeetingRooms1(list));
    }

    @Test
    public void test3() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0, 10));
        list.add(new Interval(5, 15));
        list.add(new Interval(10, 20));
        assertEquals(2, new _0919_MeetingRooms2().minMeetingRooms(list));
        assertEquals(2, new _0919_MeetingRooms2().minMeetingRooms1(list));
    }
}
