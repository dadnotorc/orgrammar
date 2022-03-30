package leetcode;

import org.junit.Test;

import java.util.*;

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
    暴力 - 找出所有的 path, 找出每条 path 中的最大值, 选择这些最大值中的最小的那一位
    使用个 boolean[][] 记录当前节点是否已经访问过

    时间 O(4^ (n^2)) - 每个节点 可以选择 4 个方向, 共有 n^2 个节点
    空间 O(n^2)

    改进 1 - 因为数组的值是从 0 至 n^2 - 1. 每个节点值都不同,
    所以使用循环, i 从 0 到 n^2 - 1, 查找是否存在某个 path, 其中最大值 等于 i

    改进 2 - i 的起始点不用从 0 开始, 而可以定为 max(grid[0][0], grid[n - 1][n - 1], 2*n - 1)
    即 左上角节点, 右下角节点, 从左上到右下必须经过 n + (n - 1) = 2*n - 1 个节点, 即最大值位 2*n - 2
     时间 - O(n^2 * n^2) = O(n^4) - 循环 n^2, 每次查找 n^2 数组
     空间 - O(n^2)

    改进 3 - 使用 binary search 取代 循环遍历
     时间 - O(n^2 * log(n^2)) = O(n^2 * 2log(n)) = O(n^2 * log(n))
     二分法 log(n^2), 每次查找 n^2 的 数组
     空间 - O(n^2)
     */

    int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};

    // 是否存在一条 path, 其最大值 == targe.  不用 DFS, 因为递归深度可达 n^2, 数据规模太大
    public boolean canReachWithSetMax(int[][] grid, int target) {
        int n = grid.length;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] coord = q.poll();
            if (coord[0] == n - 1 && coord[1] == n - 1) {
                return true;
            }

            for (int[] dir : dirs) {
                int x = coord[0] + dir[0];
                int y = coord[1] + dir[1];
                if (x < 0 || x >= n || y < 0 || y >= n ||    // 越界
                        visited[x][y] ||                     // 已访问
                        grid[x][y] > target) {               // 超过 target
                    //return false; // 这里不能 return false, 会提前错误的结束
                    continue;
                }

                visited[x][y] = true;
                q.offer(new int[]{x, y});
            }
        }

        return false; // queue 读完, 仍未到终点, 说明无法完成
    }

    public int swimInWater_binarysearch(int[][] grid) {
        int n = grid.length;
        int l = Math.max(2 * n - 2, // 最少经过 2n - 1 个节点, 即从 0 至 2n - 2
                Math.max(grid[0][0], grid[n - 1][n - 1]));
        int r = n * n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (canReachWithSetMax(grid, mid)) {
                r = mid; // mid 可以, 但是要继续找有没有更小的
            } else {
                l = mid + 1; // mid 达不到, 必须扩大
            }
        }
        return l;
    }

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int start = Math.max(2 * n - 2, // 最少经过 2n - 1 个节点, 即从 0 至 2n - 2
                    Math.max(grid[0][0], grid[n - 1][n - 1]));
        int end = n * n;
        for (int i = start; i < end; i++) {
            if (canReachWithSetMax(grid, i)) {
                return i;
            }
        }

        return end;
    }


    /**
     * Dijkstra - min heap
     * 使用 PriorityQueue 记录到达 [i,j] 节点时, 所有路径中的最小值 (每条路径记录遇到的最大节点)
     * [i, j, 路径最小值]
     */
    public int swimInWater_dijkstra(int[][] grid) {
        int n = grid.length;

        // PQ 中记录 [座标_i, 座标_j, 路径值]
        // 排列顺序为 - 路径值较小者靠前
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });
        pq.offer(new int[] {0, 0, grid[0][0]});

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] val = pq.poll();
            if (val[0] == n - 1 && val[1] == n - 1) {
                return val[2];
            }

            for (int[] dir : dirs) {
                int x = val[0] + dir[0];
                int y = val[1] + dir[1];
                if (x < 0 || x >= n || y < 0 || y >= n || visited[x][y]) {
                    //return false; // 这里不能 return false, 会提前错误的结束
                    continue;
                }

                visited[x][y] = true;
                pq.offer(new int[] {x, y, Math.max(val[2], grid[x][y])}); // 注意, 值要取较大者
            }
        }

        return n * n;
    }
}
