package leetcode;

import org.junit.Test;

/**
 * 122. Best Time to Buy and Sell Stock II
 * medium
 * #Array, #Greedy
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of
 * the stock at any time. However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 *
 * Example 2:
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Example 3:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class _0122_Best_Time_Buy_Sell_Stock_2 {

    /*
    贪心 - 上涨时持有, 一旦下跌就买出. 跌到底, 开始上涨时又买
     */

    /**
     * 速度更快 ~ 1ms
     * 只要比前一天上涨, 就把涨额加入 ans 中
     * 下跌 或者 不变, 都不做任何更新
     */
    public int maxProfit_2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int ans = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                ans += prices[i] - prices[i - 1];
            }
        }

        return ans;
    }


    /**
     * 速度较慢 ~ 3ms
     *
     * 易错点:
     * 1. 因为不能有multiple transaction, 所有只有当curProfit为0(没有卖出时), 才更新min
     * 2. for循环结束后, 别忘了如果curProfit不为0, 要将其加入maxProfit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int min = prices[0], curProfit = 0, maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min && curProfit == 0) {
                min = prices[i];
            } else {
                if (curProfit < (prices[i] - min)) {
                    curProfit = prices[i] - min;
                } else {
                    maxProfit += curProfit;
                    curProfit = 0;
                    min = prices[i];
                }
            }
        }

        if (curProfit != 0) { // 别忘了还有没加的profit
            maxProfit += curProfit;
        }

        return maxProfit;
    }

    @Test
    public void test1() {
        int[] prices = {7,1,5,3,6,4};
        org.junit.Assert.assertEquals(7, maxProfit(prices));
        org.junit.Assert.assertEquals(7, maxProfit_2(prices));
    }

    @Test
    public void test2() {
        int[] prices = {1,2,3,4,5};
        org.junit.Assert.assertEquals(4, maxProfit(prices));
        org.junit.Assert.assertEquals(4, maxProfit_2(prices));
    }

    @Test
    public void test3() {
        int[] prices = {7,6,4,3,1};
        org.junit.Assert.assertEquals(0, maxProfit(prices));
        org.junit.Assert.assertEquals(0, maxProfit_2(prices));
    }

    @Test
    public void test4() {
        int[] prices = {2,1,2,0,1};
        org.junit.Assert.assertEquals(2, maxProfit(prices));
        org.junit.Assert.assertEquals(2, maxProfit_2(prices));
    }
}
