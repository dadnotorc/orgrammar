/*
Medium
#Array, #Enumertation, #Greedy
Amazon, Facebook, Microsoft, Uber
 */
package lintcode;

/**
 * 149. Best Time to Buy and Sell Stock
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum profit.
 *
 * Example 1
 * Input: [3, 2, 3, 1, 2]
 * Output: 1
 * Explanation: You can buy at the third day and then sell it at the 4th day. The profit is 2 - 1 = 1
 *
 * Example 2
 * Input: [1, 2, 3, 4, 5]
 * Output: 4
 * Explanation: You can buy at the 0th day and then sell it at the 4th day. The profit is 5 - 1 = 4
 *
 * Example 3
 * Input: [5, 4, 3, 2, 1]
 * Output: 0
 * Explanation: You can do nothing and get nothing.
 */
public class _0149_BestTimeToBuyAndSellStock {

    /**
     * 类似 max subarray (前缀和问题)
     * 原始prices数组 a=[a0, a1, a2, ..., an]
     * 前缀差数组     b=[b0, b1, b2, ..., bn] where b[0] = 0, b[i] = a[i] - a[i-1] (i > 0)
     *
     * 寻找最大利润 = 寻找最大的 a[j] - a[i] (i < j)
     * a[j] - a[i] = (a[j]-a[j-1]) + (a[j-1]-a[j-2]) + ... + (a[i+2]-a[i-1]) + (a[i+1]-a[i])
     *             = b[j] + b[j-1] + ... + b[i+1] + b[i+1]
     *
     * 例如: 假设我们要计算 a[5] - a[2] = (a[5]-a[4]) + (a[4]-a[3]) + (a[3]-a[2]) = b[5] + b[4] + b[3]
     *
     * 从0位开始, 只要每一位的前缀和都大于0, 说明当前价格都大于0位的价格 a[i] - a[0] = prefix sum > 0
     * 也就是说0位是到目前为止最低的价格
     *
     * 当到达j位, 如果前缀和小于等于0, a[j] - a[0] <= 0, 说明j位价格低于0位, 则j位变成最低价格,
     * 则我们应当将当天的利润归零 b[j] = 0
     */
    public int maxProfit_3(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int curProfit = 0; // prefix sum b[0] = 0
        int maxProfit = 0; // ans

        for (int i = 1; i < prices.length; i++) {
            curProfit += prices[i] - prices[i-1]; // 前缀和 curProfit += b[i]

            curProfit = Math.max(curProfit, 0); // 如果当前利润 <=0, 则将其归零

            maxProfit = Math.max(maxProfit, curProfit); // 查看当天利润是否最高
        }

        return maxProfit;
    }




    /**
     * 另一种写法, 遍历数组, 记录:
     * 1. 到当天为止的最低价
     * 2. 到当天为止能获得的最大利润
     */
    public int maxProfit_2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int lowestPrice = prices[0];
        int highestProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                // 价格比前一天上涨, 保持lowestPrice不变, 计算highestProfit
                highestProfit = Math.max(highestProfit, prices[i] - lowestPrice);
            } else if (prices[i - 1] > prices[i]) {
                // 价格比前一天下跌, highestProfit保持不变, 更新highestProfit
                lowestPrice = Math.min(lowestPrice, prices[i]);
            }
            // 如果两天价格保持不变, 则不做任何改动
        }

        return highestProfit;
    }


    /*
    不能用头尾指针找min/max的办法, 因为最后可能不一定是max - min
    例如[7,8,10,1,5]
     */

    /**
     * 暴力解法 - 遍历所有elements, 遇到ith element之时, 找从头到i-1的最小值
     * n-1 + n-2 + ... + 1 = n * (n-1) / 2 = O(n^2)
     */
    public int maxProfit_1(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int highestProfit = 0;
        int lowestPrice = prices[0];

        for (int j = 1; j < prices.length; j++) {
            for (int i = 0; i < j; i++) {
                lowestPrice = Math.min(lowestPrice, prices[i]);
            }
            // 暴力解法中, 上面这个for循环会重复做同样的min判断很多次, 可以用memorization记住, 比如改成如下
            // lowestPrice = Math.min(lowestPrice, prices[j]);

            highestProfit = Math.max(highestProfit, prices[j] - lowestPrice);
        }

        return highestProfit;
    }
}
