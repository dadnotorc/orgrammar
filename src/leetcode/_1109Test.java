package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class _1109Test {

    @Test
    void test1() {
        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
        int n = 5;
        int[] expected = {10,55,45,25,25};
        int[] actual = new _1109().corpFlightBookings(bookings, n);
        assertArrayEquals(expected, actual);
    }

}