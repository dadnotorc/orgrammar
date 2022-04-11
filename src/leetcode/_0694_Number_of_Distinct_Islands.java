package leetcode;

import java.util.HashSet;

/**
 * 694. Number of Distinct Islands
 * Medium
 * #Prime
 * Amazon, Apple, Facebook meta, Google, Microsoft, Uber
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of
 * 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another
 * if and only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Example 1:
 * 1 1 0 0 0
 * 1 1 0 0 0
 * 0 0 0 1 1
 * 0 0 0 1 1
 * Given the above grid map, return 1.
 *
 * Example 2:
 * 1 1 0 1 1
 * 1 0 0 0 0
 * 0 0 0 0 1
 * 1 1 0 1 1
 * Given the above grid map, return 3.
 *
 * Notice that:
 * 1 1  and    1
 * 1         1 1
 * are considered different island shapes, because we do not consider reflection / rotation.
 * Note: The length of each dimension in the given grid does not exceed 50.
 *
 * lintcode 860
 */
public class _0694_Number_of_Distinct_Islands {

    /*
    得想办法把二维矩阵中的「岛屿」进行转化，变成比如字符串这样的类型，
    然后利用 HashSet 这样的数据结构去重，最终得到不同的岛屿的个数。

    如果想把岛屿转化成字符串，说白了就是序列化，序列化说白了就是遍历嘛

    首先，对于形状相同的岛屿，如果从同一起点出发，dfs 函数遍历的顺序肯定是一样的。
    只要每次使用 dfs 遍历岛屿的时候生成这串数字进行比较，就可以计算到底有多少个不同的岛屿了

    易错点

     */

    public int numDistinctIslands(int[][] grid) {

        int m = grid.length, n = grid[0].length;
        HashSet<String> distinctIslands = new HashSet<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, visited, sb, 5); // 这里 pre_dir 的值无所谓

                    // 这里要小心别忘了, 如果 [i,j] 已经放问过, sb 会是 empty
                    if (sb.length() > 0) {
                        distinctIslands.add(sb.toString());
                    }
                }
            }
        }

        for (String s : distinctIslands) {
            System.out.println("s = [" + s + "]");
        }

        return distinctIslands.size();
    }


    private void dfs(int[][] grid, int i, int j,
                     boolean[][] visited, StringBuilder sb, int pre_dir) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length ||
                visited[i][j] ||
                grid[i][j] == 0) {
            return;
        }

        // 前序遍历位置, 进去 [i,j]
        visited[i][j] = true;
        sb.append(pre_dir).append(",");

        for (int[] di : directions) {
            dfs(grid, i + di[0], j + di[1], visited, sb, di[2]);
        }

        // 后序遍历位置, 离开 [i,j]
        sb.append(-pre_dir).append(","); // 这里取负
    }

    // 这里每个 int[] 多了一位, 表示当前移动方向
    private final int[][] directions = {
            {0, -1,  1},  // 上
            {0,  1,  2},  // 下
            {-1, 0,  3},  // 左
            {1,  0,  4}}; // 右
}
