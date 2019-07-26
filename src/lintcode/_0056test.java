package lintcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class _0056test {

    @Test
    void test1() {
        int[] numbers = {2,7,11,15};
        int target = 9;
        int[] expected = {0,1};
        int[] actualHashMap = (new _0056().twoSumHashMap(numbers, target));
        int[] actualTwoPointers = (new _0056().twoSumTwoPointers(numbers, target));
        assertArrayEquals(expected, actualHashMap);
        assertArrayEquals(expected, actualTwoPointers);
    }

    @Test
    void test2() {
        int[] numbers = {15,2,7,11};
        int target = 9;
        int[] expected = {1,2};
        int[] actualHashMap = (new _0056().twoSumHashMap(numbers, target));
        int[] actualTwoPointers = (new _0056().twoSumTwoPointers(numbers, target));
        assertArrayEquals(expected, actualHashMap);
        assertArrayEquals(expected, actualTwoPointers);
    }
}
