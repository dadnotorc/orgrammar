package leetcode;

/**
 * 441. Arranging Coins
 *
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 *
 * Given n, find the total number of full staircase rows that can be formed.
 *
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 *
 * Example 1:
 * n = 5
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 * Because the 3rd row is incomplete, we return 2.
 *
 * Example 2:
 * n = 8
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 * Because the 4th row is incomplete, we return 3.
 */
public class _0441_ArrangingCoins {


    /**
     * 数学解法 - 要求寻找k, 满足 (1 + k) * k / 2 <= n, 进行数学拆解
     * (1 + k) * k <= 2n
     * k^2 + k <= 2n
     * k^2 + k + 1/4 - 1/4 <= 2n
     * (k + 1/2)^2 <= 2n + 1/4
     * (k + 1/2)^2 <= (8n + 1) / 4
     * k + 1/2 <= sqrt((8n + 1) / 4)
     * k <= sqrt((8n + 1) / 4) - 1/2
     * k <= (sqrt(8n + 1) - 1) / 2
     *
     * 注意, n需要转成long, 否则 8n 可能导致 int overflow
     */
    public int arrangeCoins_Math(int n) {
        return (int) (Math.sqrt(8 * (long)n + 1) - 1) / 2;
    }



    /**
     * 二分法 - 到k行时, 当前已用金币 (1+k)*k/2 枚.
     * 如果仍有多余金币, 说明答案可能是当前行或者之后的某一行 (注意剩余金币可能已无法完成下一行, 所以 l=k+1 后, l可能已不是答案)
     * 如果金币已不足填满当前行, 说明答案肯定是前一行, 所以 r=k-1, 返回r才是正解
     *
     * 易错点:
     * 1. l, r, k, curSum 都为long
     * 2. while条件是 l <= r
     */
    public int arrangeCoins_BinarySearch(int n) {
        long l = 1, r = n;
        long k, curSum;

        while (l <= r) { // 注意, 用 <=. 否则n=2时会出错
            k = l + (r - l) / 2;
            curSum = (1 + k) * k / 2;
            if (curSum == n) {
                return (int) k; // 别忘了 cast 回int
            } else if (curSum < n) { // 还有剩余金币可加入下一行
                l = k + 1;
            } else { // 所有金币已用完
                r = k - 1;
            }
        }

        return (int) r; // 注意 此处不可以return l. 例如 n=5,
    }



    /**
     * 也是暴力解法 - 每次减少i个coin, 知道当前所剩硬币 < i
     * 比前一种稍微简洁些
     */

    public int arrangeCoins_BruteForce_2(int n) {
        int k = 0;
        while (n > k) {
            k++;
            n -= k;
        }

        return k;
    }
    /**
     * 暴力解法 - 每次减少i个coin, 知道当前所剩硬币 < i
     */
    public int arrangeCoins_BruteForce(int n) {
        int res = 0;

        int k = 1;
        while (n >= k) {
            n -= k;
            k++;
            res++;
        }

        return res;
    }


}

