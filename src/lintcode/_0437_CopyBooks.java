/*
Medium
#DP, #Binary Search
 */
package lintcode;

/**
 * 437 · Copy Books
 *
 * Given n books and the i-th book has pages[i] pages. There are k persons to copy these books.
 *
 * These books list in a row and each person can claim a continous range of books. For example,
 * one copier can copy the books from i-th to j-th continously, but he can not copy the 1st book,
 * 2nd book and 4th book (without 3rd book).
 *
 * They start copying books at the same time and they all cost 1 minute to copy 1 page of a book.
 * What's the best strategy to assign books so that the slowest copier can finish at earliest time?
 *
 * Return the shortest time that the slowest copier spends.
 *
 * The sum of book pages is less than or equal to 2147483647
 *
 * Example 1:
 * Input: pages = [3, 2, 4], k = 2
 * Output: 5
 * Explanation: First person spends 5 minutes to copy book 1 and book 2, Second person spends 4 minutes to copy book 3.
 *
 * Example 2:
 * Input: pages = [3, 2, 4], k = 3
 * Output: 4
 * Explanation: Each person copies one of the books.
 *
 * Challenge
 * O(nk) time
 */
public class _0437_CopyBooks {

    /**
     * 二分法 - 答案的范围在 max(pages) ~ sum(pages) 之间
     * 二分 设置页数上限/时间上限, 即每台机器被分配的最大值, 看更需要多少台机器
     *
     * 每次二分到一个时间上限 limit 的时候, 用贪心法从左到右扫描一下 pages, 看看需要多少个人(打印机)来完成
     * 将尽可能多的书分给同一个人, 判断复印完这 n 本书需要的人数是否 <= k
     * - 如果值 <= k, 说明人数足够, 每人可尝试分配更少的页数 -- 即 end 左移到 mid
     * - 如果值 > k, 说明人数不够, 每人需要增加工作量 (分配到更多的页数) -- 即 start 右移至 mid
     *
     * 时间上限 limit 与可否完成任务 (0 或者 1) 这两个量之间有单调性关系. 所以对 limit 进行二分查找, 找到可完成任务的最小 limit
     *
     * 时间复杂度 O(nlog(sum))
     */
    public int copyBooks_binary_search(int[] pages, int k) {
        if (pages == null || pages.length == 0) { return 0; }
        if (k < 1) { return Integer.MAX_VALUE; }

        // 二分页数, 找到适合 k 台机器(比较平均)的分配方式
        int start = 0, end = getSum(pages); // 所需时间的天花板为, 使用一台机器完成所有页面的拷贝

        while (start + 1 < end) {
            int mid = start + (end - start) / 2; // mid 为当前给每个机器安排的页数工作量
            int numCopierNeeded = getCopiersNeeded(pages, mid);

            if (numCopierNeeded <= k) { // 只需相同或更少的机器, 说明某些机器的工作量较大, 可减少其工作量 (分配到其他机器上)
                end = mid;
            } else { // 需要更多数量的机器, 说明每台机器需要负责更多的工作量,
                start = mid;
            }
        }

        if (getCopiersNeeded(pages, start) <= k) { // 先看前者, 因为需要给每台机器更少的工作量 (需要花费更少的时间)
            return start;
        }
        return end;
    }

    public int getCopiersNeeded(int[] pages, int limit) {
        int copiersNeeded = 1;
        int copiedPages = 0;

        for (int curPage : pages) {
            if (curPage > limit) {
                return Integer.MAX_VALUE;
            }

            copiedPages += curPage;

            if (copiedPages > limit) {
                copiersNeeded++;
                copiedPages = curPage; // 重新开一台来复印当前书的 curPage
            }
        }

        return copiersNeeded;
    }

    public int getSum(int[] pages) {
        int sum = 0;
        for (int curPage : pages) {
            sum += curPage;
        }
        return sum;
    }



    /**
     * DP
     */
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) { return 0; }
        if (k < 1) { return Integer.MAX_VALUE; }

        int n = pages.length;

        // 前缀和 - 到当前书为止, 共计有多少页
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + pages[i - 1];
        }

        // dp[i][j] 表示前 i 本书, 划分给 j 个人来拷贝, 最少需要的时间
        int[][] dp = new int[n + 1][k + 1];

        // 初始化 dp
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                for (int prev = 0; prev < i; prev++) {
                    int cost = prefixSum[i] - prefixSum[prev];
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[prev][j - 1], cost));
                }
            }
        }

        return dp[n][k];
    }
}
