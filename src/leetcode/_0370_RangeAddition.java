/*
Medium

Google
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 370. Range Addition
 *
 * Assume you have an array of length n initialized with all 0’s and are given k update operations.
 *
 * Each operation is represented as a triplet: [startIndex, endIndex, inc]
 * which increments each element of subarray A[startIndex … endIndex] (startIndex and endIndex inclusive) with inc.
 *
 * Return the modified array after all k operations were executed.
 *
 * Example
 * Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * Output: [-2,0,3,5,3]
 * Explanation:
 * Initial state:                       [0,0,0,0,0]
 * After applying operation [1,3,2]:    [0,2,2,2,0]
 * After applying operation [2,4,3]:    [0,2,5,5,3]
 * After applying operation [0,2,-2]:  [-2,0,3,5,3]
 */
public class _0370_RangeAddition {

    /** 思考过程:
     * 1. 因为只有在所有 update 执行结束后, 才需要执行 read query, 所以每次更新无需处理整个 array (只需要考虑一部分)
     * 2. 累加 cumulative sums operations 会用先前的数据, 来影响后来的数据
     *
     * 所以每次 update (start, end, val), 我们只需完成
     *    arr[start] += val
     *    arr[end + 1] -= val   (因为在最后递加的过程里, end 的下一位就不会加上 val)
     *
     * update 完成后, 在 arr上做一层 从头到尾 (去掉首位) 的递加
     * 例如: 0   1   2   3   4   (下标)
     *      0   0   0   0   0   (起始)
     *      0   2   0   0  -2   (第一 [1,3,2])
     *      0   2   3   0  -2   (第二 [2,4,3] - 忽略 end + 1 因为越界了)
     *     -2   2   3   2  -2   (第三 [0,2,-2])
     *     -2   0   3   5   3   (递加 - array[i] += array[i - 1] - 注意 array[i - 1] 是已经更新的, 考虑左下右上, 斜线相加, 而不是上一行的两两相加)
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        if (length == 0 || updates == null || updates.length == 0 || updates[0].length < 3) {
            return new int[0];
        }

        int[] arr = new int[length]; // arr 中所有值默认起始值为 0

        for (int[] update : updates) {
            arr[update[0]] += update[2];

            if (update[1] + 1 < length) {
                arr[update[1] + 1] -= update[2];
            }
        }

        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
        }

        return arr;
    }

    @Test
    public void test1() {
        int length = 5;
        int[][] updates = new int[][] {{1,3,2} , {2,4,3}, {0,2,-2}};
        int[] exp = new int[] {-2,0,3,5,3};

        Assert.assertArrayEquals(exp, getModifiedArray(length, updates));
    }
}
