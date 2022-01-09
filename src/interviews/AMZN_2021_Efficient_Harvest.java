package interviews;

import org.junit.Assert;
import org.junit.Test;

/**
 * Efficient Harvest - sliding window
 *
 * A farmer uses pivot irrigation (中心支点灌溉) to water a circular field of crops (圆形).
 * Due to varying conditions, the field does not produce consistently. The farmer wants to achieve maximum profit
 * using limited resources for harvest. The field is segmented into a number of equal segments, and a profit is
 * calculated for each segment. This profit is the cost to harvest versus the sale price a the produce.
 *
 * The farmer will harvest a number of contiguous segments along with those opposite. (连续的 n 块 + 对面的 n 块)
 * Determine the maximum profit the farmer can achieve.
 *
 * For example, the field is divided into n = 6 sections and will select k = 2 contiguous sections
 * and those opposite for harvest. The profit estimates are profit = [1, 5, 1, 3, 7,-3) respectively.
 * The diagrams below show the possible choices with profits(0) at the 9 o'clock position and filling counterclockwise.
 *
 *   -3  7          连续 2 块及其对立面的可选项为: [1, 5] ~  [7, 3]
 * 1       3                               OR  [5, 1] ~ [-3, 7]
 *   5   1                                 OR  [1, 3] ~ [1, -3]
 *
 * -3 The profit levels, from left to right, are 1 + 5 + 7 + 3 = 16, 5 + 1 - 3 + 7 = 10, and 1 + 3 + 1 - 3 = 2.
 * The maximum profit is 16.
 *
 *
 * Function Description Complete the function maxProfit in the editor below.
 * The function must return the maximum profit achievable.
 * maxProfit has the following parameters: k an integer denoting the half of the needed amount of pieces of the field. profit[profit[0],..profit[n-1].
 */
public class AMZN_2021_Efficient_Harvest {

    /*  n = 8, k = 2, [0,1,2,3,4,5,6,7]

            7   6
        0           5       [0,1]   [1,2]   [2,3]   [3,4]
                                 -->     -->     -->         之后再转就重复了
        1           4       [4,5]   [5,6]   [6,7]   [7,0]
            2   3

         i 从 0 开始逆时针转 (向下)
     */

    /**
     * 简化的解法, 也是 sliding window
     *
     * 时间 O(n)
     * 空间 O(1)
     */
    public int maxProfit_2(int k, int[] profits) {
        if (profits == null || profits.length < 2 * k) {
            return 0; // 无法进行平分
        }

        int n = profits.length;
        int half = n / 2;
        int curSum = 0, max = Integer.MIN_VALUE; // 后者取 MIN_VALUE, 是因为有些值可能为负数
        int i = 0, j = 0;

        // i 在窗口右侧, j 在窗口左侧
        while (i <= half) { // 只做一半, 因为另一半是重复了. 注意 必须是 <=, 而不是 <. 不然, 最后的一组会错过, 例如 [1,1,1,3,8,1,1,5], k = 2
            curSum += profits[i] + profits[(half + i) % n]; // 这里必须 % n, 因为 i 可以等于 half

            if (i - j + 1 == k) { // 不能用 < k 判断, 必须精确 - 当 k = 2, i = 1, j = 0, 窗口填满, 比较最大值, 然后去掉 j 指针的值并右移
                // 当前 i 与 j 到达 窗口边沿位置, 比较最大值
                max = Math.max(max, curSum);

                // 去掉下一轮 即将出窗口的 j
                curSum -= profits[j] + profits[(half + j) % n]; // 这里也必须要 %n, 因为 i - j + 1 = k, 当 k = 1 时, i = j, 即 j 可以等于 half
                j++;
            }

            i++;
        }

        return max;
    }



    /**
     * 这题的 trick 点在于: 确定 i 坐标及其对立点
     * 下标 i 的对面是 n / 2 + i
     *
     * 假设 n 是偶数
     *
     * 时间 O(n)
     * 空间 O(1)
     *
     * @param k the half of the needed amount of pieces of the field (收割面积的一半, 记得是选对立的 k 组)
     * @param profits the array of profits
     * @return the maximum profit achievable
     */
    public int maxProfit(int k, int[] profits) {
        if (profits == null || k < 1 || profits.length < 2 * k) {
            return 0; // 无法进行平分
        }

        int n = profits.length;
        int half = n / 2;

        // 先求第一组的和
        int curSum = 0;
        for (int i = 0; i < k; i++) {
            curSum += profits[i] + profits[half + i];
        }

        // 之后每组, 使用 slide window: 加上进 window 的值, 减去出 window 的值
        int max = curSum;
        for (int j = k; j <= half; j++) {
            curSum += profits[j] + profits[(half + j) % n]; // 这里必须要 % n, 因为 j 可能等于 half
            curSum -= profits[j - k] + profits[half + j  - k]; // 这里应该不需要 % n

            max = Math.max(max, curSum);
        }

        return max;
    }




    @Test
    public void test1() {
        int k = 2;
        int[] profits = new int[] {1, 5, 1, 3, 7, -3};
        Assert.assertEquals(16, maxProfit(k, profits));
        Assert.assertEquals(16, maxProfit_2(k, profits));

        k = 1;
        profits = new int[] {-3, -6, 3, 6};
        Assert.assertEquals(0, maxProfit(k, profits));
        Assert.assertEquals(0, maxProfit_2(k, profits));

        k = 1;
        profits = new int[] {3, -5};
        Assert.assertEquals(-2, maxProfit(k, profits));
        Assert.assertEquals(-2, maxProfit_2(k, profits));



        /*
            5   1
        1           1       [1,1]   [1,1]   [1,3]   [3,8]
                                 -->     -->     -->         之后再转就重复了
        1           8       [8,1]   [1,1]   [1,5]   [5,1]
            1   3                                 最后这组之和最大
         */
        k = 2;
        profits = new int[] {1,1,1,3,8,1,1,5};
        Assert.assertEquals(17, maxProfit(k, profits));
        Assert.assertEquals(17, maxProfit_2(k, profits));
    }
}
