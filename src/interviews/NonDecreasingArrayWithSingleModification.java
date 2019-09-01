package interviews;

import org.junit.Assert;
import org.junit.Test;

/**
 * You are given an array of integers in an arbitrary order.
 * Return whether or not it is possible to make the array
 * non-decreasing by modifying at most 1 element to any value.
 *
 * We define an array is non-decreasing if array[i] <= array[i + 1]
 * holds for every i (1 <= i < n).
 *
 * Example:
 * [13, 4, 7] should return true, since we can modify 13 to
 * any value 4 or less, to make it non-decreasing.
 * [13, 4, 1] however, should return false, since there is no way
 * to modify just one element to make the array non-decreasing.
 *
 * Here is the function signature:
 *
 * def check(lst):
 *   # Fill this in.
 *
 * print check([13, 4, 7])
 * # True
 * print check([5,1,3,2,5])
 * # False
 *
 * Challenge: Can you find a solution in O(n) time?
 *
 * Microsoft
 */
public class NonDecreasingArrayWithSingleModification {

    public boolean check(int[] nums) {
        if (nums == null || nums.length < 2)
            return false;

        int modCount = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i+1])
                modCount++;

            if (modCount > 1)
                return false;
        }
        return true;
    }

    @Test
    public void test1() {
        int[] nums = {13,4,7};
        Assert.assertTrue(check(nums));
    }

    @Test
    public void test2() {
        int[] nums = {5,1,3,2,5};
        Assert.assertFalse(check(nums));
    }
    @Test
    public void test3() {
        int[] nums = {5};
        org.junit.Assert.assertFalse(check(nums));
    }

    @Test
    public void test4() {
        int[] nums = {5,1,3,4,2};
        Assert.assertFalse(check(nums));
    }

    @Test
    public void test5() {
        int[] nums = {5,1,3,3,5};
        Assert.assertTrue(check(nums));
    }
}
