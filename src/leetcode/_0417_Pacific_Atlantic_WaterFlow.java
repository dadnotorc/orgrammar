package leetcode;

import java.util.*;

/**
 * 417. Pacific Atlantic Water Flow
 * Medium
 * #Array, #DFS, #BFS, #Matrix
 *
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges,
 * and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights
 * where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly
 * north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height.
 * Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from
 * cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 * 题目的意思是, 找到所有节点, 既能达到 top/left 也能达到 bottom/right
 *
 * Example 1:
 * Pacific   ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 *
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]] -> 这是满足条件的所有座标
 *
 * Example 2:
 * Input: heights = [[2,1],[1,2]]
 * Output: [[0,0],[0,1],[1,0],[1,1]]
 *
 *
 * Constraints:
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 10^5
 */
public class _0417_Pacific_Atlantic_WaterFlow {

    /*
    暴力解法 - 从每个点出发, 使用DFS, 尝试能否到达 pacific and/or atlantic - O(4^(m*n))
    重点是, 对每个点做完 DFS 后, 要回溯


     */

    /**
     * DFS - 反向推, 从 pacific / atlantic 开始, 看能去到哪些节点, 最后将这些节点取交集
     *
     * DFS 时, 每个节点最多被访问 4 次 (上下左右), 所有 O(4 * m * n) ~ O(m * n), 最后求交集也是 O(m * n)
     * 空间 O(m * n)
     */
    public List<List<Integer>> pacificAtlantic_DFS(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();

        if (heights == null || heights.length == 0 || heights[0].length == 0) { return ans; }

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] reachableFromPacific = new boolean[m][n];
        boolean[][] reachableFromAtlantic = new boolean[m][n];

        // 最上行 和 最左列 一定能到达 P, 所以反向推, 我们要从 最上行 和 最左列 开始移动, 寻找能从 P 到达的节点
        // 同理, 要从最下行 和 最右列开始移动, 寻找能从 A 到达的节点

        // 其实 h 从 0 开始, 是选定最低的陆地为基点

        for (int i = 0; i < m; i++) {
            dfs(heights, reachableFromPacific, i, 0, 0); // left column
            dfs(heights, reachableFromAtlantic, i, n - 1, 0); // right column
        }

        for (int j = 0; j < n; j++) {
            dfs(heights, reachableFromPacific, 0, j, 0); // top row
            dfs(heights, reachableFromAtlantic, m - 1, j, 0); // bottom row
        }

        findCommon(ans, reachableFromPacific, reachableFromAtlantic);

        return ans;
    }

    private void dfs(int[][] heights, boolean[][] reachable, int x, int y, int h) {
        if (x < 0 || x >= heights.length || y < 0 || y >= heights[0].length) { return; } // [x,y] 座标越界

        if (reachable[x][y] || heights[x][y] < h) { return; } // [x,y]座标已访问 或者 值不如前一位的高 (即无法从[x,y]流向前一位)

        reachable[x][y] = true;

        dfs(heights, reachable, x + 1, y, heights[x][y]);
        dfs(heights, reachable, x - 1, y, heights[x][y]);
        dfs(heights, reachable, x, y + 1, heights[x][y]);
        dfs(heights, reachable, x, y - 1, heights[x][y]);
    }


    private void findCommon(List<List<Integer>> ans, boolean[][] p, boolean[][] a) {
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[0].length; j++) {
                if (p[i][j] && a[i][j]) {
                    ans.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
    }


    /**
     * BFS - 也是 O(m * n)
     */
    public List<List<Integer>> pacificAtlantic_BFS(int[][] heights) {
        List<List<Integer>> ans = new LinkedList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) { return ans; }

        int m = heights.length, n = heights[0].length;

        boolean[][] p = new boolean[m][n];
        boolean[][] a = new boolean[m][n];

        Queue<int[]> queueP = new LinkedList<>();
        Queue<int[]> queueA = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            queueP.offer(new int[] {i, 0}); // 最左列
            queueA.offer(new int[] {i, n - 1}); // 最右列
        }

        for (int j = 0; j < n; j++) {
            queueP.offer(new int[] {0, j}); // 最上行
            queueA.offer(new int[] {m - 1, j}); // 最下行
        }

        bfs(heights, queueP, p);
        bfs(heights, queueA, a);

        findCommon(ans, p, a);

        return ans;
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] r) {
        while (!queue.isEmpty()) {
            // 这里可以省略获取当前 queue 的 size
            int[] cur = queue.poll();
            r[cur[0]][cur[1]] = true; // 别忘了更新 reachable 这个 boolean array

            for (int[] di : direction) {
                int x = cur[0] + di[0];
                int y = cur[1] + di[1];

                if (x < 0 || x >= heights.length || y < 0 || y >= heights[0].length) { continue; }

                if (r[x][y] || heights[x][y] < heights[cur[0]][cur[1]]) { continue; }

                queue.offer(new int[] {x, y});
            }
        }
    }

    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};






    /**
     * DP - O(m * n * x) 这个 x 值不确定, 不知道要循环多少次才能达到稳定的 DP
     *
     * 如果前一个点能到达 P, 且当前点的值更高, 则当前点也能到达 P - 用 1 表示 (即二进制 01)
     * 如果前一个点能到达 A, 且当前点的值更高, 则当前点也能到达 A - 用 2 表示 (即 10)
     * 如果当前点既能到达 P, 也能到达 A - 用 3 表示 (即 11)
     * 如果当前点哪里都去不了 - 用 0 表示 (即 00)
     *
     * 最上行 和 最左列 一定能到达 P, 所以它们的起始值为 1
     * 最下行 和 最右列 一定能到达 A, 所以它们的起始值为 2
     */
    public List<List<Integer>> pacificAtlantic_DP(int[][] heights) {
        List<List<Integer>> ans = new LinkedList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) { return ans; }

        int m = heights.length, n = heights[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] |= 1; // 最左列
            dp[i][n - 1] |= 2; // 最右列
        }

        for (int j = 0; j < n; j++) {
            dp[0][j] |= 1; // 最上行
            dp[m-1][j] |= 2; // 最下行
        }

        while (true) {
            // 只有循环中还有改动, 就要继续检查, 知道没有任何变化后才结束
            boolean changed = false;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] di : direction) {
                        int x = i + di[0];
                        int y = j + di[1];

                        // 如果邻居 [x,y] 的值较大, 则无法从当前 [i,j] 通过邻居继续前进, 所以要跳过
                        // 如果当前 [i,j] 的值不会受临时 [x,y] 的值影响, 即无法通过邻居造成改变, 所有也要跳过

                        if (x < 0 || x >= m || y < 0 || y >= n
                                || heights[x][y] > heights[i][j] // 注意是 >
                                || ((dp[i][j] | dp[x][y]) == dp[i][j])) {
                            continue;
                        }

                        dp[i][j] |= dp[x][y];
                        changed = true;
                    }
                }
            }

            if (!changed) { break; }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 3) {
                    ans.add(new LinkedList<>(Arrays.asList(i, j)));
                }
            }
        }

        return ans;
    }
}
