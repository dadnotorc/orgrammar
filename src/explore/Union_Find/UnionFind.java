package explore.Union_Find;

/**
 * 并查集 Union Find
 *
 * 介绍: https://blog.csdn.net/dm_vincent/article/details/7655764
 *      https://blog.csdn.net/Yaokai_AssultMaster/article/details/78888846
 *
 * labuladong UnionFind算法详解
 * https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/UnionFind%E7%AE%97%E6%B3%95%E8%AF%A6%E8%A7%A3.md
 *
 */
public class UnionFind {

    /* union 优化
    union 时, 简单粗暴的把 p 所在的树接到 q 所在的树的根节点下面，那么这里就可能出现「头重脚轻」的不平衡状况

    我们其实是希望，小一些的树接到大一些的树下面，这样就能避免头重脚轻，更平衡一些。
    解决方法是额外使用一个size数组，记录每棵树包含的节点数，我们不妨称为「重量」：

    通过比较树的重量，就可以保证树的生长相对平衡，树的高度大致在 logN 这个数量级，极大提升执行效率。
    此时，find ,union, isConnected 的时间复杂度都下降为 O(logN)，即便数据规模上亿，所需时间也非常少。
     */


    /* find 优化
    find 时要遍历整个树, 所以有效的压缩树的高度, 能使 find 更有效
     */


    private int count; // 连通分量的个数
    private int[] parent; // x 节点的父节点为 parent[x]
    private int[] size; // x 节点所在的树的大小 (优化)

    public UnionFind(int n) {
        // 初始化时, 每棵树只有一个节点, 即本身. 一共有n颗树
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.size[i] = 1; // 每个树 初始只有 root, 所以 size 为 1
        }
    }

    // 将p与q所在的两个树连通
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) { return; } // 已相连, 无需其他动作

        count--; // 别忘了递减count, 因为两根树已连通

        // 将小数连接到大树下, 以便平衡
        if (size[rootP] > size[rootQ]) { // P 树大, 将 rootQ 指向 rootP
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {                         // Q 树大, 将 rootP 指向 rootQ
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
    }

    // 找到 x 所在树的根节点
    public int find(int x) {
        while (x != parent[x]) {  // 这里是while, 一路走到底
            parent[x] = parent[parent[x]]; // 进行路径压缩, 将父节点指向爷爷节点
            x = parent[x];
        }
        return x;
    }

    // 判断 p 与 q 所在的两个树是否连通
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 获得连通分量的个数
    public int getCount() {
        return count;
    }
}
