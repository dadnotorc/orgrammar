package leetcode;

/**
 * 1905. Count Sub Islands
 * Medium
 * #Array, #DFS, #BFS, #Union Find, #Matrix
 *
 * You are given two m x n binary matrices grid1 and grid2 containing only
 * 0's (representing water) and 1's (representing land).
 * An island is a group of 1's connected 4-directionally (horizontal or vertical).
 * Any cells outside the grid are considered water cells.
 *
 * An island in grid2 is considered a sub-island if there is an island
 * in grid1 that contains all the cells that make up this island in grid2.
 * 只有当 grid2 中某个岛屿的所有位置都存在与 grid1 时, 才符合条件
 *
 * Return the number of islands in grid2 that are considered sub-islands.
 *
 * Example 1:
 *
 *
 * Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * 1 1 1 0 0        1 1 1 0 0      [] [] []
 * 0 1 1 1 1        0 0 1 1 1            [] [] []
 * 0 0 0 0 0   -    0 1 0 0 0
 * 1 0 0 0 0        1 0 1 1 0      []
 * 1 1 0 1 1        0 1 0 1 0         []
 * Output: 3
 * Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
 *
 * Example 2:
 * Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * 1 0 1 0 1       0 0 0 0 0
 * 1 1 1 1 1       1 1 1 1 1
 * 0 0 0 0 0   -   0 1 0 1 0
 * 1 1 1 1 1       0 1 0 1 0
 * 1 0 1 0 1       1 0 0 0 1     []          []
 * Output: 2
 *
 *
 * Constraints:
 * m == grid1.length == grid2.length
 * n == grid1[i].length == grid2[i].length
 * 1 <= m, n <= 500
 * grid1[i][j] and grid2[i][j] are either 0 or 1.
 *
 */
public class _1905_Count_Sub_Islands {

    /**
     * DFS 做法 - 两次遍历
     * 1. 第一次遍历, 找到 grid1[i][j] = 0 且 grid2[i][j] = 1 的位置,
     *    说明 grid2 上 [i,j] 所在岛屿肯定不是 子岛屿.
     *    然后对 grid2, i, j 进行递归, 将该岛屿其他位置标记
     * 2. 第一次遍历结束后, 剩下的都是有效的 子岛屿
     *    再次遍历统计数量
     *
     * 时间 O(2 * m * n), 空间 O(m * n)
     * 想节省空间的话, 可以考虑将访问过的岛屿 从 1 改成 0
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {

        int ans = 0;
        int m = grid1.length, n = grid1[0].length;
        boolean[][] visited = new boolean[m][n];

        // 第一次遍历 - 找出 grid2 中不符合条件的位置, 并将其所在岛屿的所有位置标记
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    dfs(grid2, i, j, visited);
                }
            }
        }

        // 第二次遍历 - 统计 grid2 中剩下的岛屿, 这些都是子岛屿
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1 && !visited[i][j]) {
                    ans++;
                    dfs(grid2, i, j, visited);
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || // 越界
                visited[i][j] || // 已标记
                grid[i][j] == 0) { // 非岛屿 - 小心别忘了, 之前就漏写了
            return;
        }

        visited[i][j] = true;

        for (int[] di : directions) {
            dfs(grid, i + di[0], j + di[1], visited);
        }
    }

    private final int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};



    /**
     * todo 使用 Union Find 解决
     */
}
