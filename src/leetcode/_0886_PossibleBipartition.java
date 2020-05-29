/*
Medium
#DFS, #BFS
 */
package leetcode;

import java.util.*;

/**
 * 886. Possible Bipartition
 *
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 *
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 *
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 * Example 1:
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 *
 * Example 2:
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 * Note:
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * There does not exist i != j for which dislikes[i] == dislikes[j].
 */
public class _0886_PossibleBipartition {

    /**
     * 1. 找出每个人不喜欢的所有人, 将他们加入列表. 可以使用Map<Integer, List<Integer>> 或者 List<List<Integer>>
     * 2. 遍历每个人, 如果此人未被分组, 将其加入1组, 并将其不喜欢的人加入2组. 之后再将这些人所不喜欢的人加入1组
     *    如果有冲突, 则说明无法完成
     *
     * 易错点:
     * 1. 创建不喜欢的人的列表时, 注意dislike时双向的, undirected graph, 所以要双向的加
     */
    public boolean possibleBipartition(int N, int[][] dislikes) {

        int[] group = new int[N + 1]; // 记录分组信息

        // 遍历dislikes数字, 将每个人所不喜欢的所有人放入一个队列
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        // 注意! dislike是双向的 undirected graph, 所以要双向加
        for (int i = 0; i < dislikes.length; i++) {
            map.get(dislikes[i][0]).add(dislikes[i][1]);
            map.get(dislikes[i][1]).add(dislikes[i][0]);
        }

        for (int i = 1; i <= N; i++) {
            // 如果 persona i 未分组, 将其加入group 1, 并将其不喜欢的所有人加入group 2
            // 再找这些人所不喜欢的其他人, 将其列入group 1
            // 如果有冲突, 则返回false
            if (group[i] == 0) {
                group[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    if (map.get(cur) != null) { // 注意 map中可能未包含所有person
                        for (int dislike : map.get(cur)) { // for each person that cur dislikes
                            if (group[dislike] == 0) {
                                group[dislike] = group[cur] == 1 ? 2 : 1;
                                q.offer(dislike);
                            } else {
                                // 注意这里不能直接return
                                if (group[dislike] == group[cur]) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

}
