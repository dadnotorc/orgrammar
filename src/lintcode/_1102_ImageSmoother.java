/*
Easy
#Array, #Simulation
Amazon
 */
package lintcode;

/**
 * 1102 · Image Smoother
 *
 * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother
 * to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8
 * surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
 *
 * 1.The value in the given matrix is in the range of [0, 255].
 * 2.The length and width of the given matrix are in the range of [1, 150].
 *
 * Example 1:
 * Input:
 * [[1,1,1],
 *  [1,0,1],
 *  [1,1,1]]
 * Output:
 * [[0, 0, 0],
 *  [0, 0, 0],
 *  [0, 0, 0]]
 * Explanation:
 * For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 * For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 * For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 */
public class _1102_ImageSmoother {

    /**
     * 遍历数组上每个坐标, 计算以此点为中心, 周围一圈(包含自己)的总和, 并计算平均值
     */
    public int[][] imageSmoother(int[][] M) {
        // 跳过 length 特判 因为 the length and width of the given matrix are in the range of [1, 150]

        int[][] ans = new int[M.length][M[0].length];

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                ans[i][j] = help(M, i, j);
            }
        }

        return ans;
    }

    public int help(int[][] m, int x, int y) {
        int sum = 0, count = 0;

        // 依次遍历 x, y 周围一圈
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i; //
                int ny = y + j;

                if (nx >= 0 && nx < m.length && ny >= 0 && ny < m[0].length) {
                    sum += m[nx][ny];
                    count++;
                }
            }
        }

        return sum / count;
    }
}
