package interviews;

import org.junit.Test;

/**
 * A scenario about student badges (或者学生选课)
 *
 * Given an array of -1 or 1, such as [-1,-1,1,-1]. Return the maximum subarray length with a product of 1 (positive)
 *
 * 2 <= nums.length
 * nums[i] can only be -1 or 1
 *
 * Example
 * nums = [-1,-1,1,1,-1], return 4, since index 0 to 3 have the max length with product equal to 1
 *
 *
 * 如果 nums 的值可以包含其他值, 例如 nums = [0,1,-2,-3,-4],
 * 那就要参考 leetcode 1567. Maximum Length of Subarray With Positive Product
 */
public class AMZN_2022_Max_Length_Subarray_Positive_Product {

    /**
     * 1. 遍历数组
     *    - 记录 第一个 和 最后一个 -1 的下标
     *    - 统计 -1 的个数
     * 2. 如果 -1 的个数为
     *    - 偶数 - 直接返回整个数组的长度
     *    - 奇数 - 说明必须舍去下面两者之一, 比较选较长值
     *       - 第一个 -1 即之前的
     *       - 最后一个 -1 即之后的
     */
    public int getMaxLen(int[] nums) {

        int first = -1; // 第一个 -1 的下标
        int last = -1; // 最后一个 -1 的下标
        int count = 0; // -1 的个数
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == -1) {
                count++;

                if (first == -1) {
                    first = i;
                } else {
                    last = i;
                }
            }
        }

        if ((count & 1) == 0) { // 注意 & 操作需要一层自己的括号
            // 偶数
            return n;
        }

        // 奇数
        return Math.max(
                last, // 舍弃 last 即之后的
                n - 1 - first // 舍去 first 即之前的
        );
    }

    @Test
    public void test1() {
        int[] nums = new int[] {1,1,1,1,1,-1,-1,-1,1,1,1,1};
        org.junit.Assert.assertEquals(7, getMaxLen(nums));

        nums = new int[] {-1,-1,-1,-1,-1,1};
        org.junit.Assert.assertEquals(5, getMaxLen(nums));

        nums = new int[] {-1,-1,1,1,-1};
        org.junit.Assert.assertEquals(4, getMaxLen(nums));
    }

}
