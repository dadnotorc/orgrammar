package lintcode.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class _0838Test {

    @Test
    void test1() {
        int[] nums = {1,1,1};
        int k = 2;
        int expected = 2;
        int actual = new _0838().subarraySumEqualsK(nums, k);
        assertEquals(expected, actual);

        nums = new int[]{1,1,1};
        int actual_s = new _0838().subarraySumEqualsK_s(nums, k);
        assertEquals(expected, actual_s);
    }

    @Test
    void test2() {
        int[] nums = {2,1,-1,1,2};
        int k = 3;
        int expected = 4;
        int actual = new _0838().subarraySumEqualsK(nums, k);
        assertEquals(expected, actual);

        nums = new int[]{2,1,-1,1,2};
        int actual_s = new _0838().subarraySumEqualsK_s(nums, k);
        assertEquals(expected, actual_s);
    }

    @Test
    void test3() {
        int[] nums = {-4,-1,-6,16,13,2,-1,-4,6,6,-9,13,3,-6,-6,16,8,-10,2,1,0,8,6,16,11,0,10,-6,-5,16};
        int k = 12;
        int expected = 5;
        int actual = new _0838().subarraySumEqualsK(nums, k);
        assertEquals(expected, actual);

        nums = new int[]{-4,-1,-6,16,13,2,-1,-4,6,6,-9,13,3,-6,-6,16,8,-10,2,1,0,8,6,16,11,0,10,-6,-5,16};
        int actual_s = new _0838().subarraySumEqualsK_s(nums, k);
        assertEquals(expected, actual_s);
    }

}