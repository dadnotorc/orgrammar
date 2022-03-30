package leetcode;

/**
 * 778. Swim in Rising Water
 *
 * You are given an n x n integer matrix grid where
 * each value grid[i][j] represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t.
 * You can swim from a square to another 4-directionally adjacent square
 * if and only if the elevation of both squares individually are at most t.
 * You can swim infinite distances in zero time.
 * Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1)
 * if you start at the top left square (0, 0).
 *
 * Example 1:
 * Input: grid = [[0,2],[1,3]]
 * 0 2
 * 1 3
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 *
 * Example 2:
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 *  0   1   2   3   4        0 ->  1 ->  2 ->  3 ->  4
 * 24  23  22  21   5       24    23    22    21     5
 * 12  13  14  15  16   ->  12 <- 13 <- 14 <- 15 <- 16
 * 11  17  18  19  20       11    17    18    19    20
 * 10   9   8   7   6       10 ->  9 ->  8 ->  7 ->  6
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 *
 * Constraints:
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] < n^2
 * Each value grid[i][j] is unique.
 */
public class _0778_Swim_in_Rising_Water {

    /**
     * 暴力 - 找出所有的 path, 找出每条 path 中的最大值, 选择这些最大值中的最小的那一位
     * 使用个 boolean[][] 记录当前节点是否已经访问过
     *
     * 时间 O(4^ (n^2)) - 每个节点 可以选择 4 个方向, 共有 n^2 个节点
     * 空间 O(n^2)
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];

        return dfs_bf(grid, visited, 0, 0, -1);
    }

    // i, j - 当前座标, max_cur_path - max in current path
    public int dfs_bf(int[][] grid, boolean[][] visited, int i , int j, int max_cur_path) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length || visited[i][j]) {
            // 出界 或者 已经访问过
            return Integer.MAX_VALUE;
        }

        if (i == j && i == grid.length - 1) {
            return Math.max(max_cur_path, grid[i][j]); // 别忘了 最后这步也要比较
        }

        visited[i][j] = true;
        max_cur_path = Math.max(max_cur_path, grid[i][j]);
        int min = dfs_bf(grid, visited, i + 1, j, max_cur_path);
        Math.min(min, dfs_bf(grid, visited, i - 1, j, max_cur_path));
        Math.min(min, dfs_bf(grid, visited, i, j + 1, max_cur_path));
        Math.min(min, dfs_bf(grid, visited, i, j - 1, max_cur_path));
        visited[i][j] = false;
        return min;
    }
}
