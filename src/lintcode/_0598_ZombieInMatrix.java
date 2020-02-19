/*
Medium
#BFS
Amazon
 */
package lintcode;

import sun.font.CoreMetrics;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 598. Zombie in Matrix
 *
 * Given a 2D grid, each cell is either a wall 2, a zombie 1 or people 0.
 * Zombies can turn the nearest people (up/down/left/right) into zombies
 * every day, but can not through wall. How long will it take to turn all
 * people into zombies? Return -1 if can not turn all people into zombies.
 *
 * Example 1:
 * Input:
 * [[0,1,2,0,0],
 *  [1,0,0,2,1],
 *  [0,1,0,0,0]]
 * Output:
 * 2
 *
 * Example 2:
 * Input:
 * [[0,0,0],
 *  [0,0,0],
 *  [0,0,1]]
 * Output:
 * 4
 */
public class _0598_ZombieInMatrix {

    private class Coordinate{
        int x, y;
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    int[] xs = {-1, 1, 0, 0};
    int[] ys = {0, 0, -1, 1};
    enum Obj {
        HUMAN,  // 0
        ZOMBIE, // 1
        WALL    // 2
    }

    /**
     * 前提: 创建Coordinate class； 上/下/左/右 neighbor
     * 1. 遍历, 找出human总数, 以及zombie的位置. 将初始zombie位置放入queue
     * 2. 特殊情况: 如初始已无人类, 返回 0
     * 3. 使用BFS - 每轮为一天, 找出当天初始时zombie位置, 将其周围human感染为zombie
     *    (human数量减少, 新zombie位置放去queue, 并在grid上更新)
     * 4. 当queue变空时, 如果human总数为0, 返回天数-1； 否则返回 -1
     *
     * 注意: 返回天数-1的原因是:
     * 在倒数第二天时, 所有人类都被感染. 则在最后一天, 只是确认再无人类幸存, 所以会多加一天, 需要去掉
     */
    public int zombie(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;

        // 遍历, 找出人类的数量, 并将zombie的位置(coordinate)放入queue
        int humanCount = 0, daysAfter = 0;
        Queue<Coordinate> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == Obj.HUMAN.ordinal()) {
                    humanCount++;
                } else if (grid[i][j] == Obj.ZOMBIE.ordinal()) {
                    queue.offer(new Coordinate(i, j));
                }
            }
        }

        if (humanCount == 0)
            return 0;

        // 每次BFS, 找出当前可感染的人类, 减少人类数量, 增加天数
        while (!queue.isEmpty()) {
            int size = queue.size(); // 每轮为1天
            for (int i = 0; i < size; i++) {
                Coordinate zombieLoc = queue.poll();
                for (int j = 0; j < xs.length; j++) {
                    Coordinate neighbor = new Coordinate(zombieLoc.x + xs[j], zombieLoc.y + ys[j]);
                    if (isHuman(grid, neighbor)) {
                        humanCount--;
                        queue.offer(neighbor);
                        grid[neighbor.x][neighbor.y] = Obj.ZOMBIE.ordinal();
                    }
                }
            }
            daysAfter++;
        }


        // 如果BFS结束后, 人类数量为0, 返回天数 -1； 否则返回 -1

        // 注意! 天数-1是因为, 在倒数第二天时, 所有人类都被感染. 则在最后一天, 只是确认再无人类幸存, 所以会多加一天, 需要去掉
        return (humanCount == 0) ? daysAfter - 1 : -1;
    }


    // 判断当前坐标是否: 1.越界 2.是围墙 3.是人类
    private boolean isHuman(int[][] grid, Coordinate cord) {
        return (cord.x >= 0 && cord.x < grid.length
                && cord.y >= 0 && cord.y < grid[0].length
                && grid[cord.x][cord.y] == Obj.HUMAN.ordinal());
    }

}
