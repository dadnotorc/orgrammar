package explore;

/**
 * 并查集 Union Find
 *
 * 介绍: https://blog.csdn.net/dm_vincent/article/details/7655764
 *      https://blog.csdn.net/Yaokai_AssultMaster/article/details/78888846
 *
 * 朋友圈问题解答 by labuladong
 *      https://leetcode-cn.com/problems/friend-circles/solution/union-find-suan-fa-xiang-jie-by-labuladong/
 *
 */
public class UnionFind {

    private int count; // 连通分量的个数
    private int[] parent; // x节点的父节点为 parent[x]
    private int[] size; // x节点所在的树的大小

    public UnionFind(int n) {
        // 初始化时, 每棵树只有一个节点, 即本身. 一共有n颗树
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    // 将p与q所在的两个树连通
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {  // 已相连, 无需其他动作
            return;
        }

        // 别忘了递减count, 因为两根树已连通
        count--;

        // 将小数连接到大树下, 以便平衡
        if (size[rootP] > size[rootQ]) { // P树大, 将rootQ指向rootP
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
    }

    // 判断p与q所在的两个树是否连通
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 找到p所在树的根节点
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // 进行路径压缩, 将父节点指向爷爷节点
            p = parent[p]; // 将p沿路径往上
        }
        return p;
    }

    // 获得连通分量的个数
    public int getCount() {
        return count;
    }
}
