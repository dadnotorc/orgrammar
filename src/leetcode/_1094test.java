package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class _1094test {

    /*

    Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
    Output: true
    */

    @Test
    void test1() {
        int[][] trips = new int[][] {{2,1,5},{3,3,7}};
        int capacity = 4;
        boolean actual = (new _1094().carPooling(trips, capacity));
        assertFalse(actual);
    }

    @Test
    void test2() {
        int[][] trips = new int[][] {{2,1,5},{3,3,7}};
        int capacity = 5;
        boolean actual = (new _1094().carPooling(trips, capacity));
        assertTrue(actual);
    }

    @Test
    void test3() {
        int[][] trips = new int[][] {{2,1,5},{3,5,7}};
        int capacity = 3;
        boolean actual = (new _1094().carPooling(trips, capacity));
        assertTrue(actual);
    }

    @Test
    void test4() {
        int[][] trips = new int[][] {{3,2,7},{3,7,9},{8,3,9}};
        int capacity = 11;
        boolean actual = (new _1094().carPooling(trips, capacity));
        assertTrue(actual);
    }

}
