package lintcode;

/**
 * 1005 · Largest Triangle Area
 * Easy
 * #geometry, #math
 * Google
 * 
 * You have a list of points in the plane. Return the area of the largest 
 * triangle that can be formed by any 3 of the points.
 * 
 * 3 <= points.length <= 50.
 * Points do not repeat.
 * -50 <= points[i][j] <= 50.
 * The result error is within 10 ^(-6) and can be considered correct.
 * 
 * Example 1
 * Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * Output: 2
 * 
 * [4,0]   [4,1]   [4,2]   [4,3]   [4,4]
 * [3,0]   [3,1]   [3,2]   [3,3]   [3,4]     取 [0,0], [0,2], [2,0]
 * [2,0]   [2,1]   [2,2]   [2,3]   [2,4]     *
 * [1,0]   [1,1]   [1,2]   [1,3]   [1,4]        
 * [0,0]   [0,1]   [0,2]   [0,3]   [0,4]     *      *
 * 
 */
public class _1005_Largest_Triangle_Area {

    /*
    1. 三角形面积公式 [ax, ay], [bx, by], [cx, cy]
    面积 = 0.5 * (ax * by + bx * cy + cx * ay - ax * cy - bx * ay - cx * by)
    或者 / 2.0, 要保证 double 运算

    2. 暴力 3 层循环, 尝试所有组合 (因为最多只有 50 个节点)

    要注意得点:
    1. 计算面试时, 用 0.5 去乘, 或者 2.0 去除, 这样保证 double 运算
    2. 面试的值要取绝对值, 保证为正数
     */

    public double largestTriangleArea(int[][] points) {
        if (points == null || points.length < 3 || points[0].length != 2) {
            return 0;
        }

        int n = points.length;
        double ans = 0;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    ans = Math.max(ans, getArea(points[i], points[j], points[k]));
                }
            }
        }

        return ans;
    }

    public double getArea(int[] a, int[] b, int[] c) {
        double val = 0.5 * (a[0] * b[1] + b[0] * c[1] + c[0] * a[1]
                          - a[0] * c[1] - b[0] * a[1] - c[0] * b[1]);

        return val < 0 ? -val : val; // 要去绝对值
    }

}
