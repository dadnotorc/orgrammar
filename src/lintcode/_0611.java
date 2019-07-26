package lintcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 611. Knight Shortest Path
 *
 * Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier)
 *  with a source position, find the shortest path to a destination position,
 *  return the length of the route.
 * Return -1 if destination cannot be reached.
 *
 * Clarification
 * If the knight is at (x, y), he can get to the following positions in one step:
 * (x + 1, y + 2)
 * (x + 1, y - 2)
 * (x - 1, y + 2)
 * (x - 1, y - 2)
 * (x + 2, y + 1)
 * (x + 2, y - 1)
 * (x - 2, y + 1)
 * (x - 2, y - 1)
 */
public class _0611 {
	public static class Point {
        int x;
        int y;
        public Point() { x = 0; y = 0; }
        public Point(int a, int b) { x = a; y = b; }
    }

    /**
     * @param grid a chessboard included 0 (false) and 1 (true)
     * @param source, destination a point
     * @return the shortest path
     *
     * time complexity:  O(?)
     * space complexity: O(?)
     */
    public static int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

        Queue<Point> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];

        if (source.x == destination.x && source.y == destination.y) {
            return 0;
        }
        q.offer(source);
        isVisited[source.x][source.y] = true;

        int steps = 0;
        // BFS
        while (!q.isEmpty()) {
            steps++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();
                for (int d = 0; d < dx.length; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    // next point is with in chessboard's boundary
                    //  AND not blocked by barrier on grid
                    //  AND not visited before
                    if (nx >= 0 && nx < grid.length
                            && ny >= 0 && ny < grid[0].length
                            && !grid[nx][ny] && !isVisited[nx][ny]) {
                        if (nx == destination.x && ny == destination.y) {
                            return steps;
                        }

                        q.offer(new Point(nx, ny));
                        isVisited[nx][ny] = true;
                    }
                }
            }
        }

        return -1;
    }

    /*int n, m; // size of the chessboard
    int[] deltaX = {1, 1, 2, 2, -1, -1, -2, -2};
    int[] deltaY = {2, -2, 1, -1, 2, -2, 1, -1};

    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        n = grid.length;
        m = grid[0].length;

        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point point = queue.poll();
                if (point.x == destination.x && point.y == destination.y) {
                    return steps;
                }

                for (int direction = 0; direction < 8; direction++) {
                    Point nextPoint = new Point(
                            point.x + deltaX[direction],
                            point.y + deltaY[direction]
                    );

                    if (!inBound(nextPoint, grid)) {
                        continue;
                    }

                    queue.offer(nextPoint);
                    // mark the point not accessible
                    grid[nextPoint.x][nextPoint.y] = true;
                }
            }
            steps++;
        }

        return -1;
    }

    private boolean inBound(Point point, boolean[][] grid) {
        if (point.x < 0 || point.x >= n) {
            return false;
        }
        if (point.y < 0 || point.y >= m) {
            return false;
        }
        return (grid[point.x][point.y] == false);
    }*/
}
