package lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 654 · Sparse Matrix Multiplication
 * Medium
 * #Math
 * Facebook meta
 *
 * Given two Sparse Matrix A and B, return the result of A*B.
 *
 * You may assume that A's column number is equal to B's row number.
 *
 * Example1
 * Input:
 * [[1,0,0],[-1,0,3]]
 * [[7,0,0],[0,0,0],[0,0,1]]
 * Output:
 * [[7,0,0],[-7,0,3]]
 * Explanation:
 * A = [             B = [
 *     [ 1, 0, 0],       [ 7, 0, 0 ],
 *     [-1, 0, 3]        [ 0, 0, 0 ],
 * ]                     [ 0, 0, 1 ]
 *                       ]
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 *
 * Example2
 * Input:
 * [[1,0],[0,1]]
 * [[0,1],[1,0]]
 * Output:
 * [[0,1],[1,0]]
 */
public class _0654_Sparse_Matrix_Multiplication {
    /*
    矩阵乘法的实现
    一个 m * n 的矩阵A，与一个 n * p 的矩阵B可以相乘，得到的结果AB是一个 m * p列的矩阵，
    其中的第 i 行第 j 列位置上的数AB(i,j)为:
    矩阵A第 i 行上的 n 个数 与 矩阵B第 j 列上的n个数, 一一对应相乘后，所得到的 n个乘积之和。

     | 1 1 1 |   | 1 2 3 |   | 12 15 18 |
     | 2 2 2 | x | 4 5 6 | = | 24 30 36 |
                 | 7 8 9 |

     1 * 1 + 1 * 2 + 1 * 3 = 12
     1 * 4 + 1 * 5 + 1 * 6 = 15
     1 * 7 + 1 * 8 + 1 * 9 = 18

     2 * 1 + 2 * 2 + 2 * 3 = 24
     2 * 4 + 2 * 5 + 2 * 6 = 30
     2 * 7 + 2 * 8 + 2 * 9 = 36

     常规解法
     3 层循环 - 注意, 循环最内层 ans[i][k] - i 来自 A's row, k 来自 B's column
     时间 O (m * n * p), 空间 O(1)

     改进 1 - 考虑 A 的 稀疏性, 跳过 A 中 非0数字的, 但是对 B 没有影响
     在第二层循环中, 加入 A[i][j] 是否为 0 的判断, 减少
     时间 O (m * n * (a * p)) - a 为 A 的稀疏系数 在 0 到 1之间 越稀疏越小, 空间 O(1)

     改进 2 - 考虑 A 和 B 的 稀疏性, 对两者 中的 非0数字 都有判断
     在循环之前, 找出 B 中所有的 非0数字, 记录其位置
     时间 O (m * n * (a * b * p)) - a 为 A 的稀疏系数 b 为 B 的稀疏系数, 空间 O(1)

     B's row i, 记录该行中所有非0数字的 column
     */

    /**
     * 还是 改进 2 - 但是用 List<List<Integer>> 取代 ashMap<Integer, List<Integer>>
     */
    public int[][] multiply_4(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length; // n = B.length (A's column number = B's row number)
        int p = B[0].length;

        int[][] ans = new int[m][p];

        // For B, 记录 <row i, list of columns in current row with a non-0 value>
        List<List<Integer>> columns = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            columns.add(new ArrayList<>()); // 每层都先加个新的 list

            for (int j = 0; j < p; j++) {
                if (B[i][j] != 0) {
                    columns.get(i).add(j);
                }
            }
        }

        for (int i = 0; i < m; i++) { // A's row
            for (int j = 0; j < n; j++) { // A's column and B's row
                // 改进 1 在这里
                if (A[i][j] == 0) {
                    continue;
                }

                // 改进 2 在这
                List<Integer> columnsInCurrentRow = columns.get(j); // j 行中包含非0数字的所有 column
                for (int k : columnsInCurrentRow) {
                    // 注意这里!
                    // ans[i][k] - A's row & B's column
                    ans[i][k] += A[i][j] * B[j][k];
                }
            }
        }

        return ans;
    }



    /**
     * 改进 2
     */
    public int[][] multiply_3(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length; // n = B.length (A's column number = B's row number)
        int p = B[0].length;

        int[][] ans = new int[m][p];

        // For B, 记录 <row i, list of columns in current row with a non-0 value>
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                if (B[i][j] != 0) {
                    if (!map.containsKey(i)) {
                        map.put(i, new ArrayList<>());
                    }
                    map.get(i).add(j);
                }
            }
        }

        for (int i = 0; i < m; i++) { // A's row
            for (int j = 0; j < n; j++) { // A's column and B's row
                // 改进 1 在这里
                if (A[i][j] == 0) {
                    continue;
                }

                // 改进 2 在这
                if (map.containsKey(j)) {
                    List<Integer> columns = map.get(j);
                    for (int k : columns) {
                        // 注意这里!
                        // ans[i][k] - A's row & B's column
                        ans[i][k] += A[i][j] * B[j][k];
                    }
                }
            }
        }

        return ans;
    }



    /**
     * 改进 1
     */
    public int[][] multiply_2(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length; // n = B.length (A's column number = B's row number)
        int p = B[0].length;

        int[][] ans = new int[m][p];

        for (int i = 0; i < m; i++) { // A's row
            for (int j = 0; j < n; j++) { // A's column and B's row
                // 改进1 在这里
                if (A[i][j] == 0) {
                    continue;
                }

                for (int k = 0; k < p; k++) { // B's column
                    // 注意这里!
                    // ans[i][k] - A's row & B's column
                    ans[i][k] += A[i][j] * B[j][k];
                }
            }
        }

        return ans;
    }



    /**
     * 常规解法
     */
    public int[][] multiply_1(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length; // n = B.length (A's column number = B's row number)
        int p = B[0].length;

        int[][] ans = new int[m][p];

        for (int i = 0; i < m; i++) { // A's row
            for (int j = 0; j < n; j++) { // A's column and B's row
                for (int k = 0; k < p; k++) { // B's column
                    // 注意这里!
                    // ans[i][k] - A's row & B's column
                    ans[i][k] += A[i][j] * B[j][k];
                }
            }
        }

        return ans;
    }
}
