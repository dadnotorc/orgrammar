package lintcode;


import org.junit.jupiter.api.Test;
import util.Interval;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _0839test {

    @Test
    void test1() {
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
        List<Interval> actual = new _0839().mergeTwoInterval(list1, list2);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).start, actual.get(0).start);
        assertEquals(expected.get(0).end, actual.get(0).end);
        assertEquals(expected.get(1).start, actual.get(1).start);
        assertEquals(expected.get(1).end, actual.get(1).end);
    }

    @Test
    void test2() {
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
        List<Interval> actual = new _0839().mergeTwoInterval(list1, list2);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).start, actual.get(0).start);
        assertEquals(expected.get(0).end, actual.get(0).end);
        assertEquals(expected.get(1).start, actual.get(1).start);
        assertEquals(expected.get(1).end, actual.get(1).end);
        assertEquals(expected.get(2).start, actual.get(2).start);
        assertEquals(expected.get(2).end, actual.get(2).end);
    }

    @Test
    void test3() {
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
        List<Interval> actual = new _0839().mergeTwoInterval(list1, list2);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).start, actual.get(0).start);
        assertEquals(expected.get(0).end, actual.get(0).end);
        assertEquals(expected.get(1).start, actual.get(1).start);
        assertEquals(expected.get(1).end, actual.get(1).end);
        assertEquals(expected.get(2).start, actual.get(2).start);
        assertEquals(expected.get(2).end, actual.get(2).end);
    }
}
