package leetcode;

/**
 * 121. Best Time to Buy and Sell Stock
 * Easy
 * #Array, #DP
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 *
 * Constraints:
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 */
public class _0121_Best_Time_Buy_Sell_Stock {

    /**
     * 遍历数组
     * 1. 如果当前值 > curHigh, 说明还有 profit
     *    - 保持 curLow 不变
     *    - 更新 curHigh
     * 2. 如果当前值 < curLow, 说明现在是历史新低, 应当重新开始
     *    - 更新 curLow 和 curHigh
     * 3. 遍历的同时更新 max
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int curLow = prices[0], curHigh = prices[0], max = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > curHigh) {
                curHigh = prices[i];
            } else if (prices[i] < curLow) {
                curLow = prices[i];
                curHigh = curLow;
            }

            max = Math.max(max, curHigh - curLow);
        }

        return max;
    }
}
