/*
Easy
#BFS, #Union Find
Amazon, Facebook, Microsoft, Uber, Google
FAQ+
 */
package lintcode;

import org.junit.Test;

/**
 * 433. Number of Islands
 *
 * Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as
 * the island. If two 1 is adjacent, we consider them in the same island. We
 * only consider up/down/left/right adjacent. Find the number of islands.
 *
 * Example 1:
 * Input:
 * [
 *   [1,1,0,0,0],
 *   [0,1,0,0,1],
 *   [0,0,0,1,1],
 *   [0,0,0,0,0],
 *   [0,0,0,0,1]
 * ]
 * Output: 3
 *
 * Example 2:
 * Input:
 * [
 *   [1,1]
 * ]
 * Output: 1
 */
public class _0433_NumberOfIslands {

    /**
     * 将已访问的海岛沉下去, 之后再访问的时候就可以跳过
     *
     * 易错点:
     * 1. 在主function里不要沉海岛, 在recursion function里沉
     */
    public int numIslands_2(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int ans = 0;

        // boolean[][] map = copyMap(grid); // 将当前海图复制

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]) { // (i,j) is island
                    ans++;

                    // 注意, 不可以在这里沉海岛, 不然进去recursion时, 立马会退出

                    findNeighbor(grid, i, j);
                }
            }
        }

        return ans;
    }

    // 将当前海岛沉下去
    public void findNeighbor(boolean[][] grid, int x, int y) {
        if (!isValid(grid, x, y) || !grid[x][y]) // 越界 或者 当前是海面
            return;

        grid[x][y] = false; // 沉下去

        for (int i = 0; i < DIR.length; i++) {
            findNeighbor(grid, x + DIR[i][0], y + DIR[i][1]);
        }
    }



    /**
     * 将已访问的海岛 mark as visited
     */
    private static final int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int islands = 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] isVisited = new boolean[n][m]; // all initialized as false

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!isVisited[i][j]) {
                    if (!grid[i][j]) {
                        isVisited[i][j] = true;
                    } else { // find the rest of the island and mark as visited
                        islands++;
                        // 注意这里不能修改isVisited, 因为会导致markVisited时, 提前退出
                        markVisited(grid, isVisited, i, j);
                    }
                }
            }
        }

        return islands;
    }

    private void markVisited(boolean[][]grid, boolean[][] isVisited, int i, int j) {
        if (!isValid(grid, i, j)  || isVisited[i][j])
            return;

        isVisited[i][j] = true;

        if (!grid[i][j]) {
            return;
        }

        // current on island, continue explain and mark the rest of the island as visited
        for (int[] dir : DIR) {
            markVisited(grid, isVisited, i + dir[0], j + dir[1]);
        }
    }

    private boolean isValid(boolean[][] grid, int i, int j) {
        return (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length);
    }



    @Test
    public void test1() {
        boolean[][] grid = {
                {true, true, false, false, false},
                {false, true, false, false, true},
                {false, false, false, true, true},
                {false, false, false, false, false},
                {false, false, false, false, true}};
        org.junit.Assert.assertEquals(3, numIslands_2(grid));
    }
}
