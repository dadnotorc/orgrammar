package lintcode;

/**
 * 433. Number of Islands
 * Easy
 * Amazon, Facebook, Microsoft, Uber, Google
 *
 * Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as
 *  the island. If two 1 is adjacent, we consider them in the same island.
 *  We only consider up/down/left/right adjacent.
 *
 * Find the number of islands.
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
}
