/*
Medium
#Union Find, #DFS
 */
package leetcode;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Union;

/**
 * 547. Friend Circles
 *
 * There are N students in a class. Some of them are friends, while some are not.
 * Their friendship is transitive in nature. For example, if A is a direct friend of B,
 * and B is a direct friend of C, then A is an indirect friend of C.
 * And we defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class.
 * If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
 * And you have to output the total number of friend circles among all the students.
 *
 * Example 1:
 * Input:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 *
 * Example 2:
 * Input:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 *
 * Note:
 * 1. N is in range [1,200].
 * 2. M[i][i] = 1 for all students.
 * 3. If M[i][j] = 1, then M[j][i] = 1.
 */
public class _0547_FriendCircles {

    /**
     * Union Find
     */
    class UnionFind{
        private int count; // 连通分量的个数
        private int[] parent; // x节点的父节点为 parent[x]
        private int[] size; // x节点所在的树的大小

        public UnionFind(int n) {
            // 初始化时, 每棵树只有一个节点, 即本身. 一共有n颗树
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 将p与q所在的两个树连通
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) { // 已相连, 无需其他动作
                return;
            }

            // 将小数连接到大树下, 以便平衡
            if (size[rootP] > size[rootQ]) { // P树大, 将rootQ指向rootP
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }

            // 别忘了递减count, 因为两根树已连通
            count--;
        }

        // 判断p与q所在的两个树是否连通
        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        // 找到x所在树的根节点
        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]]; // 进行路径压缩, 将父节点指向爷爷节点
                x = parent[x]; // 将x沿路径往上
            }
            return x;
        }

        // 获得连通分量的个数
        public int getCount() {
            return this.count;
        }
    }

    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < M.length - 1; i++) {
            for (int j = i; j < M.length; j++) {
                if (M[i][j] == 1) {
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

