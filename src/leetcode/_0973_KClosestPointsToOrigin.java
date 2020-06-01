/*
Medium
# Divide and Conquer, #Heap, #Sort
 */
package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 *
 * We have a list of points on the plane. Find the K closest points to the origin (0, 0).
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 * Note:
 * * 1 <= K <= points.length <= 10000
 * * -10000 < points[i][0] < 10000
 * * -10000 < points[i][1] < 10000
 */
public class _0973_KClosestPointsToOrigin {

    // TODO read quick sort solution #3
    // https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.

    /**
     * 遍历points数组, 使用PriorityQueue按与原点距离从大到小排列
     * 当heap即PQ长度超过K时, 删除PQ首位(poll出来)
     * 遍历完成时, PQ包含K个由远及近的点, 将其一一加入答案数组中
     */
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int distance1 = o1[0] * o1[0] + o1[1] * o1[1];
                int distance2 = o2[0] * o2[0] + o2[1] * o2[1];
                return distance2 - distance1;
            }
        });

        // lambda comparator
//        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
//                (p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);

        for (int[] pt : points) {
            pq.offer(pt);
            if (pq.size() > K) {
                pq.poll();
            }
        }

        for (int i = K - 1; i >= 0; i--) {
            res[i] = pq.poll();
        }

        return res;
    }
}
