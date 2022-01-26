package lintcode;

/**
 * 1334 · Rotate Array
 * Easy
 * #Array
 * Bloomberg, Microsoft, Amazon
 *
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 * Input: [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Example 2:
 * Input: [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 * Challenge
 * 1.Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * 2.Could you do it in-place with O(1) extra space?
 *
 * 类似 lintcode 8 · Rotate String
 * 区别是 后者要求是 in-place
 */
public class _1334_RotateArray {

    // 要注意:
    // 1: k < n
    // 2. k > n
    // 3. k = 0 或者 k = n

    /**
     * in-place rotate 使用 两个指针 与 两个临时参数
     *
     * 时间 O(n) - 如果是 void func, 会快些. 但是此题是返回 int[], 相比之前 下一个解答, 用赋值的方法更快, 无需借助 tmp 变量 或者 指针
     * 空间 O(1)
     */
    public int[] rotate_1(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k % nums.length == 0) {
            return nums;
        }

        int n = nums.length;
        k %= n;

        int curTmp;
        int nextTmp = nums[0];

        int curIndex = 0, prevIndex = 0;

        for (int i = 0; i < n; i++) {
            curTmp = nextTmp;
            curIndex = (curIndex + k) % n;

            if (curIndex != prevIndex) {
                nextTmp = nums[curIndex];
                nums[curIndex] = curTmp;
            } else {
                nums[curIndex] = curTmp;

                curIndex++;
                prevIndex++;
                nextTmp = nums[curIndex];
            }
        }

        return nums;
    }


    /**
     * 使用两个数组, 计算 rotate 之后. 原有元素的新位置, 将原有元素拷入新数组中
     * 时间 O(n)
     * 空间 O(1)
     */
    public int[] rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k % nums.length == 0) {
            return nums;
        }

        int n = nums.length;
        k = k % n; // 别忘了这里要 %. 第一次错误就是漏了这里

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int index = (n - k + i) % n; // 别忘了 这里也要 % n
            ans[i] = nums[index];

            // 也可以写成
            // ans[(i + k) % n] = nums[i];

            // i - index: 0 - 4, 1 - 5, 2 - 6, 3 - 0, 4 - 1, 5 - 2, 6 - 3
            // System.out.println("i = " + i + "; index = " + index);
        }
        return ans;
    }

    /**
     * 针对这种题的特殊解法, 分割 反转 再反转 - 可以做到 in-place
     * [1,2,3,4,5,6,7], k = 3
     * 先划分成    [1,2,3,4] [5,6,7]
     * 反转成      [4,3,2,1] [7,6,5]
     * 合起来再反转 [5,6,7,1,2,3,4]
     *
     * 时间 O(n) 遍历两次数组
     * 空间 O(1)
     */
    public int[] rotate_2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k % nums.length == 0) {
            return nums;
        }

        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1 - k);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);

        return nums;
    }

    public void reverse(int[] nums, int l, int r) {
        for (int i = l, j = r; i < j; i++, j--) {
            int xor = nums[i] ^ nums[j];
            nums[i] ^= xor;
            nums[j] ^= xor;
        }
    }
}
