/*
Medium
#Computational Geometry, #Math
 */
package lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 186 · Max Points on a Line
 *
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * point.x and point.y ranges from -1000 to 1000
 *
 * Example 1:
 * Input:(1,2),(3,6),(0,0),(1,3).
 * Output:3
 *
 * Example 2:
 * Input:(1,2),(2,3),(3,2).
 * Output:2
 */
public class _0186_MaxPoints_On_Line {


    // todo 读读

    /**
     * 9z解法 1
     */
    public  int maxPoints_1(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        HashMap<Double, Integer> map=new HashMap<Double, Integer>();
        int max = 1;

        for(int i = 0 ; i < points.length; i++) {
            // shared point changed, map should be cleared and server the new point
            map.clear();

            // maybe all points contained in the list are same points,and same points' k is
            // represented by Integer.MIN_VALUE
            map.put((double)Integer.MIN_VALUE, 1);

            int dup = 0;
            for(int j = i + 1; j < points.length; j++) {
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    dup++;
                    continue;
                }

                // look 0.0+(double)(points[j].y-points[i].y)/(double)(points[j].x-points[i].x)
                // because (double)0/-1 is -0.0, so we should use 0.0+-0.0=0.0 to solve 0.0 !=-0.0
                // problem

                // if the line through two points are parallel to y coordinator, then K(slop) is
                // Integer.MAX_VALUE
                double key=points[j].x - points[i].x == 0 ?
                        Integer.MAX_VALUE :
                        0.0 + (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x);

                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 2);
                }
            }

            for (int temp: map.values()) {
                // duplicate may exist
                if (temp + dup > max) {
                    max = temp + dup;
                }
            }

        }
        return max;
    }


    /**
     * 9z解法 - 2
     */
    public int maxPoints_2(Point[] points) {
        if (points == null) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slope = new HashMap<>();
            int maxPoints = 0, overlap = 0, vertical = 0;

            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x) {
                    if (points[i].y == points[j].y) {
                        overlap++;
                    } else {
                        vertical++;
                    }
                    continue;
                }
                int dx = points[i].x - points[j].x;
                int dy = points[i].y - points[j].y;
                int tmp = gcd(dx, dy);
                dx /= tmp;
                dy /= tmp;
                String k = dy + "/" + dx;

                if (!slope.containsKey(k)) {
                    slope.put(k, 0);
                }
                slope.put(k, slope.get(k) + 1);
                maxPoints = Math.max(maxPoints, slope.get(k));
            }
            maxPoints = Math.max(maxPoints, vertical);
            ans = Math.max(ans, maxPoints + overlap + 1);
        }
        return ans;
    }

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }



    class Point{
        int x, y;
        Point() { x = 0; y = 0;}
        Point(int a, int b) { x = a; y = b; }
    }
}
