package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class _1103test {

    @Test
    void test1() {
        int candies = 7, num_people = 4;
        int[] expected = {1,2,3,1};
        int[] actual = (new _1103().distributeCandies(candies, num_people));
        assertArrayEquals(expected, actual);
    }

    @Test
    void test2() {
        int candies = 10, num_people = 3;
        int[] expected = {5,2,3};
        int[] actual = (new _1103().distributeCandies(candies, num_people));
        assertArrayEquals(expected, actual);
    }
}
