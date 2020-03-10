/*
Medium
#Array, #Matrix
Amazon, Google, Microsoft, Uber
FAQ+
 */
package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 374. Spiral Matrix
 *
 * Given a matrix of m x n elements (m rows, n columns),
 * return all elements of the matrix in spiral order.
 *
 * Example 1:
 * Input:  [[1,2,3],
 *          [4,5,6],
 *          [7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Example 2
 * Input:  [[6,4,1],
 *          [7,8,9]]
 * Output: [6,4,1,9,8,7]
 */
public class _0374_SpiralMatrix {


    /**
     * 使用坐标 + 移动方向
     * 每次扫描一行/一列, 扫描完, 转向, 并将已扫描的行数/列数递减
     *
     * 易错点:
     * 1. while循环开始前, 坐标应当从(0,-1)开始 即(0,0)点左一位
     * 2. while循环中需要做:
     *    - 沿当前方向读取 m 个值, 将坐标值加入答案队列
     *    - 递减 n
     *    - 互换 n 与 m
     *    - 递增direction index
     * 3. 递增direction index, 需 mod 4, 保持在4个方向内循环
     */

    class Coord{
        int x, y;
        public Coord (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[][] DIRECTION = {{0,1},{1,0},{0,-1},{-1,0}}; // 向右, 向下, 向左, 向上

    public List<Integer> spiralOrder_2(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return ans;

        int n = matrix.length;
        int m = matrix[0].length;
        int totalElements = n * m;

        int di = 0; // direction index 用于记录当前移动方向, 0=向右, 1=向下, 2=向左, 3=向上

        Coord pos = new Coord(0, -1); // 从左上角(0,0)的左一位(0,-1)开始

        while (ans.size() < totalElements) { // 当前仍未读完所有数字, 则读取当前row/column
            for (int i = 0; i < m; i++) {
                pos.x += DIRECTION[di][0];
                pos.y += DIRECTION[di][1];
                ans.add(matrix[pos.x][pos.y]);
            }

            n--; // 减少row / column总数, 因为已经读取了一行/一列

            // 将m,n的值互换, 改变 horizontal vertical mode (读取一行后, 改成读取一列...)
            // 不能使用 void swap(n,m) 因为传递的是int值而不是指针
            int xor = n ^ m;
            n = xor ^ n;
            m = xor ^ m;

            di = (di + 1) % 4; // 在4个方向里循环
        }

        return ans;
    }



    /**
     * 定义 rowStart, rowEnd, colStart, colEnd 标记当前左/右/上/下边界下标
     * 按照右下左上的顺序访问matrix
     *
     * 易错点:
     * 1. 向左和向上到时候, 注意判断是否仍有未访问的点, 以免重复
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return ans;

        int rowStart = 0, rowEnd = matrix.length - 1;
        int colStart = 0, colEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            // 向右
            for (int i = colStart; i <= colEnd; i++) {
                ans.add(matrix[rowStart][i]);
            }
            rowStart++;

            // 向下
            for (int i = rowStart; i <= rowEnd; i++) {
                ans.add(matrix[i][colEnd]);
            }
            colEnd--;

            // 向左
            if (rowStart <= rowEnd) { // 确认当前row仍未访问 - 因为第一个for循环后, rowStart向下移, 可能导致当前row不成立
                for (int i = colEnd; i >= colStart; i--) {
                    ans.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }

            // 向上
            if (colStart <= colEnd) { // 确认当前col仍未访问 - 因为第二个for循环后, colEnd向左移, 可能导致当前col不成立
                for (int i = rowEnd; i >= rowStart; i--) {
                    ans.add(matrix[i][colStart]);
                }
                colStart++;
            }
        }

        return ans;
    }



    @Test
    public void test1() {
        int[][] matrix = {{1},{2},{3},{4},{5},{6},{7},{8},{9},{10}};
        List<Integer> output = spiralOrder_2(matrix);
    }

    @Test
    public void test2() {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> output = spiralOrder_2(matrix);
    }
}