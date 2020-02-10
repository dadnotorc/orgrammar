/*
Hard
Divide and Conquer
Facebook, Google
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 653. Expression Add Operators
 *
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or *
 * between the digits so they evaluate to the target value.
 *
 * Example 1:
 * Input: "123", 6
 * Output: ["1*2*3","1+2+3"]
 *
 * Example 2:
 * Input: "232", 8
 * Output: ["2*3+2", "2+3*2"]
 *
 * Example 3:
 * Input: "105", 5
 * Output: ["1*0+5","10-5"]  注意, 不是每个digit, 可能是多个digits组成的数字, 例如这里的10
 *
 * Example 4:
 * Input: "00", 0
 * Output: ["0+0", "0-0", "0*0"]
 *
 * Example 5:
 * Input: "3456237490", 9191
 * Output: []
 */
public class _0653_ExpressionAddOperators {

    // todo input = "000000000000", 0 时, 会内存溢出

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        dfs(num, target, ans, 0, "", 0, 0);
        return ans;
    }

    /**
     *
     * @param num input的数字
     * @param target input需要达到的target
     * @param ans output的答案
     * @param start 当前指针所在, 指去num中某个数字
     * @param exp 当前组成的公式
     * @param res 当前公式的结果
     * @param lastNum 当前公式中, 最后一个数字
     */
    private void dfs(String num, int target, List<String> ans,
                     int start, String exp, long res, long lastNum) {

        // 递归出口 - start已指完所有数字, 如sum等于target, 将str加入ans中
        if (start == num.length()) {
            if (res == target)
                ans.add(exp);
            return;
        }

        for (int i = start; i < num.length(); i++) {
            // 如果使用int, num很大的时候会无法完成, 例如 input = "3456237490", 9191
            long x = Long.parseLong(num.substring(start, i + 1)); //不是每个digit, 可能是多个digits组成的数字

            if (start == 0) {
                dfs(num, target, ans, i + 1, "" + x, x, x);
            } else {
                // 加法
                dfs(num, target, ans, i + 1, exp + "+" + x, res + x, x);

                // 减法
                dfs(num, target, ans, i + 1, exp + "-" + x, res - x, -x);

                // 乘法 注意没有括号, 要先减掉上个数字, 先执行乘法
                dfs(num, target, ans, i + 1, exp + "*" + x, res - lastNum + lastNum * x, lastNum * x);
            }

            // 注意: 如果当前x为0, 说明从start往后的数字是以0开头, 例如01,02, 这些数字是无效的, 所以必须退出循环
            if (x == 0)
                break;
        }
    }
}
