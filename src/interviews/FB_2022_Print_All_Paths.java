package interviews;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 62. Unique Paths 的变种. 要打印出所有 path 的路径
 *
 * m x n matrix,
 * from top left to bottom right,
 * can only go down or right.
 */
public class FB_2022_Print_All_Paths {
    /* 假设
    1. in each path (list), print the directions, for example
       - Right -> Down -> Down\
       - Down -> Down -> Right
       - Down -> Right -> Down
     */

    /**
     * DFS
     */

    public List<List<String>> getPaths(int m, int n) {
        List<List<String>> ans = new ArrayList<>();

        if (m < 1 || n < 1) { return ans; }

        dfs(m, n, 0, 0, ans, new ArrayList<>());

        return ans;
    }

    public void dfs(int m, int n, int x, int y, List<List<String>> ans, List<String> list) {
        if (x == m - 1 && y == n - 1) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (x < m - 1) {
            list.add("Down");
            dfs(m, n, x + 1, y, ans, list);
            list.remove(list.size() - 1);
        }

        if (y < n - 1) {
            list.add("Right");
            dfs(m, n, x, y + 1, ans, list);
            list.remove(list.size() - 1);
        }
    }



    /* -------------------------- */

    /**
     * 如果要求的是, matrix 中每个block 有自己的值, 要求打印出每条 path 的值
     */

    public List<List<Integer>> getPaths(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();

        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) { return ans; }

        dfs(matrix, 0, 0, ans, new ArrayList<>());

        return ans;
    }

    public void dfs(int[][] matrix, int x, int y, List<List<Integer>> ans, List<Integer> list) {

        // 在外面加, 就不用在每个 if 里 重复加
        list.add(matrix[x][y]);

        if (x == matrix.length - 1 && y == matrix[0].length - 1) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (x < matrix.length - 1) {
            dfs(matrix, x + 1, y, ans, list);
            list.remove(list.size() - 1); // 这里别忘了减, 但是第一个 if 里不能减, 会 "Index -1 out of bounds for length 0"
        }

        if (y < matrix[0].length - 1) {
            dfs(matrix, x, y + 1, ans, list);
            list.remove(list.size() - 1); // 这里别忘了减, 但是第一个 if 里不能减, 会 "Index -1 out of bounds for length 0"
        }

        //也可以写成如下, 但是复杂多了
        /*
        if (x == matrix.length - 1 && y == matrix[0].length - 1) {
            list.add(matrix[x][y]);
            ans.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }

        if (x < matrix.length - 1) {
            list.add(matrix[x][y]);
            dfs(matrix, x + 1, y, ans, list);
            list.remove(list.size() - 1);
        }

        if (y < matrix[0].length - 1) {
            list.add(matrix[x][y]);
            dfs(matrix, x, y + 1, ans, list);
            list.remove(list.size() - 1);
        }
        */
    }



    @Test
    public void test1 () {
        int[][] matrix = {{1,2,3} , {4,5,6}, {7,8,9}};
        getPaths(matrix);
    }
}
