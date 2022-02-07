package lintcode;

import org.junit.Test;

import java.util.Stack;

/**
 * 264 · Counting Universal Subarrays
 * Easy
 * #Stack, #Array
 *
 * You will be given an array comprised of '2's or '4's. A subarray (A subarray is a group of contiguous elements
 * in an array and cannot be empty) of such an array is called "universal" if it matches the following conditions:
 *
 * 1. The 2's and 4's are grouped consecutively (e.g., [4, 2],[2, 4],[4, 4, 2, 2],[2, 2, 4, 4],[4, 4, 4, 2, 2, 2], etc.).
 * 2. The number of 4's in the subarray is equal to the number of 2's in the subarray.
 * 3. Subarrays with the same element but different positions are treated differently.
 *    For example, there are two [4, 2] subarrays in array[4, 2, 4, 2].
 *
 * You need to return an integer value, the number of "universal" subarrays in a given array.
 *
 * 1 ≤ |array| ≤ 10^5
 * array[i] ∈ (2, 4)
 *
 * Example
 * Sample 1:
 * Input sample: array = [4, 4, 2, 2, 4, 2]
 * Output sample: 4
 * Explanation: The 4 subarrays that match these two criteria are: [4, 4, 2, 2]，[4,2]，[2,4]，[4,2].
 *              Note that there are two subarrays [4,2], in indexes 1-2 and 4-5, respectively.
 *
 * Sample 2:
 * Input sample: array = [4, 4]
 * Output sample: 0
 * Explanation: the given array does not have 2, certainly cannot satisfy the condition two, so the result is 0.
 */
public class _0264_Counting_Universal_Subarrays {

    /**
     * 遍历 并 统计 当前数的长度. 每次读取都比较 前一个数的长度 VS 当前数长度.
     * - 前一个数长度 >= 当前数长度, 则答案 +1
     *
     * 当前数改变时 (即 当前位 与 前一位 不同时), 交换长度, 并将当前数长度 reset 为 1
     *
     * 时间 - O(n), 空间 - O(1)
     */
    public int subarrays(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }

        int ans = 0;

        int preNumLen = 0; // 前一个数的长度从 0 开始, 因为最开始的时候, 就没有任何前一个数
        int curNumLen = 1; // 当前数的长度从 1 开始, 因为刚读到它

        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[i -1]) {
                preNumLen = curNumLen;
                curNumLen = 1;
            } else {
                curNumLen++;
            }

            // 每次都要比较长度
            if (preNumLen >= curNumLen) {
                ans++;
            }
        }

        return ans;
    }




    /**
     * todo 能不能用 stack 做?   考虑 4, 2 顺序　加上　2,4 顺序
     * 有 bug
     */
    public int subarrays_bug(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }

        int ans = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i : array) {
            if (!stack.isEmpty()) {
                if (stack.peek() != i) { // 这里假设, array 中 只有 2 或者 4, 没有别的可能
                    stack.pop();
                    ans++;
                }
            }
            stack.push(i);
        }

        while (stack.size() > 1) {
            int top = stack.pop();
            if (top != stack.peek()) {
                ans++;
            }
        }

        return ans;
    }


    @Test
    public void test1() {
        int[] array = {2,2,2,2,2,2,4,4,2,4,4,4,4,2,2,2,2,4,2,4};

        org.junit.Assert.assertEquals(11, subarrays_bug(array));
    }

}
