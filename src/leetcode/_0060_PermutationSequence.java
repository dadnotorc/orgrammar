/*
Medium
#Math, #Backtracking
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. Permutation Sequence
 *
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 *
 * Given n and k, return the kth permutation sequence.
 *
 * Note:
 * - Given n will be between 1 and 9 inclusive.
 * - Given k will be between 1 and n! inclusive.
 *
 * Example 1:
 * Input: n = 3, k = 3
 * Output: "213"
 *
 * Example 2:
 * Input: n = 4, k = 9
 * Output: "2314"
 */
public class _0060_PermutationSequence {

    /**
     * 1. 将1-n所有数字一一加入nums队列, 用于之后取值
     * 2. 将1-n的各自的阶乘存在数组, 方便查询
     * 3. k本身为1-indexed, 将其减一, 变成0-indexed, 方便计算
     * 4. 计算 k / (n-1)!, 所得的值对应nums队列下标 (注意, 此下标是0-indexed),
     *    将下标对应数字加入答案中,
     *    并将此数字从nums队列中移除 (因为不可能重复出现了)
     *    更新 k 值, k = k % (n-1)!
     * 5. 不断重复第4步, 取 (n-2)!, (n-3)!, 直到 (n-n)!
     *
     * 此题妙点在于将1-n数字放入队列, 每次计算 k/(n-1)! 取得的队列下标, 加入答案后, 将此数字从队列中去除
     * 另外, 将k从1-indexed变成0-indexed, 方便计算下标
     *
     * 可以注意到, n!没有被用到, 所以计算阶乘时, 无需计算
     */
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> nums = new ArrayList<>();
        int[] factorial = new int[n + 1]; // 1-indexed, 不过之后可发现最后一位是不需要的

        // nums = 1-2-3-..-n
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        // factorial = {1, 1!, 2!, 3!, ..., n!}
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = i * factorial[i - 1];
        }

        // 让k变成0-indexed, 方便计算
        k -= 1;

        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i]; // 此index为nums队列的下标
            sb.append(nums.get(index));
            nums.remove(index);
            k %= factorial[n - i]; // 取 k 除以 factorial[n-i] 的余数, 赋值于k
        }

        return sb.toString();
    }
}
