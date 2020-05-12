/*
Easy
#DFS, #BFS
 */
package leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 733. Flood Fill
 *
 * An image is represented by a 2-D array of integers,
 * each integer representing the pixel value of the image (from 0 to 65535).
 *
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill,
 * and a pixel value newColor, "flood fill" the image.
 *
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally
 * to the starting pixel of the same color as the starting pixel, plus any pixels connected
 * 4-directionally to those pixels (also with the same color as the starting pixel), and so on.
 * Replace the color of all of the aforementioned pixels with the newColor.
 *
 * At the end, return the modified image.
 *
 * Example 1:
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * 1 1 1      2 2 2
 * 1 1 0  ->  2 2 0
 * 1 0 1      2 0 1
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 *
 * Note:
 * The length of image and image[0] will be in the range [1, 50].
 * The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 * The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 */
public class _0733_FloodFill {

    /**
     * DFS
     */

    public int[][] floodFill_DFS(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        if (originalColor == newColor) // 注意这里, 如果不判断的话, 会TLE, 因为不停重复修改相同颜色
            return image;

        helper(image, sr, sc, originalColor, newColor);
        return image;
    }

    private void helper(int[][] image, int y, int x, int originalColor, int newColor) {
        if (y < 0 || y >= image.length || x < 0 || x >= image[0].length || image[y][x] != originalColor)
            return;

        image[y][x] = newColor;

        for (int i = 0; i < 4; i++) {
            helper(image, y + directions[i][0], x + directions[i][1], originalColor, newColor);
        }
    }


    /**
     * BFS
     *
     * 易错点:
     * 1. 初始时, 必须判断原color是否等于新color, 否则会无线循环, 不断修改相同颜色
     */

    class Coord {
        int y, x;
        public Coord (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    int[][] directions = {{0,1},{1,0},{-1,0},{0,-1}};

    public int[][] floodFill_BFS(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        if (originalColor == newColor) // 注意这里, 如果不判断的话, 会TLE, 因为不停重复修改相同颜色
            return image;
        Queue<Coord> q = new LinkedList<>();
        q.offer(new Coord(sr, sc));

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Coord coord = q.poll();
                image[coord.y][coord.x] = newColor;

                for (int j = 0; j < 4; j++) {
                    Coord neighbour = new Coord(coord.y + directions[j][0], coord.x + directions[j][1]);
                    if (isValid(image, neighbour, originalColor)) {
                        q.offer(neighbour);
                    }
                }
            }
        }

        return image;
    }

    private boolean isValid(int[][] image, Coord coord, int color) {
        int y = coord.y;
        int x = coord.x;

        return y >= 0 && y < image.length && x >= 0 && x < image[0].length && image[y][x] == color;
    }




    @Test
    public void test1() {
        int[][] image = {{0,0,0},{0,1,1}};
        int[][] exp = {{0,0,0},{0,1,1}};
        org.junit.Assert.assertArrayEquals(exp, floodFill_BFS(image, 1, 1, 1));
    }
}
