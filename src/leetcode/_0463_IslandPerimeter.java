package leetcode;

/**
 * 463. Island Perimeter
 *
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
 * and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island).
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100.
 * Determine the perimeter of the island.
 *
 * Example:
 * Input:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 * Output: 16
 * perimeter是指岛屿的边沿与水相邻的边, 比如例题中, 每个点的边沿为
 * 0 3 0 0
 * 3 0 3 0
 * 0 2 0 0
 * 3 2 0 0   所以总和为16
 */
public class _0463_IslandPerimeter {

    /**
     * 简化
     */
    public int islandPerimeter_2(int[][] grid) {
        int res = 0;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 0) {
                    // 每个if无需判断两端边界, 只需看变化的一段是否越界
                    if (i == 0 || grid[i - 1][j] == 0) { res++; }
                    if (i == n - 1 || grid[i + 1][j] == 0) { res++; }
                    if (j == 0 || grid[i][j - 1] == 0) { res++; }
                    if (j == m - 1 || grid[i][j + 1] == 0) { res++; }
                }
            }
        }
        return res;
    }




    /**
     * 易错点:
     * 1. 特别小心手误...
     */
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    res += checkEdge(grid, i, j);
                }
            }
        }
        return res;
    }

    private int checkEdge(int[][] grid, int x, int y) {
        int edge = 0;
        for (int i = 0; i < 4; i++) {
            int neighborX = x + direction[i][0]; // 注意手误, 别把direction写成grid
            int neighborY = y + direction[i][1];
            if (!isValid(grid, neighborX, neighborY) || grid[neighborX][neighborY] == 0) { // 注意手误, 分别是neighborX与neighborY
                edge++;
            }
        }
        return edge;
    }

    private boolean isValid(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    private int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
