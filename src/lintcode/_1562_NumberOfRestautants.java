package lintcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1562. Number of restaurants
 *
 * Give a List of data representing the coordinates[x,y] of each restaurant
 *  and the customer is at the origin[0,0]. Find n closest restaurants to the
 *  customer, where m is the furthest distance from n restaurants to the customer.
 * If there are more than n restaurants in the list and the distance from the
 *  customer is not greater than m, then the first n restaurants will be
 *  returned in the order of the elements in the list.
 *
 * 1. Coordinates in range [-1000,1000]
 * 2. n > 0
 * 3. No same coordinates
 *
 * Example 1
 * Input: n = 2 , List = [[0,0],[1,1],[2,2]]
 * Output : [[0,0],[1,1]]
 * Explanation: The closest 2 restaurants are [0,0] and [1,1]. And only these two restaurants are in sqrt(2) meters.
 *
 * Example 2
 * Input: n = 3,List = [[0,1],[1,2],[2,1],[1,0]]
 * Output:[[0,1],[1,2],[2,1]]
 * Explanation: The closest 3 restaurants are [0,1],[1,2] and [2,1]. And only these three restaurants are in sqrt(5) meters.
 */
public class _1562_NumberOfRestautants {
    class Point{
        int x, y;
        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    /**
     * @param restaurant:
     * @param n:
     * @return: nothing
     */
    public List<List<Integer>> nearestRestaurant(List<List<Integer>> restaurant, int n) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (restaurant == null || restaurant.size() == 0 || restaurant.size() < n) {
            return result;
        }

        // 1. find n closest restaurants
        PriorityQueue<Point> pq = new PriorityQueue<>(n, new Comparator<Point>(){
            @Override
            public int compare(Point a, Point b) {
                return getDistance(b) - getDistance(a);
            }
        });

        for (List<Integer> list : restaurant) {
            pq.offer(new Point(list.get(0), list.get(1)));

            if (pq.size() > n) {
                pq.poll();
            }
        }

        // 2. find m the furthest distance frrom n restaurants
        int m = getDistance(pq.peek());

        // 3. find first n restaurants w/ distance < m
        for (List<Integer> list : restaurant) {
            int distance = getDistance(new Point(list.get(0), list.get(1)));
            if (distance <= m) {
                result.add(list);
            }
        }

        return result;
    }

    private int getDistance(Point a) {
        return a.x * a.x + a.y * a.y;
    }
}