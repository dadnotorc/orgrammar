package lintcode;

import org.junit.jupiter.api.Test;
import util.Interval;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class _0920test {

    @Test
    void test1() {
        // test list input
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0, 30));
        list.add(new Interval(5, 10));
        list.add(new Interval(15, 20));
        assertFalse(new _0920().canAttendMeetings(list));

        // test array input
        Interval[] array = {
                new Interval(0, 30),
                new Interval(5, 10),
                new Interval(15, 20)};
        assertFalse(new _0920().canAttendMeetings(array));
    }

    @Test
    void test2() {
        // test list input
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(5, 8));
        list.add(new Interval(9, 15));
        assertTrue(new _0920().canAttendMeetings(list));

        // test array input
        Interval[] array = {
                new Interval(5, 8),
                new Interval(9, 15)};
        assertTrue(new _0920().canAttendMeetings(array));
    }
}
