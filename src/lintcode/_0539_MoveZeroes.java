/*
Easy
#Two Pointers, #Array
Facebook
 */
package lintcode;

/**
 * 539 · Move Zeroes
 *
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 * Example 1:
 * Input: nums = [0, 1, 0, 3, 12],
 * Output: [1, 3, 12, 0, 0].
 *
 * Example 2:
 * Input: nums = [0, 0, 0, 3, 1],
 * Output: [3, 1, 0, 0, 0].
 */
public class _0539_MoveZeroes {

    /**
     * 同向双指针 - 快慢指针同时向右移动
     * 1. 快指针不停向右移动, 直到数组末端
     *    - 遇到非 0 值时 (注意看, 下面两种情况下, 快慢指针都会移动)
     *       - 如 快慢指针相同 -> 两个指针同时右移
     *       - 如 快慢指针不同 -> 说明慢指针指向 0 值, 则将快指针的值赋予慢指针, 然后两个指针右移
     *    - 遇到 0 值时
     *       - 快指针继续右移跳过, 慢指针不动 (慢指针保持指向 0)
     *
     * 2. 别忘了, 将慢指针之后的所有位置赋值为 0
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * 可以想像成, 把数组中非 0 的值, 按顺序分别写入, 然后把数组的后半部分全部写成0
     */
    public void moveZeroes_2(int[] nums) {
        if (nums == null || nums.length < 2)
            return;

        int l = 0, r = 0;
        while (r < nums.length) {
            // if (nums[r] != 0 && l != r) {   不能这么写

            if (nums[r] != 0) {
                // 遇到非0数赋值给新数组指针指向的位置
                if (l != r) {
                    nums[l] = nums[r];
                }
                l++;
            }
            r++;
        }

        while (l < nums.length) { // 若新数组指针还未指向尾部，将剩余数组赋值为0
            nums[l] = 0;
            l++;
        }
    }

    /**
     * 也是同向双指针
     * 1. 左指针不断向右移动, 直到倒数第二位 (保证右指针至少还有一位)
     *    - 遇到非 0 值, 直接跳过
     *    - 遇到 0 值, 启动右指针
     *       - 从左指针下一位开始遍历知道数组末尾
     *       - 右指针不停移动, 直到遇到非 0 值, 然后与左指针互换
     *
     * 优化 - 当左指针遇到0, 右指针遍历到数组末尾时, 说明从左指针之后, 都是 0, 无需继续
     *
     * 缺点 - 右指针移动时, 可能会多次遍历
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2)
            return;

        int l = 0;
        while (l < nums.length - 1) {
            if (nums[l] == 0) {
                int r = l + 1;
                while (r < nums.length) {
                    if (nums[r] != 0) {
                        nums[l] = nums[r];
                        nums[r] = 0;
                        break;
                    }
                    r++;
                }

                if (r == nums.length) { // 优化 - l 及其右边全为 0. 不加这段也能 AC
                    break;
                }
            }
            l++;
        }
    }


    /**
     * 跟上一解法类似, 写法略有不同
     */
    public void moveZeroes_3(int[] nums) {
        if (nums == null || nums.length < 2)
            return;

        int l = 0, r = 1;
        while (l < nums.length - 1) {
            if (nums[l] == 0) {
                r = Math.max(r, l + 1);
                while (r < nums.length) {
                    if (nums[r] != 0) {
                        nums[l] = nums[r];
                        nums[r] = 0;
                        break;
                    }
                    r++;
                }
                if (r == nums.length) { // l 及其右边全为 0
                    break;
                }
            }
            l++;
        }
    }

}
