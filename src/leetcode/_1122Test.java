package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class _1122Test {

    /**
     * Input:
     * [33,22,48,4,39,36,41,47,15,45]
     * [22,33,48,4]
     * Output:
     * [22,33,48,4,15,36,41,47,39,45]
     * Expected:
     * [22,33,48,4,15,36,39,41,45,47]
     */

    @Test
    void test1() {
        //int[] arr1 = {33,22,48,4,39,36,41,47,15,45};
        int[] arr1 = {33,22,48,4,60,36,41,47,15,45};
        int[] arr2 = {22,33,48,4};
        //int[] exp = {22,33,48,4,15,36,39,41,45,47};
        int[] exp = {22,33,48,4,15,36,41,45,47,60};
        int[] act = new _1122().relativeSortArray(arr1, arr2);

        assertArrayEquals(exp, act);
    }
}