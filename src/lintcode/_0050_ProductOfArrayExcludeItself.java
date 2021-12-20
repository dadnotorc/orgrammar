/*
Easy
#Array

 */
package lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 50 · Product of Array Exclude Itself
 *
 * Given an integers array A.
 *
 * Define B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1],
 * calculate B WITHOUT divide operation. Output B
 *
 * Example 1:
 * Input: A = [1,2,3]
 * Output: [6,3,2]
 * Explanation:
 * B[0] = A[1] * A[2] = 6; B[1] = A[0] * A[2] = 3; B[2] = A[0] * A[1] = 2
 *
 * Example 2:
 * Input: A = [2,4,6]
 * Output: [24,12,8]
 * Explanation:
 * B[0] = A[1] * A[2] = 24; B[1] = A[0] * A[2] = 12; B[2] = A[0] * A[1] = 8
 *
 *
 * leetcode 238 Product of Array Except Self
 */
public class _0050_ProductOfArrayExcludeItself {

    /**
     * 1 | _ x 2 x 3 x 4
     * 2 | 1 x _ x 3 x 4
     * 3 | 1 x 2 x _ x 4
     * 4 | 1 x 2 x 3 x _
     *
     * 将空格处当做1. 先从上向下做阶乘, 再从下向上.
     *
     * 注意, 从下向上时, 使用tmp记录之前一层的积
     */
    public List<Long> productExcludeItself(List<Integer> nums) {
        Long[] products = new Long[nums.size()];
        products[0] = 1L;
//        products[0] = Long.valueOf(1);

        for (int i = 1; i < nums.size(); i++) {
            products[i] = products[i - 1] * nums.get(i - 1);
        }

        Long tmp = 1L;
//        Long tmp = Long.valueOf(1);
        for (int j = nums.size() - 2; j >= 0; j--) {
            tmp *= nums.get(j + 1);
            products[j] *= tmp;
        }

//        List<Long> ans = new ArrayList<>(Arrays.asList(products));
//        return ans;
        return new ArrayList<>(Arrays.asList(products));
    }
}
