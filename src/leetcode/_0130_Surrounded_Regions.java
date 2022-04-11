package leetcode;

/**
 * 130. Surrounded Regions
 * Medium
 * #DFS, #BFS, #Union Find
 *
 * Given an m x n matrix board containing 'X' and 'O',
 * capture all regions that are 4-directionally surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example 1
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * Explanation:
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border
 * of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not
 * connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if
 * they are adjacent cells connected horizontally or vertically.
 *
 *
 * Example 2:
 * Input: board = [["X"]]
 * Output: [["X"]]
 *
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */
public class _0130_Surrounded_Regions {

    /**
     * Union Find
     */
    class UnionFind {
        private int count; // 连通分量的个数, 不过此题无需用到此值
        private int[] parent; // x节点的父节点为 parent[x]
        private int[] size; // x节点所在的树的大小

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                // 初始化时, 每棵树只有一个节点, 即本身. 一共有n颗树
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 将p与q所在的两个树连通
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) { return; }

            // 将小树并入大树下
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }

            count--; // 别忘递减连通分量
        }

        // 判断p与q所在的两个树是否连通
        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        // 找到x所在树的根节点
        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        // 获得连通分量的个数
        public int getCount() {
            return this.count;
        }
    }
    /**
     * 1. 创建长度为 n * m + 1的UnionFind. 最后一位作为dummy节点, 用于关联边界上的'O'
     * 2. 遍历数组, 将相邻的'O'连通.
     *    如果处于边界或者与边界上的'O'相连, 则将其与dummy相连
     * 3. 再次遍历数组 (这次可跳过边界节点)
     *    将不与dummy相连的'O'节点改为'X'
     *
     * 易错点:
     * 1. 计算index的时候要小心, 假设n行m列, 当前节点[i][j]的index = i * m + j. 注意是乘以m, 而不是n
     * 2. 不管当前节点是否处于边界上, 都需要连通其与邻居'O'
     * 3. 上一层的邻居与当前节点index相差 m, 而不是 n
     */
    public void solve_UnionFind(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;
        int dummy = m * n; // connect 'O' on edges with this dummy node

        UnionFind uf = new UnionFind(m * n + 1); // 多一个dummy node

        // 连通'O'节点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    continue;
                }

                int index = i * n + j; // 当前node的index, 注意是乘以 n, 而不是 m

                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) { // 将处于边界上的'O'与dummy连通
                    uf.union(index, dummy);
                }

                // 不可以将下列的邻居判断放入else中. 因为那样的话, 遇到最右列或者最下层的'O'时, 无法将其与其上或者左的邻居'O'连通

                // 连通不在边界上的'O'与其邻居'O'
                // 这里只检查左和上的邻居, 因为后和下的邻居在之后的循环中会遇到, 无需多次处理
                if (i > 0 && board[i - 1][j] == 'O') {
                    uf.union(index, index - n); // 上一层的邻居与当前节点index相差m. 注意, 相差m, 而不是n
                }
                if (j > 0 && board[i][j - 1] == 'O') {
                    uf.union(index, index - 1); // 左边的邻居与当前节点index相差1
                }
            }
        }

        // 将所有不与dummy相连的'O'节点改为'X'
        // 注意, 边界从1到n-2即可, 无需访问边界上的节点
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                // 当前节点index = i * n + j, 注意是乘以 n, 而不是 m
                if (board[i][j] == 'O' && !uf.isConnected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }




    /**
     * 另一种解法 - 两次遍历
     * 第一次遍历 - 找出边界上的'O', 使用DFS找出相连的'O', 将其标记 (标记说明其与边界上的'O'相连)
     * 第二次遍历 - 翻转所有未标记的'O'
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }

        boolean[][] isMarked = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
                    if (board[i][j] == 'O') {
                        mark(board, isMarked, i, j);
                    }
                }
            }
        }

        for (int i = 1; i < board.length - 1; i++) { // 可以跳过第一行/列 和 最后一行/列
            for (int j = 1; j < board[0].length - 1; j++) {
                if (board[i][j] == 'O' && !isMarked[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void mark(char[][] board, boolean[][] isMarked, int i, int j) {
        // 注意 这里需要判断:
        // 1. 是否越界
        // 2. 当前是否是'X'
        // 3. 如果是'O', 是否已被标记 (说明跟边界的'O'相连)
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] =='X' || isMarked[i][j]) {
            return;
        }

        isMarked[i][j] = true;

        mark(board, isMarked, i + 1, j);
        mark(board, isMarked, i - 1, j);
        mark(board, isMarked, i, j + 1);
        mark(board, isMarked, i, j - 1);
    }
}
