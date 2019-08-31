package interviews;

import org.junit.Test;

import java.util.Arrays;


public class FindNonDuplicateNumber {
    /**
     * Given a list of numbers, where every number shows up twice
     * except for one number, find that one number.
     *
     * Example:
     * Input: [4, 3, 2, 4, 1, 3, 2]
     * Output: 1
     *
     * Challenge: Find a way to do this using O(1) memory.
     *
     * Facebook
     */
    public int singleNumber(int[] nums) {
        if (nums == null || (nums.length & 1) == 0) //数组长度必须为奇数
            return 0;
        if (nums.length == 1)
            return nums[0];

        // 先sort, 然后遍历, 寻找那个独立存在的数字
        // time: O(nlogn) + O(n) = O(nlogn)
        // space: O(1)
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i = i + 2) {
            // 注意别忘了下列判断, 该数字存在于数组末端, 此时i+1会out of boundary
            if (i == nums.length - 1)
                return nums[i];

            if (nums[i] != nums[i + 1])
                return nums[i];
        }

        return 0; // shouldn't happen
    }

    @Test
    public void test1() {
        int[] nums = {4, 3, 2, 4, 1, 3, 2};
        org.junit.Assert.assertEquals(1, singleNumber(nums));
    }

    @Test
    public void test2() {
        int[] nums = {4, 3, 2, 4, 1, 3, 2, 1, 9};
        org.junit.Assert.assertEquals(9, singleNumber(nums));
    }

    @Test
    public void test3() {
        int[] nums = {9, 4, 3, 2, 4, 1, 3, 2, 1};
        org.junit.Assert.assertEquals(9, singleNumber(nums));
    }
}
