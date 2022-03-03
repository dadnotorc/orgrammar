package explore.Union_Find;

public class UnionFind_0 {

    /*
    复杂度 - 主要 API isConnected 和 union 中的复杂度都是 find 函数造成的，所以说它们的复杂度和 find 一样。

    find 主要功能就是从某个节点向上遍历到树根，其时间复杂度就是树的高度。我们可能习惯性地认为树的高度就是 logN，
    但这并不一定。logN 的高度只存在于平衡二叉树，对于一般的树可能出现极端不平衡的情况，使得「树」几乎退化成「链表」，
    树的高度最坏情况下可能变成 N。

    所以说上面这种解法，find,union,isConnected 的时间复杂度都是 O(N)。这个复杂度很不理想的，
    你想图论解决的都是诸如社交网络这样数据规模巨大的问题，对于 union 和 isConnected 的调用非常频繁，
    每次调用需要线性时间完全不可忍受。
     */

    private int count;
    private int[] parent;

    public UnionFind_0(int n) {
        this.count = n;
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) { return; }

        // 合并两者, 减少联通量
        parent[rootP] = rootQ; // parent[rootQ] = rootP 也可以
        count--;
    }


    public int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }

        return x;
    }

    // 判断p与q所在的两个树是否连通
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 获得连通分量的个数
    public int getCount() {
        return count;
    }
}
