package interviews;

import org.junit.Test;

import java.util.Arrays;

/**
 * Amazon OA - 2022 年 1月
 *
 * Given an array of integers, perform certain operations in order to satisfy the following:
 * - The first array element must be 1
 * - For all other elements, the difference between adjacent integers must not be greater than 1.
 *   In other words, for 1 <= i < n, arr[i] - arr[i - 1] <= 1
 *
 * Following operations are available to accomplish:
 * - rearrange the elements in any way
 * - reduce any element to any number that is at least 1 (最多可以把当前数字减少到 1)
 *
 * What is the maximum value that can be achieved for the final element of the array by following these operations & constraints?
 *
 * Example
 * Input: arr = [3,1,3,4]
 * Output: 4
 * Explanation:
 * 1. Subtract 1 from the first element, making the array [2,1,3,4]
 * 2. Rearrange the array into [1,2,3,4]
 * 3. The final elements value is 4, the maximum value that can be achieved.
 *
 * Example
 * Input: [3, 1, 3, 4, 7, 6, 9]
 * Output: 7
 * Explanation:
 * sort to [1, 3, 3, 4, 6, 7, 9], -> [1, 2, 3, 4, 6, 7, 9] -> [1, 2, 3, 4, 5, 7, 9] -> [1, 2, 3, 4, 5, 6, 7], max value is 7.
 *
 * 注意, 不能直接返回 arr.length, 因为原数组中数字可能都相等
 * input: [2,2,2]
 * Output: 2
 * [2,2,2] -> [1,2,2]
 */
public class AMZN_2022_Max_Final_Value_in_Array {

    /**
     * 1. 排序
     * 2. 比较相邻数字, 间距 > 1, 则修改后者
     */
    public int getMaxValue(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        Arrays.sort(arr);
        arr[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }

        return arr[arr.length - 1];
    }


    /**
     * 有 bug - 不能通过比较数组长度 vs 最后一位的值
     */
    public int getMaxValue_bug(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        if (arr[n - 1] > n) {
            // [2,3,4,5]: 5 > 4, 返回 4
            // [2,2,2,5]: 只能返回 3
        }

        if (arr[n - 1] <= n) {
            // [1,2,3,3]: 返回 3
            // [1,2,3,5]: 返回 4
        }

        // 只能得出结论, 上限是 n. 无法确定下限
        return 0;
    }

    @Test
    public void test1() {
        int[][] tests = {
                {3,1,3,4},
                {3, 1, 3, 4, 7, 6, 9},
                {2,2,2},
                {2,3,4,5},
                {2,2,2,5},
                {1,2,3,3},
                {1,2,3,5}
        };

        int[] expects = {4,7,2,4,3,3,4};

        for (int i  = 0; i < tests.length; i++) {
            org.junit.Assert.assertEquals(expects[i], getMaxValue(tests[i]));
        }
    }
}
