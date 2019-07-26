package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class _1089test {

    @Test
    void test1() {
        int[] arr = new int[]{1,0,2,3,0,4,5,0};
        int[] expected = {1,0,0,2,3,0,0,4};
        new _1089().duplicateZeros(arr);
        assertArrayEquals(arr, expected);
    }

    @Test
    void test2() {
        int[] arr = new int[]{1,2,3};
        int[] expected = {1,2,3};
        new _1089().duplicateZeros(arr);
        assertArrayEquals(arr, expected);
    }

}
