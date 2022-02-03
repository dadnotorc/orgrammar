package interviews;

import org.junit.Test;

import java.util.Arrays;

/**
 * Consider an array of n ticket prices, tickets. A number, m, is defined as the size of some subsequences, s,
 * of tickets where each element covers an unbroken range of integers. That is to say, if you were to sort
 * the elements in s, the absolute difference between any elements j and j+1 would be either 0 or 1.
 * Determine the maximum length of a subsequence chosen from the tickets array.
 *
 * Example 1:
 * Input: tickets = [8, 5, 4, 8, 4]
 * Output: 3
 * Explanation:
 * Valid subsequences, sorted, are {4,4,5} and {8,8}.
 * These subsequences have m values of 3 and 2, respectively. Return 3
 *
 * Constraints:
 * 1 <= n <= 10^5
 * 1 <= tickets[i] <= 10^9
 */
public class LNKD_2022_Pick_Tickets {

    /**
     * 1. 先排序
     * 2. 遍历数组, 寻找并更新最长的 subsequences
     */
    public int pickingTickets(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        Arrays.sort(prices);

        int cur = 1, max = 1;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= prices[i - 1] + 1) {
                cur++;
            } else {
                cur = 1;
            }

            max = Math.max(cur, max);
        }

        return max;
    }




    @Test
    public void test1() {
        int[] prices = {8, 5, 4, 8, 4};
        org.junit.Assert.assertEquals(3, pickingTickets(prices));
    }

    @Test
    public void test2() {
        int[] prices = {8};
        org.junit.Assert.assertEquals(1, pickingTickets(prices));
    }
}
