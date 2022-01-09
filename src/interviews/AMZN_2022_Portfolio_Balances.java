package interviews;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Portfolio Balances - 非常类似 leetcode 370. Range Addition
 *
 * Invest in a number of assets. Each asset begins with a balance of 0, and its value is stored in an array
 * using 1-based indexing. Periodically, a contribution is received and equal investments are made in a subset
 * of the portfolio. Each contribution will be given by investment amount, start index, end index. Each investment
 * in that range will receive the contribution amount.
 *
 * Determine the maximum amount invested in any one investment after all contributions.
 *
 * Example:
 * input: investments = [0, 0, 0, 0, 0] (n = 5)
 * The variables left and right represent the starting and ending indices, inclusive.
 * Another variable, contribution, is the new funds to invest per asset. The first investment is at index 1.
 *
 * left    right   contribution              investments array
 *
 * 						    				  [0,  0,  0,  0,  0]
 *  1        2         10                    [10, 10,  0,  0,  0]
 *  2        4          5                    [10, 15,  5,  5,  0]
 *  3        5         12                    [10, 15, 17, 17, 12]
 *
 * In the 1st round, a contribution of 10 is made to investments 1 and 2.
 * In the 2nd round, a contribution of 5 is made to assets 2, 3 and 4.
 * In the 3rd round, a contribution of 12 is added to investments 3, 4 and 5.
 * The maximum invested in any one asset is 17
 *
 * Note: The investments array is not provided in the function. It is to be created after the number of assets available is known.
 */
public class AMZN_2022_Portfolio_Balances {

    /**
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
     *
     * @param n the number of investments available
     * @param rounds each rounds[i] contains a list of 3 integers: [left, right, contribution]
     * @return the maximum invested in any one asset
     */
    public int maxValue(int n, List<List<Integer>> rounds) {
        if (n == 0 || rounds == null || rounds.size() == 0 || rounds.get(0).size() < 3) {
            return 0;
        }

        int[] arr = new int[n]; // arr 中所有值默认起始值为 0

        for (List<Integer> round : rounds) {
            int left = round.get(0);
            int right = round.get(1);
            int contribution = round.get(2);

            arr[left - 1] += contribution; // 题目要求是 1-based indexing

            if (right < n) { // 这里 不 +1, 也是已经已经是 1-based indexing了
                arr[right] -= contribution;
            }
        }

        int max = arr[0];

        for (int i = 1; i < n; i++) {
            arr[i] += arr[i - 1];
            max = Math.max(max, arr[i]);
        }

        return max;
    }

    @Test
    public void test1() {
        int n = 5;
        List<Integer> inv_0 = new ArrayList<>(Arrays.asList(1,2,10));
        List<Integer> inv_1 = new ArrayList<>(Arrays.asList(2,4,5));
        List<Integer> inv_2 = new ArrayList<>(Arrays.asList(3,5,12));
        List<List<Integer>> rounds = new ArrayList<>() {{
            add(inv_0); // 注意格式 - 两层大括号 {{ }}, 还有每次用 ;
            add(inv_1);
            add(inv_2);
        }};

        Assert.assertEquals(17, maxValue(n, rounds));

    }

}
