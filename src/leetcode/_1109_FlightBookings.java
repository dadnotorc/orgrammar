package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * 1109. Corporate Flight Bookings
 * Medium
 *
 * There are n flights, and they are labeled from 1 to n.
 *
 * We have a list of flight bookings.  The i-th booking
 *  bookings[i] = [i, j, k] means that we booked k seats
 *  from flights labeled i to j inclusive.
 *
 * Return an array answer of length n, representing the number
 *  of seats booked on each flight in order of their label.
 *
 * Example 1:
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 *
 * Constraints:
 *   1 <= bookings.length <= 20000
 *   1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
 *   1 <= bookings[i][2] <= 10000
 */
public class _1109_FlightBookings {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        if (bookings == null || bookings.length == 0) {
            return new int[0];
        }

        int[] ans = new int[n];
        for (int[] bk : bookings) {
            for (int i = bk[0] - 1; i <= bk[1] - 1; i++) {
                ans[i] += bk[2];
            }
        }

        return ans;
    }

    @Test
    public void test1() {
        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
        int n = 5;
        int[] expected = {10,55,45,25,25};
        int[] actual = new _1109_FlightBookings().corpFlightBookings(bookings, n);
        assertArrayEquals(expected, actual);
    }
}
