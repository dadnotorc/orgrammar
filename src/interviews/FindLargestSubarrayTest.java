package interviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FindLargestSubarrayTest {

    @Test
    void test0() {
        int[] a = {1, -2, 10, -1, 20, -100};
        int[] exp = {10, -1, 20};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test1() {
        int[] a = {0};
        int[] exp = {0};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test2() {
        int[] a = {-3, 1, 3, -3, 4};
        int[] exp = {1, 3, -3, 4};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test3() {
        int[] a = {0, 1, 0, 1};
        int[] exp = {0, 1, 0, 1};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test4() {
        int[] a = {-4,7,-8,6,-5,-3,4};
        int[] exp = {7};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test5() {
        int[] a = {-3,1,3,-4,7};
        int[] exp = {1,3,-4,7};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test6() {
        int[] a = {1,0,0,1};
        int[] exp = {1,0,0,1};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test7() {
        int[] a = {-1,0,0,1};
        int[] exp = {0,0,1};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test8() {
        int[] a = {0,0,0,1};
        int[] exp = {0,0,0,1};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    /**
     * 注意 这个case
     */
    @Test
    void test9() {
        int[] a = {-1,-2,-3,-100,1,2,3,100};
        int[] exp = {1,2,3,100};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test10() {
        int[] a = {-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22};
        int[] exp = {-22};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test11() {
        int[] a = {1,0,0,0};
        int[] exp = {1};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

    @Test
    void test12() {
        int[] a = {};
        int[] exp = {};
        int[] act = new FindLargestSubarray().continuousSubarraySum(a);
        assertArrayEquals(exp, act);
    }

}