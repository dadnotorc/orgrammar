/*
Hard
DP
Amazon
Ladder
 */
package lintcode;

/**
 * 1798. Minimum Cost to Merge Stones
 *
 * There are N piles of stones arranged in a row. The i-th pile has
 * stones[i] stones.
 *
 * A move consists of merging exactly K consecutive piles into one
 * pile, and the cost of this move is equal to the total number of
 * stones in these K piles.
 *
 * Find the minimum cost to merge all piles of stones into one pile.
 * If it is impossible, return -1.
 *
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 *
 * Example 1:
 * Input: stones = [3,2,4,1], K = 2
 * Output: 20
 * Explanation:
 * We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 *
 * Example 2:
 * Input: stones = [3,2,4,1], K = 3
 * Output: -1
 * Explanation:
 * After any merge operation, there are 2 piles left,
 * and we can't merge anymore.  So the task is impossible.
 *
 * 此题与leetcode 1000. Minimum Cost to Merge Stones 类似
 */
public class _1798_MinimumCostToMergeStones {

    // TODO 难题求解
    public int mergeStones(int[] stones, int K) {
        return 0;
    }

    // https://www.jiuzhang.com/solution/minimum-cost-to-merge-stones/
}
