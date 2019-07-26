package lintcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class _0611test {

    @Test
    void test1() {
        boolean[][] grid = new boolean[][]{{false,false,false}, {false,false,false}, {false,false,false}};
        _0611.Point source = new _0611.Point(2, 0);
        _0611.Point dest = new _0611.Point(2, 2);

        int result = _0611.shortestPath(grid, source, dest);
        assertTrue(result == 2);
    }

    @Test
    void test2() {
        boolean[][] grid = new boolean[][]{{false,true,false}, {false,false,true}, {false,false,false}};
        _0611.Point source = new _0611.Point(2, 0);
        _0611.Point dest = new _0611.Point(2, 2);

        int result = _0611.shortestPath(grid, source, dest);
        assertTrue(result == -1);
    }
}
