package lintcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class _0402Test {

    @Test
    void test0() {
        int[] a = {0};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 0));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test1() {
        int[] a = {-3, 1, 3, -3, 4};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 4));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test2() {
        int[] a = {0, 1, 0, 1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 3));
        List<Integer> act = new _0402().continuousSubarraySum(a);

        System.out.println(act.size());
        System.out.println(act.get(0));
        System.out.println(act.get(1));

        //assertTrue(exp.equals(act));
    }

    @Test
    void test3() {
        int[] a = {-4,7,-8,6,-5,-3,4};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 1));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test4() {
        int[] a = {-3,1,3,-4,7};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 4));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test5() {
        int[] a = {1,0,0,1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 3));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test6() {
        int[] a = {-1,0,0,1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(1, 3));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test7() {
        int[] a = {0,0,0,1};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0, 3));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    /**
     * 注意 这个case
     */
    @Test
    void test8() {
        int[] a = {-1,-2,-3,-100,1,2,3,100};
        List<Integer> exp = new ArrayList<>(Arrays.asList(4,7));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test9() {
        int[] a = {-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22};
        List<Integer> exp = new ArrayList<>(Arrays.asList(15,15));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test10() {
        int[] a = {1,0,0,0};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0,0));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }

    @Test
    void test11() {
        int[] a = {};
        List<Integer> exp = new ArrayList<>(Arrays.asList(0,0));
        List<Integer> act = new _0402().continuousSubarraySum(a);
        assertTrue(exp.equals(act));
    }
}