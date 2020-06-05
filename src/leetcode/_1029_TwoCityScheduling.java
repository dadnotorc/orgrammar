/*
Easy
#Greedy
 */
package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1029. Two City Scheduling
 *
 * There are 2N people a company is planning to interview. The cost of flying the i-th person
 * to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
 *
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 *
 * Example 1:
 * Input: [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 *
 * Note:
 * 1. 1 <= costs.length <= 100
 * 2. It is guaranteed that costs.length is even.
 * 3. 1 <= costs[i][0], costs[i][1] <= 1000
 */
public class _1029_TwoCityScheduling {

    /**
     * 按 (costs[i][0] - cost[i][1]) 大小排列, 差值越低说明去A比去B能省更多
     * 例如   [[10,20], [30,200], [400,50], [30,20]], 差值为 (-10, -170, 350, 10).
     * 排序后 [[30,200], [10,20], [30,20], [400,50]], 差值为 (-170, -10, 10, 350).
     * 之后将前N个[0]位 与 后N个[1]位相加
     */
    public int twoCitySchedCost(int[][] costs) {
        int res = 0;
        // sort by the (costs[i][0] - cost[i][1])
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (a[0] - a[1]) - (b[0] - b[1]);
            }
        });

        int len = costs.length;
        for (int i = 0; i < len / 2; i++) {
            res += costs[i][0] + costs[len - 1 - i][1];
        }

        return res;
    }


    /**
     * DP - dp[i][j] 记录 第i个人去A 与 第j个人去B
     */
    public int twoCitySchedCost_dp(int[][] costs) {
        int n = costs.length / 2;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + costs[i - 1][0]; // dp[i][0] - i去A的cost
            dp[0][i] = dp[0][i - 1] + costs[i - 1][1]; // dp[0][j] - j去B的cost
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + costs[i + j - 1][0], dp[i][j - 1] + costs[i + j - 1][1]);
            }
        }

        return dp[n][n];
    }


    @Test
    public void test1() {
        int[][] costs = {{10,20},{30,200},{400,50},{30,20}};
        org.junit.Assert.assertEquals(110, twoCitySchedCost(costs));
    }
}