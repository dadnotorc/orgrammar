/*
Medium
#DP, #Heap, #BFS
 */
package leetcode;

import java.util.*;

/**
 * 787. Cheapest Flights Within K Stops
 *
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst,
 * your task is to find the cheapest price from src to dst with up to k stops.
 * If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this:
 *             0
 *          /     \
 *   *100 /        \ 500
 *       1  -----   2
 *          *100
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 *
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this:
 *             0
 *          /     \
 *   *100 /        \ 500
 *       1  -----   2
 *          *100
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 *
 * Constraints:
 * - The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * - The size of flights will be in range [0, n * (n - 1) / 2].
 * - The format of each flight will be (src, dst, price).
 * - The price of each flight will be in the range [1, 10000].
 * - k is in the range of [0, n - 1].
 * - There will not be any duplicated flights or self cycles.
 */
public class _0787_CheapestFlightsWithinKStops {

    // todo 再多读读

    /**
     * DP - dp[i][j] 表示从src出发通过最多i个城市(edges)到达j所需的费用 - 14ms
     */
    public int findCheapestPrice_DP(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[K + 2][n]; // i是1-indexed, j是0-indexed

        for (int i = 0; i < K + 2; i++) {
            for (int j = 0; j < n; j++) {
                if (j == src) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 1; i < K + 2; i++) {
            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int w = flight[2];

                if (dp[i - 1][u] != Integer.MAX_VALUE) {
                    dp[i][v] = Math.min(dp[i][v], dp[i - 1][u] + w);
                }
            }
        }

        return dp[K + 1][dst] == Integer.MAX_VALUE ? -1 : dp[K + 1][dst];
    }



    /**
     * BFS 或者 DFS
     */
    class ResultType {
        int cost, city, distance;
        // 从src出发到达当前city, 所需的cost, 以及距离
        public ResultType (int city, int cost, int distance) {
            this.city = city;
            this.cost = cost;
            this.distance = distance;
        }
    }


