/*
Easy
Hash Table
Google
 */
package lintcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1225. Island Perimeter
 *
 * You are given a map in form of a two-dimensional integer grid where 1
 * represents land and 0 represents water. Grid cells are connected
 * horizontally/vertically (not diagonally). The grid is completely
 * surrounded by water, and there is exactly one island (i.e., one or more
 * connected land cells). The island doesn't have "lakes" (water inside
 * that isn't connected to the water around the island). One cell is a square
 * with side length 1. The grid is rectangular, width and height don't exceed
 * 100. Determine the perimeter of the island.
 *
 * Example
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * Answer: 16
 */
public class _1225_IslandPerimeter {

    // BFS
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        boolean[][] isVisited = new boolean[grid.length][grid[0].length];

        // 找到任意一块陆地
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0)
                    continue;

                return bfs(i, j, grid, isVisited);
            }
        }

        // 全是水， 没有陆地
        return (grid.length + 1) * grid[0].length + (grid[0].length + 1) * grid.length;
    }

    private int[] dx = new int[] {0, 1, 0, -1};
    private int[] dy = new int[] {1, 0, -1, 0};

    private int bfs (int x, int y, int[][] grid, boolean[][] isVisited) {
        Queue<Coordinate> q = new LinkedList<>();
        q.offer(new Coordinate(x, y));
        isVisited[x][y] = true;

        int perimeter = 0;
        while (!q.isEmpty()) {
            Coordinate coor = q.poll();
            for (int i = 0; i < 4; i++) {
                int neighborX = coor.x  + dx[i];
                int neighborY = coor.y + dy[i];

                if (neighborX < 0 || neighborX >= grid.length ||
                        neighborY < 0 || neighborY >= grid[0].length ||
                        grid[neighborX][neighborY] == 0) {
                    perimeter++;
                    continue;
                }

                if (!isVisited[neighborX][neighborY]) {
                    isVisited[neighborX][neighborY] = true;
                    q.offer(new Coordinate(neighborX, neighborY));
                }
            }
        }
        return perimeter;
    }

    private class Coordinate {
        int x, y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
