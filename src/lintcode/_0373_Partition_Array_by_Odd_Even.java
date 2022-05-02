package lintcode;

/**
 * 373 · Partition Array by Odd and Even
 * Easy
 * #Two Pointers, #Opposite Direction Two Pointers, #Array
 *
 * Partition an integers array into odd number first and even number second.
 *
 * The answer is not unique. All you have to do is give a valid answer.
 *
 * Example
 * Example 1:
 *
 * Input: [1,2,3,4]
 * Output: [1,3,2,4]
 * Example 2:
 *
 * Input: [1,4,2,3,5,6]
 * Output: [1,3,5,4,2,6]
 * Challenge
 * Do it in-place.
 */
public class _0373_Partition_Array_by_Odd_Even {

    /**
     * 另一种写法
     */
    public void partitionArray_2(int[] nums) {
        if (nums == null || nums.length < 2) { return; }

        int l = 0, r = nums.length - 1;

        while (l < r) {
            while ((nums[l] & 1) != 0) {
                l++;
            }

            while ((nums[r] & 1) == 0) {
                r--;
            }

            if (l < r) { // 这里要小心, 别忘了边界判断
                swap(nums, l, r);
                l++;
                r--;
            }
        }
    }

    /**
     * 首尾双指针, 奇数放前, 偶数放后
     */
    public void partitionArray(int[] nums) {
        if (nums == null || nums.length < 2) { return; }

        int l = 0, r = nums.length - 1;

        while (l < r) {
            if ((nums[l] & 1) != 0) { // l 处为奇数, 直接后移
                l++;
            } else if ((nums[r] & 1) == 0) { // r 处为偶数, 直接前移
                r--;
            } else {
                swap(nums, l, r);
                l++;
                r--;
            }
        }
    }

    public void swap(int[] nums, int l, int r) {
        int xor = nums[l] ^ nums[r];
        nums[l] ^= xor;
        nums[r] ^= xor;
    }
}