    private Map<Integer, Map<Integer, Integer>> getPrices (int[][] flights) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] flight : flights) {
            if (!prices.containsKey(flight[0])) {
                prices.put(flight[0], new HashMap<>());
            }
            prices.get(flight[0]).put(flight[1], flight[2]);
        }
        return prices;
    }


    /**
     * BFS + PQ + HashMap - 4ms
     * 与上一个解法类似
     * 1. 获得所有航线的价格
     * 2. 从src开始, 先将本身加入队列 (到达城市为src, cost=0, distance=0)
     * 3. 访问队列, 将能到达的其他城市分别加入队列中.
     *    注意, 要保证出发城市与src的距离必须 <= K.
     *    例如要求K=0, 则出发城市距离src必须为0, 这样才能保证到达城市距离src为1, 即0转飞
     *
     * 不同点 - 使用PQ, PQ首位为从src出发, 能够到达的城市中, cost最低的那条航线 (注意, 最低的价格不一定是到达dst的)
     *
     * 另外加入HashMap记录已访问过的城市, 因为PQ的原因, 费用较低的航线会先被判断, 所以遇到转飞次数较大可以费用较高的路线, 就可以放弃
     *
     * 注意此法不能直接用在findCheapestPrice_BFS, 因为可以先加入直飞路线, 后续的转飞路线就不加入pq了
     */
    public int findCheapestPrice_PQ_BFS_HashMap(int n, int[][] flights, int src, int dst, int K) {
        // 第一层map的key为出发城市, 第二层map的key为到达城市, 第二层map的value为价格
        Map<Integer, Map<Integer, Integer>> prices = getPrices(flights);

        PriorityQueue<ResultType> pq = new PriorityQueue<>(new Comparator<ResultType>() {
            @Override
            public int compare(ResultType rt1, ResultType rt2) {
                return rt1.cost - rt2.cost;
            }
        });
//        PriorityQueue<ResultType> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        pq.offer(new ResultType(src, 0, 0));
        HashMap<Integer, Integer> hasSeen = new HashMap<>(); // key=城市, value=离src的距离; 已存在的entry表示之前访问过

        while (!pq.isEmpty()) {
            ResultType rt = pq.poll();

            // 未访问过 或者 之前访问时的距离更长
            // 例如 0->2 可以直飞, 也可以 0->1->2 转飞. 在PQ中1->2可能在0->2之前(因为价格较低), 两者都需要加入pq
            // 例如 [[0,1,1],[0,2,5],[1,2,1],[2,3,1]], src=0, dst=3, K=1
            if (!hasSeen.containsKey(rt.city) || hasSeen.get(rt.city) > rt.distance) {
                hasSeen.put(rt.city, rt.distance);

                if (rt.city == dst) {
                    return rt.cost;
                }

                // 注意这里需要 <=
                if (rt.distance <= K && prices.containsKey(rt.city)) {
                    Map<Integer, Integer> adj = prices.get(rt.city); // 从rt.city出发能够到达的城市
                    for (int city : adj.keySet()) {
                        pq.offer(new ResultType(city, rt.cost + adj.get(city), rt.distance + 1));
                    }
                }
            }
        }

        return -1;
    }




    /**
     * BFS + PQ - 19ms
     * 与上一个解法类似
     * 1. 获得所有航线的价格
     * 2. 从src开始, 先将本身加入队列 (到达城市为src, cost=0, distance=0)
     * 3. 访问队列, 将能到达的其他城市分别加入队列中.
     *    注意, 要保证出发城市与src的距离必须 <= K.
     *    例如要求K=0, 则出发城市距离src必须为0, 这样才能保证到达城市距离src为1, 即0转飞
     *
     * 不同点 - 使用PQ, PQ首位为从src出发, 能够到达的城市中, cost最低的那条航线 (注意, 最低的价格不一定是到达dst的)
     */
    public int findCheapestPrice_PQ_BFS(int n, int[][] flights, int src, int dst, int K) {
        // 第一层map的key为出发城市, 第二层map的key为到达城市, 第二层map的value为价格
        Map<Integer, Map<Integer, Integer>> prices = getPrices(flights);

        PriorityQueue<ResultType> pq = new PriorityQueue<>(new Comparator<ResultType>() {
            @Override
            public int compare(ResultType rt1, ResultType rt2) {
                return rt1.cost - rt2.cost;
            }
        });
//        PriorityQueue<ResultType> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        pq.offer(new ResultType(src, 0, 0));
        while (!pq.isEmpty()) {
            ResultType rt = pq.poll();
            if (rt.city == dst) {
                return rt.cost;
            }

            // 注意这里需要 <=
            if (rt.distance <= K && prices.containsKey(rt.city)) {
                Map<Integer, Integer> adj = prices.get(rt.city); // 从rt.city出发能够到达的城市
                for (int city : adj.keySet()) {
                    pq.offer(new ResultType(city, rt.cost + adj.get(city), rt.distance + 1));
                }
            }
        }

        return -1;
    }


    /**
     * BFS - 23ms
     * 1. 获得所有航线的价格
     * 2. 从src开始, 先将本身加入队列 (到达城市为src, cost=0, distance=0)
     * 3. 访问队列, 将能到达的其他城市分别加入队列中.
     *    注意, 要保证出发城市与src的距离必须 <= K.
     *    例如要求K=0, 则出发城市距离src必须为0, 这样才能保证到达城市距离src为1, 即0转飞
     */
    public int findCheapestPrice_BFS(int n, int[][] flights, int src, int dst, int K) {
        int minCost = Integer.MAX_VALUE;

        // 第一层map的key为出发城市, 第二层map的key为到达城市, 第二层map的value为价格
        Map<Integer, Map<Integer, Integer>> prices = getPrices(flights);

        Queue<ResultType> q = new LinkedList<>();
        q.offer(new ResultType(src, 0, 0)); // 从src出发到达src, 价格为0, 距离为0
        while (!q.isEmpty()) {
            ResultType rt = q.poll();
            if (rt.city == dst) {
                minCost = Math.min(minCost, rt.cost);
            }

            // 注意这里需要 <=
            if (rt.distance <= K && prices.containsKey(rt.city)) {
                Map<Integer, Integer> adj = prices.get(rt.city); // 从rt.city出发能够到达的城市
                for (int city : adj.keySet()) {
                    if (rt.cost + adj.get(city) < minCost) { // 转飞的价格较低
                        q.offer(new ResultType(city, rt.cost + adj.get(city), rt.distance + 1));
                    }
                }
            }
        }

        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }



    /**
     * DFS - 比BFS慢很多 - 241ms
     */
    int res;

    public int findCheapestPrice_DFS(int n, int[][] flights, int src, int dst, int K) {
        res = Integer.MAX_VALUE;

        // 第一层map的key为出发城市, 第二层map的key为到达城市, 第二层map的value为价格
        Map<Integer, Map<Integer, Integer>> prices = getPrices(flights);
        dfs(prices, src, dst, K + 1, 0); // K stops = K + 1 distance
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void dfs(Map<Integer, Map<Integer, Integer>> prices, int src, int dst, int distance, int cost) {
        if (distance < 0) {
            return;
        }
        if (src == dst) {
            res = cost;
            return;
        }

        if (prices.containsKey(src)) {
            Map<Integer, Integer> adj = prices.get(src); // 从src出发能够到达的城市
            for (int city : adj.keySet()) {
                if (cost + adj.get(city) < res) { // 转飞的价格较低
                    dfs(prices, city, dst, distance - 1, cost + adj.get(city));
                }
            }
        }
    }
}
