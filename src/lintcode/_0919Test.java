package lintcode;

import org.junit.jupiter.api.Test;
import util.Interval;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class _0919Test {

    @Test
    void test1() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0, 30));
        list.add(new Interval(5, 10));
        list.add(new Interval(15, 20));
        assertEquals(2, new _0919().minMeetingRooms(list));
        assertEquals(2, new _0919().minMeetingRooms1(list));
    }

    @Test
    void test2() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(2, 7));
        assertEquals(1, new _0919().minMeetingRooms(list));
        assertEquals(1, new _0919().minMeetingRooms1(list));
    }

}