/*
Easy
#Array, #Math, #Geometry
 */
package leetcode;

/**
 * 1232. Check If It Is a Straight Line
 *
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents
 * the coordinate of a point. Check if these points make a straight line in the XY plane.
 *
 * Example 1:
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 *
 * Example 2:
 * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * Output: false
 *
 * Constraints:
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates contains no duplicate point.
 */
public class _1232_CheckStraightLine {

    /**
     * 如果只有两个坐标 -> 两点定直线 -> true
     * 超过两个点 -> 前两个点定直线的角度. 用这个角度确定之后的每个点是否在同一直线上
     * (x1-x0) / (y1-y0) = (x2-x0) / (y2-y0) 同等于 (x1-x0) * (y2-y0) = (x2-x0) * (y1-y0) 避免除法
     *
     * time: O(n);  space: O(1)
     */
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length < 3)
            return true;

        int xdiff = coordinates[1][0] - coordinates[0][0];
        int ydiff = coordinates[1][1] - coordinates[0][1];

        for (int i = 2; i < coordinates.length; i++) {
            if (xdiff * (coordinates[i][1] - coordinates[0][1]) != ydiff * (coordinates[i][0] - coordinates[0][0])) {
                return false;
            }
        }

        return true;
    }
}
