package lintcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// 1562. Number of restaurants
public class _1562 {
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