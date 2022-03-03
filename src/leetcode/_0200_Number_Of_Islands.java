package leetcode;

/**
 * 200. Number of Islands
 * Medium
 * #DFS, #BFS, #Union Find
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input:
 * 1 1 1 1 0
 * 1 1 0 1 0
 * 1 1 0 0 0
 * 0 0 0 0 0
 * Output: 1
 *
 * Example 2:
 * Input:
 * 1 1 0 0 0
 * 1 1 0 0 0
 * 0 0 1 0 0
 * 0 0 0 1 1
 * Output: 3
 */
public class _0200_Number_Of_Islands {


    /**
     * Union Find
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int ans = 0;
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!isVisited[i][j] && grid[i][j] == '1') {
                    ans++;
                    exploreIsland(grid, isVisited, i, j);
                }
            }
        }

        return ans;
    }

    class UF {
        private int count;
        private int[] parent;
        private int[] size;

        public UF(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) { return; }

            count--;

            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }

            return x;
        }

        public int getCount() { return count; }

        public boolean isConnected(int p, int q) { return find(p) == find(q); }
    }





    /**
     * DFS
     * 易错点：
     * 1. 只在exploreIsland里将(x,y)点设为已访问, 不要在主循环里设
     * 2. 先判断是否有效, 再判断是否访问过, 否则会越界
     */
    public int numIslands_dfs(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int ans = 0;
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!isVisited[i][j] && grid[i][j] == '1') {
                    ans++;
                    exploreIsland(grid, isVisited, i, j);
                }
            }
        }

        return ans;
    }

    private int[][] coord = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private void exploreIsland(char[][] grid, boolean[][] isVisited, int x, int y) {
        if (!isValid(grid, x, y) || isVisited[x][y]) { // 先判断是否valid， 再判断是否访问过， 否则会出现越界
            return;
        }

        isVisited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            exploreIsland(grid, isVisited, x + coord[i][0], y + coord[i][1]);
        }
    }

    private boolean isValid(char[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1';
    }
}
