package leetcode;

/**
 * 547. Number of Provinces (原名 friend circles)
 * Medium
 * #Union Find, #DFS, #BFS, #Graph
 *
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected  with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where
 * isConnected[i][j] = 1 if the ith city and the jth city are directly connected,
 * and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 * Example 1:
 * Input: isConnected = [[1,1,0],
 *                       [1,1,0],
 *                       [0,0,1]]
 * Output: 2
 * Explanation: province 1 = 0th and 1th; province 2 = 2nd
 *
 * Example 2:
 * Input: isConnected = [[1,0,0],
 *                       [0,1,0],
 *                       [0,0,1]]
 * Output: 3
 * Explanation: no one is connected with any one else
 *
 * Example:
 * Input:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 *
 * Note:
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class _0547_Number_Provinces {

    /**
     * Union Find
     */
    class UnionFind{
        private int count; // 连通分量的个数
        private int[] root; // x 节点的根节点为 root[x]
        private int[] size; // x 节点所在的树的大小

        public UnionFind(int n) {
            // 初始化时, 每棵树只有一个节点, 即本身. 一共有n颗树
            this.count = n;
            root = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i; // 每个节点的根节点就是自己
                size[i] = 1; // 每棵树的大小为 1
            }
        }

        // 找到 x 所在树的根节点
        public int find(int x) {
            // 递归写法
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);

            // 循环的写法
            /*
            while (x != root[x]) {
                root[x] = root[root[x]]; // 进行路径压缩, 将父节点指向爷爷节点
                x = root[x]; // 将x沿路径往上
            }
            return x;
             */
        }

        // 将 x 与 y 所在的两个树连通
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                // 将小数连接到大树下, 以便平衡
                if (size[rootX] > size[rootY]) { // x 树大, 将 rootY 指向 rootX
                    root[rootY] = rootX;
                    size[rootX] += size[rootY];
                } else {
                    root[rootX] = rootY;
                    size[rootY] += size[rootX];
                }

                // 别忘了递减count, 因为两根树已连通
                count--;
            }

            // 否则, 已连通, 无需其他动作
        }



        // 判断p与q所在的两个树是否连通
        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        // 获得连通分量的个数
        public int getCount() {
            return this.count;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < isConnected.length - 1; i++) {
            for (int j = i; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.getCount();
    }


    /**
     * DFS
     * 1. 创建一个长度为n的数组, 记录每个学生是否已被访问过
     * 2. 遍历每个学生
     *    a. 如果该学生已被访问过, 说明他已属于某个朋友圈, 则无需继续访问
     *    b. 如果该学生未被访问过, 说明该学生及其朋友可以组成一个新的朋友圈
     *       i)  先递增res (新增朋友圈)
     *       ii) 对该学生进行递归, 将其标记他的朋友们, 说明他们属于同一个朋友圈
     */
    public int findCircleNum_DFS(int[][] M) {
        int res = 0;
        boolean[] hasChecked = new boolean[M.length];
        for (int i = 0; i < M.length; i++) {
            if (!hasChecked[i]) {
                // 遇到未访问过的学生, 说明找到一个新的friend circle, 所以递增res
                // 使用递归, 访问该学生的所有朋友, 将其标记, 说明他们属于同一个circle. 以后访问时, 则需要跳过他们
                res++;
                checkFriends(M, hasChecked, i);
            }
        }
        return res;
    }

    // 先标记当前学生x为访问
    // 再遍历其他学生, 找寻并标记x的朋友们
    private void checkFriends(int[][] M, boolean[] hasChecked, int x) {
        hasChecked[x] = true;
        for (int i = 0; i < M.length; i++) {
            if (M[x][i] == 1 && !hasChecked[i]) { // x与i是朋友, 且i未被访问过
                checkFriends(M, hasChecked, i); // 递归标记i以及i的朋友们
            }
        }
    }
}

