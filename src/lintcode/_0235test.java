package lintcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class _0235test {

    @Test
    void test1() {
        List<Integer> expected = Arrays.asList(2, 5);
        List<Integer> actual = _0235.primeFactorization(10);

        Integer[] expArray = actual.toArray(new Integer[expected.size()]);
        Integer[] actArray = actual.toArray(new Integer[actual.size()]);

        assertTrue(Arrays.equals(expArray, actArray));
    }

    @Test
    void test2() {
        List<Integer> expected = Arrays.asList(2, 2, 3, 5, 11);
        List<Integer> actual = _0235.primeFactorization(660);

        Integer[] expArray = actual.toArray(new Integer[expected.size()]);
        Integer[] actArray = actual.toArray(new Integer[actual.size()]);

        assertTrue(Arrays.equals(expArray, actArray));
    }
}
