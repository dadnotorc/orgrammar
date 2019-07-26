package lintcode.recursion;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class _0015Test {

    @Test
    void test1() {
        int[] nums = {1};
        List expected = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        expected.add(l1);
        List<List<Integer>> actual = new _0015().permute_dfs(nums);
        assertTrue(expected.equals(actual));
    }

    @Test
    void test2() {
        int[] nums = {1,2,3};
        List expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        expected.add(new ArrayList<>(Arrays.asList(1, 3, 2)));
        expected.add(new ArrayList<>(Arrays.asList(2, 1, 3)));
        expected.add(new ArrayList<>(Arrays.asList(2, 3, 1)));
        expected.add(new ArrayList<>(Arrays.asList(3, 1, 2)));
        expected.add(new ArrayList<>(Arrays.asList(3, 2, 1)));
        List<List<Integer>> actual = new _0015().permute_bfs(nums);
        assertTrue(expected.size() == actual.size());

        // TODO how to compare List<List<Integer>>

        //assertArrayEquals(expected.toArray(new Integer[expected.size()]), actual.toArray(new Integer[actual.size()]));

    }

}