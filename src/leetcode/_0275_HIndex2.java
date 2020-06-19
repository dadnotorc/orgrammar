/*
Medium
#Binary Search
 */
package leetcode;

/**
 * 275. H-Index II
 *
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer)
 * of a researcher, write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers
 * have at least h citations each, and the other N − h papers have no more than h citations each."
 *
 * Example:
 * Input: citations = [0,1,3,5,6]
 * Output: 3
 * Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
 *              received 0, 1, 3, 5, 6 citations respectively.
 *              Since the researcher has 3 papers with at least 3 citations each and the remaining
 *              two with no more than 3 citations each, her h-index is 3.
 * Note:
 * If there are several possible values for h, the maximum one is taken as the h-index.
 *
 * Follow up:
 * This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
 * Could you solve it in logarithmic time complexity? - 二分法
 *
 */
public class _0275_HIndex2 {


    /**
     * 在前一个基础上使用二分法
     * 要找到最小的 i 满足 citation[i] >= numOfBooks - i; 返回值为 numOfBooks - i;
     *
     * 此题重点在于明白题目, 分析出以上等式
     *
     */
    public int hIndex_BS(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int numOfBooks = citations.length;
        int l = 0, r = numOfBooks - 1;
        while (l <= r) { // 注意 这里用 <=
            int m = l + (r - l) / 2;
            if (citations[m] == numOfBooks - m) {
                return numOfBooks - m;
            } else if (citations[m] < numOfBooks - m) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        // 注意! 这里不可以返回 numOfBooks - r
        // 因为要找的是第一个i, 满足 citation[i] >= numOfBooks - i
        // 当最后 l=r, 是如果citation[m] < numsOfBooks - m, 将l右移至m+1, 则l所在位置必定 citation[l] > numOfBooks - l
        // 如果citation[m] > numsOfBooks - m, r左移可导致无法满足条件, 而l保持不变, 即本身为第一个满足条件的值
        return numOfBooks - l;
    }



    /**
     * 数组从右往左, 最右为第一本书. 要找出 i 本书, 每本至少 i 次引用
     * 下标    0 1  2  3 4
     * 数值    3 3 |3| 3 3
     * 本数    5 4 |3| 2 1
     * 当数值 = 书本数量时, 再往左看, 数量只会不变或减少, 但是书本数会增加, 所以返回当前书本数量
     *
     * 下标    0 1 2  3  4
     * 数值    2 2 2 |3| 3
     * 本数    5 4 3 |2| 1
     * 当数值 < 书本数量时, 表示到前一本为止, 数值仍保持 > 书本数, 即前一本仍满足条件, 即返回到前一本时的书本数量
     *
     * 易错点:
     * 1. 不可以返回引用数值, 而应该返回书本数量. 因为当数值 < 书本数量时, 引用数值可能为0
     */
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int numOfBooks = citations.length;
        for (int i = numOfBooks - 1; i >= 0; i--) {
            if (citations[i] == numOfBooks - i) {
                return numOfBooks - i;
            } else if (citations[i] < numOfBooks - i) {
                return numOfBooks - i -1; // 注意 返回的是到前一本为止的书本数量
            }
        }

        return numOfBooks;
    }
}
