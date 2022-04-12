package interviews;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 输入是一个 M*N 的 image matrix，然后要求从每一行删去一个点，得到一个 M*（N-1）的 matrix
 *
 * 删点的时候有一个限制条件，要求每一个被删去的点互相之间是相邻或者斜对角的，
 * 也就是说当前删掉的 column 是 j 的话，下一行只能删 j, j - 1, j + 1 这三个位置。
 *
 * 既然有这样的限制条件，删去的点就可以形成一条 path，然后这条 path 上的每一个点都会有一个cost。
 * 这题要求找到 cost 之和最小的那条 path，并且返回删完这条 path 之后的那个 image matrix。
 *
 * 这题可以用 recursion 或者 dp 来做。我觉得比较麻烦的是要先计算每一个点的cost。
 * 这里面试官会跟你讨论你认为什么样的 cost 比较合理，会让删完以后的 image 还是比较可用。
 * 接下来就是常规的 recursion 或者 dp。最后找到那条 cost 之和最小的 path 以后，再重新构建一遍那个 matrix。
 *
 */
public class Image_Lowest_Cost_Path {

    /* DP
    假设 image 是

    0  1  2  3  4
    --------------
    1  5  3  2  4       MAX  1  5 3  2 4 MAX
    7  3  2  8  1   ->  M    8  4 4 10 3   M   min([i - 1][j - 1], [i - 1][j], [i - 1][j + 1]) + [i][j]
    4  8  1  0  2       M    8 12 5  3 5   M

    path 为
    0  0  3  3  3
    1  1  2  4  4
    0  1  2  3  4

    不能用 1D array 做, 会覆盖掉 j - 1的值
     */

    public List<Integer> minPath(int[][] image) {
        int m = image.length, n = image[0].length;

        List<List<Integer>> paths = new ArrayList<>();

        Map<Integer, List<Integer>> map =new HashMap<>();
        int[][] dp = new int[m][n + 2];

        // 将 dp 首尾 设为 MAX, 并将 第一行 直接写入
        for (int i = 0 ; i < m; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][n + 1] = Integer.MAX_VALUE;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j + 1] = image[0][j];
            map.put(j, new ArrayList<>());
        }

        for (int i = 1; i < m; i++) { // 这里从 1 开始, 因为想判断上一行 min index 的位置
            for (int j = 1; j <= n; j++) {
                int min_index = dp[i - 1][j - 1] < dp[i - 1][j] ? j - 1 : j;
                min_index = dp[i - 1][j + 1] < dp[i - 1][min_index] ? j + 1 : min_index;

                map.get(j - 1).add(min_index - 1);

                dp[i][j] = dp[i - 1][min_index] + image[i][j - 1];
            }
        }

        // 在最后一行, 补齐所有的 path list, 并找出最小 path 的 index
        int index_lowest_path = 0;
        for (int j = 1; j <= n; j++) {
            map.get(j - 1).add(j - 1);

            if (dp[m - 1][j] < dp[m - 1][index_lowest_path]) {
                index_lowest_path = j;
            }
        }

        // dp 中的下标比实际多 1, 所以 这里 -1
        return map.get(index_lowest_path - 1);
    }



    @Test
    public void test1() {
        int[][] image = {{1,5,3,2,4}, {7,3,2,8,1}, {4,8,1,0,2}};

        List<Integer> ans = minPath(image);

    }
}
