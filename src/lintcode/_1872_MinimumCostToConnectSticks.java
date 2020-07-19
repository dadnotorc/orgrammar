/*
Medium

Amazon
 */
package lintcode;

import java.util.List;
import java.util.PriorityQueue;

/**
 * 1872. Minimum Cost to Connect Sticks
 *
 * You have some sticks with positive integer lengths.
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.
 * You perform this action until there is one stick remaining.
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 *
 * Notice
 * 1 ≤ sticks.length ≤ 10^4
 * 1 ≤ sticks[i] ≤ 10^4
 *
 * Example 1:
 * Input:
 *  [2,4,3]
 * Output: 14
 * Explanation: First connect 2 and 3 to 5 and cost 5; then connect 5 and 4 to 9; total cost is 14
 *
 * Example 2:
 * Input:
 *  [1,8,3,5]
 * Output: 30
 * 1+3=4, 4+5=9, 9+8=17. So 4+9+17=30n
 *
 * 此题类似 leetcode 1167. Minimum Cost to Connect Sticks
 */
public class _1872_MinimumCostToConnectSticks {

    /**
     * 使用PQ排列, 每次取最短的两根, 将两者之和加入res中, 并放回PQ中
     *
     * 注意, 当PQ只剩一根时, 退出循环, 因为最后这根已经加入过res中, 所以不要重复加入了
     */
    public int MinimumCost(List<Integer> sticks) {
        if (sticks.size() == 1)
            return sticks.get(0);

        int res = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : sticks) {
            pq.offer(i);
        }

        while (pq.size() > 1) {
            int val = pq.poll() + pq.poll();
            res += val;
            pq.offer(val);
        }

        // 注意 这里 别要再加pq中最后一个元素了, 因为res中已经加过了

        return res;
    }
}
