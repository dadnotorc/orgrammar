package lintcode;

import org.junit.jupiter.api.Test;
import util.Interval;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class _0156Test {

    @Test
    void test1() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 3));
        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 3));
        List<Interval> actual = new _0156().merge(list);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).start, actual.get(0).start);
        assertEquals(expected.get(0).end, actual.get(0).end);
    }

    @Test
    void test2() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 3));
        list.add(new Interval(2, 6));
        list.add(new Interval(8, 10));
        list.add(new Interval(15, 18));
        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 6));
        expected.add(new Interval(8, 10));
        expected.add(new Interval(15, 18));
        List<Interval> actual = new _0156().merge(list);

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
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(2, 3));
        list.add(new Interval(4, 5));
        list.add(new Interval(6, 7));
        list.add(new Interval(8, 9));
        list.add(new Interval(1, 10));
        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 10));
        List<Interval> actual = new _0156().merge(list);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).start, actual.get(0).start);
        assertEquals(expected.get(0).end, actual.get(0).end);
    }
}