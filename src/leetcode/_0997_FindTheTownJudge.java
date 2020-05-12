/*
Easy
#Graph
 */
package leetcode;

/**
 * 997. Find the Town Judge
 *
 * In a town, there are N people labelled from 1 to N.
 * There is a rumor that one of these people is secretly the town judge.
 *
 * If the town judge exists, then:
 * 1. The town judge trusts nobody.
 * 2. Everybody (except for the town judge) trusts the town judge.
 * 3. There is exactly one person that satisfies properties 1 and 2.
 *
 * You are given trust, an array of pairs trust[i] = [a, b] representing that
 * the person labelled a trusts the person labelled b.
 *
 * If the town judge exists and can be identified, return the label of the town judge.
 * Otherwise, return -1.
 *
 * Example 1:
 * Input: N = 2, trust = [[1,2]]
 * Output: 2
 *
 * Example 2:
 * Input: N = 3, trust = [[1,3],[2,3]]
 * Output: 3
 *
 * Example 3:
 * Input: N = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 *
 * Example 4:
 * Input: N = 3, trust = [[1,2],[2,3]]
 * Output: -1
 *
 * Example 5:
 * Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * Output: 3
 *
 * Note
 * 1 <= N <= 1000
 * trust.length <= 10000
 * trust[i] are all different
 * trust[i][0] != trust[i][1]
 * 1 <= trust[i][0], trust[i][1] <= N
 */
public class _0997_FindTheTownJudge {

    /**
     * 考虑入度出度
     * 把trust考虑成graph, 所有的pair为direct edge
     * 如果存在某个点 inDegree - outDegree = N - 1 (N-1个村民相信他, 他不相信任何人), 此人为judge
     *
     * time: O(N + T), space: O(N)
     */
    public int findJudge(int N, int[][] trust) {
        int[] count = new int[N + 1];

        for (int[] pair : trust) {
            count[pair[0]]--; // pair前者出度--
            count[pair[1]]++; // pair后者入度++
        }

        for (int i = 1; i <= N; i++) {
            if (count[i] == N -1) {
                return i;
            }
        }

        return -1;
    }
}
