/*
Medium
# Divide and Conquer, #Heap, #Sort
Amazon
 */
package leetcode;

import javax.xml.transform.Result;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 *
 * Given an array of points where points[i] = [x_i, y_i] represents a point on the X-Y plane
 * and an integer k, return the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)^2 + (y1 - y2)^2).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
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

    /**
     * 1. 正常计每一个点距离源点的距离，将此距离 与 该点坐标, 存入 ResultType
     * 2. 对全部 ResultType 排序
     * 3. 然后取最小的 k 个
     *
     * 时间 O(nlog)
     * 空间 O(n)
     */
    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0) {
            return null;
        }

        ResultType[] resultTypes = new ResultType[points.length];
        for (int i = 0; i < points.length; i++) {
            resultTypes[i] = new ResultType(getDist(points[i]), points[i]);
        }

        Arrays.sort(resultTypes, new Comparator<ResultType>() {
            @Override
            public int compare(ResultType o1, ResultType o2) {
                return o1.distance - o2.distance;
            }
        });

        // 按照距离由近到远依次记录points信息即可
        int[][] ans = new int[k][2]; // k 个值, 每个值有 x, y 两个坐标
        for (int i = 0; i < k; i++) {
            ans[i] = resultTypes[i].point;
        }
        return ans;
    }

    private int getDist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    class ResultType{
        int distance;
        int[] point;
        ResultType(int _distance, int[] _point) {
            this.distance = _distance;
            this.point = _point;
        }
    }



    /**
     * 遍历points数组, 使用PriorityQueue按与原点距离从大到小排列
     * 当heap即PQ长度超过K时, 将PQ首位(距离最远的一位) 删除 / poll出来
     * 遍历完成时, PQ包含K个由远及近的点, 将其一一加入答案数组中
     *
     * 时间 O(nlogn) - n 个 points, 每次 PQ offer 需要 O(logn)
     * 空间 O(k)
     */
    public int[][] kClosest_PQ(int[][] points, int k) {
        int[][] res = new int[k][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int distance1 = o1[0] * o1[0] + o1[1] * o1[1];
                int distance2 = o2[0] * o2[0] + o2[1] * o2[1];
                return distance2 - distance1; // 按距离 从大到小排列
            }
        });

        // lambda comparator
//        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
//                (p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);

        for (int[] pt : points) {
            pq.offer(pt);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        for (int i = k - 1; i >= 0; i--) { // 由远及近的加入答案
            res[i] = pq.poll();
        }

        return res;
    }




    /**
     * TODO read quick sort solution #3 - https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
     *
     * In the quick sort, we will always choose a pivot to compare with other elements. After one iteration,
     * we will get an array that all elements smaller than the pivot are on the left side of the pivot
     * and all elements greater than the pivot are on the right side of the pviot (assuming we sort the array in ascending order).
     * So, inspired from this, each iteration, we choose a pivot and then find the position p the pivot should be.
     * Then we compare p with the K, if the p is smaller than the K, meaning the all element on the left of the pivot
     * are all proper candidates but it is not adequate, we have to do the same thing on right side, and vice versa.
     * If the p is exactly equal to the K, meaning that we've found the K-th position.
     * Therefore, we just return the first K elements, since they are not greater than the pivot.
     *
     * Theoretically, the average time complexity is O(N) , but just like quick sort, in the worst case,
     * this solution would be degenerated to O(N^2), and pratically, the real time it takes on leetcode is 15ms.
     *
     * The advantage of this solution is it is very efficient.
     * The disadvatage of this solution are it is neither an online solution nor a stable one. And the K elements closest are not sorted in ascending order.
     */
    public int[][] kClosest_Quick_Sort(int[][] points, int k) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == k) break;
            if (mid < k) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, k);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
