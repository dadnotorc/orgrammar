package interviews;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Given an array of integer, find a continuous subarray:
 * 1. with the biggest sum of numbers
 * 2. same order as original
 *
 * (If their are duplicate answer, return the minimum one
 *  in lexicographical order)
 *
 * Example:
 * Input:  [1, -2, 10, -1, 20, -100].
 * Output: [10, -1, 20].
 * Explanation:
 *  10 + (-1) + 20 = 29 has the biggest sum
 *
 * 类似 lintcode 402. Continuous Subarray Sum
 *
 * Amazon phone interview
 */
public class FindLargestSubarray {

    /**
     * 思路: 如果遇到使当前sum变为负数的值, sum(a[0],a[i-2]) <  a[i-1],
     *  说明a[i-1]为绝对值较大的负值.
     *  则记录当前最大sum, 并将左右指针挪动 i 位置重新开始.
     *
     *  如当前sum仍 >=0, 则只挪动右指针
     */
    public int[] continuousSubarraySum(int[] a) {
        if (a == null || a.length < 1) {
            return new int[0];
        }

        int ansStart = 0, ansEnd = 0;
        int l = 0, r = 0;
        int curSum = a[0], maxSum = a[0]; // 也可以用long替代int

        for (int i = 1; i < a.length; i++) {
            if (curSum < 0) { // a[i-1] > sum(a[l], a[i-2]);
                curSum = a[i]; // 重新开始
                l = r = i;
            } else {
                curSum += a[i];
                r = i;
            }

            if (curSum > maxSum) {
                maxSum = curSum;
                ansStart = l;
                ansEnd = r;
            }
        }

        int[] ans = new int[ansEnd - ansStart + 1];
        for (int j = 0; j < ansEnd - ansStart + 1; j++) {
            ans[j] = a[j + ansStart];
        }

        return ans;
    }

    @Test
    public void test0() {
        int[] a = {1, -2, 10, -1, 20, -100};
        int[] exp = {10, -1, 20};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test1() {
        int[] a = {0};
        int[] exp = {0};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test2() {
        int[] a = {-3, 1, 3, -3, 4};
        int[] exp = {1, 3, -3, 4};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test3() {
        int[] a = {0, 1, 0, 1};
        int[] exp = {0, 1, 0, 1};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test4() {
        int[] a = {-4,7,-8,6,-5,-3,4};
        int[] exp = {7};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test5() {
        int[] a = {-3,1,3,-4,7};
        int[] exp = {1,3,-4,7};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test6() {
        int[] a = {1,0,0,1};
        int[] exp = {1,0,0,1};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test7() {
        int[] a = {-1,0,0,1};
        int[] exp = {0,0,1};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test8() {
        int[] a = {0,0,0,1};
        int[] exp = {0,0,0,1};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    /**
     * 注意 这个case
     */
    @Test
    public void test9() {
        int[] a = {-1,-2,-3,-100,1,2,3,100};
        int[] exp = {1,2,3,100};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test10() {
        int[] a = {-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22};
        int[] exp = {-22};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test11() {
        int[] a = {1,0,0,0};
        int[] exp = {1};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    public void test12() {
        int[] a = {};
        int[] exp = {};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }
}
