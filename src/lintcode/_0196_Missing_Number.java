package lintcode;

/**
 * 196 · Missing Number
 * Easy
 * #Math, #Binary
 * Facebook Meta, Microsoft, Bloomberg
 *
 * Given an array contains N numbers of 0 .. N, find which number doesn't exist in the array.
 *
 * Example 1:
 * Input:[0,1,3]
 * Output:2
 *
 * Example 2:
 * Input:[1,2,3]
 * Output:0
 *
 * Challenge
 * Do it in-place with O(1) extra memory and O(n) time.
 */
public class _0196_Missing_Number {

    /*
    解法 1 - 先排序, 再遍历 - O(nlogn) + O(n)

    解法 2 - 用 1 ~ n 的和 减去数组和
            注意: 为了避免 overflow, 每次用 下标 i - nums[i]

    解法 3 - 用 XOR 来抵消相同的值
     */

    public int findMissing_2(int[] nums) {
        if (nums == null || nums.length == 0) { return -1; }

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += i - nums[i];
        }

        // 循环结束, ans = (0 + 1 + .. + (n - 1)) - 数组和
        // 最后还缺少 n, 所以 ans = ans + n
        return ans + nums.length;
    }


    /**
     * xor 将相同的数互相取消
     */
    public int findMissing_3(int[] nums) {
        if (nums == null || nums.length == 0) { return -1; }

        int ans = 0;
        for (int i : nums) {
            ans ^= i;
        }

        for (int i = 0; i <= nums.length; i++) { // 这里要从 0 到 n, 所以用 <=
            ans ^= i;
        }

        return ans;
    }
}
