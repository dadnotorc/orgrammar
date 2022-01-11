package interviews;

import org.junit.Test;

/**
 * A scenario about student badges (学习 AWS courses and earn electronic learning badge). Before signing up for courses,
 * a student assigns each badge a value based on interest:
 * 1 means interested
 * -1 means not intersted
 *  *
 * Given an array of -1 or 1, such as [-1,-1,1,-1]. Find a subarray of maximum length such that the product of all
 * the elements in the array is 1 ( 乘积为正 )
 *
 * Constraints
 * 2 <= nums.length <= 10^5
 * nums[i] can only be -1 or 1
 * Guarantee at least 1 non-empty subarray in the answer
 *
 * Example
 * nums = [-1,-1,1,1,-1], return 4, since index 0 to 3 have the max length with product equal to 1
 *
 *
 * 如果 nums 的值可以包含其他值, 例如 nums = [0,1,-2,-3,-4],
 * 那就要参考 leetcode 1567. Maximum Length of Subarray With Positive Product
 */
public class AMZN_2022_Max_Length_Subarray_Positive_Product {

    /**  13 /15 passed
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
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int first = -1; // 第一个 -1 的下标
        int last = -1; // 最后一个 -1 的下标
        int count = 0; // -1 的个数
        int n = nums.length;

        System.out.println("size = " + n);

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

        System.out.println("first = " + first);
        System.out.println("last = " + last);

        if ((count & 1) == 0) { // 注意 & 操作需要一层自己的括号
            // 偶数
            return n;
        }

        if (count == 1) { // 特殊情况, 只有一个 -1, 那就返回 -1 之前的长度
            return first;
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

        nums = new int[] {1,1,-1,1};
        org.junit.Assert.assertEquals(2, getMaxLen(nums));
    }
}
