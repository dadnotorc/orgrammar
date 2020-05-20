/*
Medium
#Stack
 */
package leetcode;

import java.util.Stack;

/**
 * 901. Online Stock Span
 *
 * Write a class StockSpanner which collects daily price quotes for some stock,
 * and returns the span of that stock's price for the current day.
 *
 * The span of the stock's price today is defined as the maximum number of consecutive days
 * (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.
 *
 * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85],
 * then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
 *
 * Example 1:
 * Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * Output: [null,1,1,1,2,1,4,6]
 * Explanation:
 * First, S = StockSpanner() is initialized.  Then:
 * S.next(100) is called and returns 1,
 * S.next(80) is called and returns 1,
 * S.next(60) is called and returns 1,
 * S.next(70) is called and returns 2,
 * S.next(60) is called and returns 1,
 * S.next(75) is called and returns 4,
 * S.next(85) is called and returns 6.
 *
 * Note that (for example) S.next(75) returned 4, because the last 4 prices
 * (including today's price of 75) were less than or equal to today's price.
 *
 * Note:
 * Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
 * There will be at most 10000 calls to StockSpanner.next per test case.
 * There will be at most 150000 calls to StockSpanner.next across all test cases.
 * The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.
 */
public class _0901_OnlineStockSpan {

    /**
     * 使用stack, 如果昨天价格<=今天价格, 将昨天从stack中pop出来, 将其span加入今天的span中.
     * 之后继续往前找, 直到找到不低于今天的价格为止
     *
     * 但是速度不如解法1
     */

//    public _0901_OnlineStockSpan() {
//        可以为空
//        或者 stack = new Stack<>();
//    }

    Stack<int[]> stack = new Stack<>();

    public int next_2(int price) {
        int res = 1; // 起始为1, 因为当天价格也 <= 当天价格
        while (!stack.isEmpty() && stack.peek()[0] <= price) { // 0位记录价格, 1位记录span
            res += stack.pop()[1];
        }

        int[] current = {price, res};
        stack.push(current);
        return res;
    }


    /**
     * 使用ResultType数组记录当前价格以及span.
     */
    class ResultType{
        int price;
        int span;
        public ResultType(int price) {
            this.price = price;
            this.span = 1;
        }
    }

    private ResultType[] spanner;
    private int cur;

    public _0901_OnlineStockSpan() {
        spanner = new ResultType[10001]; // 因为 at most 10000 calls to StockSpanner.next per test case.
        spanner[0] = new ResultType(100001); // 1 <= price <= 10^5 所以将首位定在更高的价格
        cur = 0;
    }

    public int next(int price) {
        int prev = cur;
        cur++;
        spanner[cur] = new ResultType(price); // 记得先创建个新obj出来
        while (prev >= 0) {
            if (spanner[prev].price <= spanner[cur].price) {
                spanner[cur].span += spanner[prev].span;
                prev -= spanner[prev].span;
            } else {
                break;
            }
        }

        return spanner[cur].span;
    }
}
